package org.example.osahaneatspringboot.service.auth;

import jakarta.servlet.http.HttpServletRequest;

import org.example.osahaneatspringboot.dto.request.LoginRequest;
import org.example.osahaneatspringboot.dto.request.RegisterRequest;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Token;

public interface IAuthService {
    Account register(RegisterRequest request);

    Token login(LoginRequest request);

    Void logout(HttpServletRequest request);
}
