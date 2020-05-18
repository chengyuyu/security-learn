package com.example.securitylearn.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
public class RegistrationFrom {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(
                username, passwordEncoder.encode(password),true
        );
    }
}
