package com.anselme.ikofi.utils.functions;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordManager {
    public static String encode(String password){
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean compare(String rawPassword, String hashedPassword){
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }
}
