package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.response.TokenResponse;
import org.example.osahaneatspringboot.entity.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public TokenResponse toTokenResponse(Token request) {
        return TokenResponse.builder()
                .token(request.getToken())
                .refreshToken(request.getRefreshToken())
                .refreshTokenExpiresAt(request.getRefreshTokenExpiresAt())
                .tokenType(request.getTokenType())
                .expired(request.getExpired())
                .expiresAt(request.getExpiresAt())
                .revoked(request.getRevoked())
                .build();
    }
}
