package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Profile;
import com.anselme.ikofi.models.User;

import com.anselme.ikofi.models.enums.EAccountStatus;
import com.anselme.ikofi.models.enums.ERoleName;
import com.anselme.ikofi.services.UserService;
import com.anselme.ikofi.utils.dto.requests.SignInDTO;
import com.anselme.ikofi.utils.dto.requests.SignUpDTO;
import com.anselme.ikofi.utils.jwt.JwtTokenProvider;
import com.anselme.ikofi.utils.dto.responses.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService service, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = service;
        this.passwordEncoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInDTO dto) {
        Optional<User> _user = userService.findUser(dto.getLogin());

        if (_user.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email or password ... ");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new SignUpResponse(_user.get(), jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpDTO dto) {
        if (dto.getEmail() != null && userService.emailAlreadyInUse(dto.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Address already in use!");

        if (userService.userNameAlreadyInUse(dto.getUsername()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use !");

        User user = new User(dto.getFullName(), dto.getEmail(), ERoleName.ROLE_USER, dto.getMobile(), dto.getUsername(), dto.getPassword());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Profile profile = new Profile(dto.getAddress(), dto.getIdCard(), "No title");

        Account account = new Account(0, EAccountStatus.ACTIVE);

        user.setProfile(profile);
        user.setAccount(account);

        User _user = userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(_user, "First Log in to get the token ... "));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
