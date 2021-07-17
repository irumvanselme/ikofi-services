package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.Account;
import com.anselme.ikofi.models.Profile;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.models.enums.EAccountStatus;
import com.anselme.ikofi.models.enums.ERoleName;
import com.anselme.ikofi.services.IAccountService;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.requests.SignInDTO;
import com.anselme.ikofi.utils.dto.requests.SignUpDTO;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import com.anselme.ikofi.utils.functions.PasswordManager;
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
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final IAccountService accountService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, IUserService service, PasswordEncoder encoder, IAccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = service;
        this.passwordEncoder = encoder;
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody SignInDTO dto) {
        Optional<User> _user = userService.findUser(dto.getLogin());

        if (_user.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("Invalid Credentials"));

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("Invalid Email or password ... "));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new ApiResponse(new SignUpResponse(_user.get(), jwt)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody SignUpDTO dto) {
        if (dto.getEmail() != null && userService.emailAlreadyInUse(dto.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("Email Address already in use!"));

        if (userService.userNameAlreadyInUse(dto.getUsername()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("Username already in use !"));

        User user = new User(dto.getFullName(), dto.getEmail(), ERoleName.ROLE_USER, dto.getMobile(), dto.getUsername(), dto.getPassword());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Profile profile = new Profile();

        Account account = new Account(0, PasswordManager.encode(dto.getPin()), EAccountStatus.ACTIVE);
        account.setAccountNumber(accountService.getNewAccountNumber());

        user.setProfile(profile);
        user.setAccount(account);

        User _user = userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(new SignUpResponse(_user, "First Log in to get the token ... ")));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> profile() {
        User user = userService.getLoggedInUser();
        return ResponseEntity.ok(new ApiResponse(user));
    }
}
