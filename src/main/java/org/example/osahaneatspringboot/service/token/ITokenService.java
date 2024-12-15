package org.example.osahaneatspringboot.service.token;

import jakarta.servlet.http.HttpServletRequest;

import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Token;

public interface ITokenService {
    Token store(Account account);

    boolean validate(String token);

    String findUsername(String token);

    String getTokenFromRequest(HttpServletRequest request);

    Token getTokenByJwt(String jwt);

    void revokeToken(String token);
}
