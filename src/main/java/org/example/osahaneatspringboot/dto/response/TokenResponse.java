package org.example.osahaneatspringboot.dto.response;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TokenResponse {
    String token;
    String tokenType;
    Boolean expired;
    Boolean revoked;
    LocalDateTime expiresAt;
    String refreshToken;
    LocalDateTime refreshTokenExpiresAt;
}
