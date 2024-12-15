package org.example.osahaneatspringboot.service.token;

import static org.example.osahaneatspringboot.constant.cache.CacheKey.TOKEN_KEY;
import static org.example.osahaneatspringboot.constant.error.GlobalErrorCode.UNAUTHENTICATED;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Token;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.repository.ITokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenService implements ITokenService {
    @NonFinal
    @Value("${app.jwt-secret}")
    String jwtSecret;

    @NonFinal
    @Value("${app.jwt-expiration-milliseconds}")
    long jwtExpirationDate;

    @NonFinal
    @Value("${app.jwt-expiration-refresh-token}")
    long jwtExpirationRefreshToken;

    ITokenRepository repository;

    Logger logger = LoggerFactory.getLogger(TokenService.class);

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private String generateToken(Account account) {
        String username = account.getUsername();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private String getUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            logger.warn("Token đã hết hạn: {}", e.getMessage());
        } catch (io.jsonwebtoken.JwtException e) {
            logger.error("Token không hợp lệ: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi giải mã token: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Token store(Account account) {
        String token = generateToken(account);
        Token tokenEntity = Token.builder()
                .account(account)
                .token(token)
                .refreshToken(UUID.randomUUID().toString())
                .revoked(false)
                .expired(false)
                .tokenType("Bearer")
                .expiresAt(LocalDateTime.now().plusSeconds(jwtExpirationDate / 1000))
                .refreshTokenExpiresAt(LocalDateTime.now().plusSeconds(jwtExpirationRefreshToken / 1000))
                .build();
        return repository.save(tokenEntity);
    }

    @Override
    public boolean validate(String token) {
        if (isInvalidToken(token)) {
            return false;
        }

        String username = getUsername(token);
        if (username == null) {
            return false;
        }

        return isValidTokenEntity(repository.findByToken(token).orElse(null));
    }

    private boolean isInvalidToken(String token) {
        return token == null || token.trim().isEmpty();
    }

    private boolean isValidTokenEntity(Token tokenEntity) {
        if (tokenEntity == null) {
            return false;
        }

        return !Boolean.TRUE.equals(tokenEntity.getExpired())
                && (tokenEntity.getExpiresAt() == null
                        || tokenEntity.getExpiresAt().isAfter(LocalDateTime.now()))
                && !Boolean.TRUE.equals(tokenEntity.getRevoked());
    }

    @Override
    public String findUsername(String token) {
        return getUsername(token);
    }

    @Override
    @Cacheable(
            value = TOKEN_KEY,
            key =
                    "#request.getHeader('Authorization') != null ? #request.getHeader('Authorization').substring(7) : 'defaultKey'")
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    @Cacheable(value = TOKEN_KEY, key = "#jwt")
    public Token getTokenByJwt(String jwt) {
        return repository.findByToken(jwt).orElseThrow(() -> new ApiException(UNAUTHENTICATED));
    }

    @Override
    @Cacheable(value = TOKEN_KEY, key = "#token")
    public void revokeToken(String token) {
        Token tokenEntity = repository.findById(token).orElseThrow(() -> new ApiException(UNAUTHENTICATED));
        tokenEntity.setRevoked(true);
        repository.save(tokenEntity);
    }
}
