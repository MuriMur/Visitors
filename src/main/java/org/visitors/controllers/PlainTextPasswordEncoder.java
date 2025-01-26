package org.visitors.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Base64;

public class PlainTextPasswordEncoder implements PasswordEncoder {

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("1234".getBytes()));
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }
 
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawEncoded = Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
        return rawEncoded.equals(encodedPassword);
    }
 
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }
 
    private static final PasswordEncoder INSTANCE = new PlainTextPasswordEncoder();
 
    private PlainTextPasswordEncoder() {
    }  
}