package com.alibou.banking.security;

import com.alibou.banking.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String extractUsername(String token) {
        return token.split(":")[0];
    }

    public boolean isNotTokenValid(String token, UserDetails userDetails) {
        return !isTokenValid(token, userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return  userDetails.getUsername().equals(extractUsername(token)) &&
                Boolean.parseBoolean(token.split(":")[4]);
    }

    public String generateToken(User user) {
        StringBuilder builder = new StringBuilder();
        return builder
                .append(user.getEmail())
                .append(":")
                .append(user.getFirstName())
                .append(":")
                .append(user.getLastName())
                .append(":")
                .append(true)
                .toString();
    }
}
