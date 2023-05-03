package com.springdemo.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Base64Util {

    public static String encode(String username, String password){
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = username+":"+password;

        return  encoder.encodeToString(
                normalString.getBytes(StandardCharsets.UTF_8) );
    }

}
