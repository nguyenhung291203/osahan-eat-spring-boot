package org.example.osahaneatspringboot.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import com.nimbusds.jose.util.Pair;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BypassTokenService {

    @Value("${api.prefix}")
    String apiPrefix;

    final Set<Pair<String, String>> BYPASS_ENDPOINTS = new HashSet<>();

    @PostConstruct
    void initBypassEndpoints() {
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/categories", apiPrefix), "GET"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/view", apiPrefix), "GET"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/dishes", apiPrefix), "GET"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/restaurants", apiPrefix), "GET"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/restaurants/filter", apiPrefix), "POST"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/auth/register", apiPrefix), "POST"));
        BYPASS_ENDPOINTS.add(Pair.of(String.format("%s/auth/login", apiPrefix), "POST"));
        BYPASS_ENDPOINTS.add(Pair.of("/api-docs", "GET"));
        BYPASS_ENDPOINTS.add(Pair.of("/swagger-ui.html", "GET"));
        BYPASS_ENDPOINTS.add(Pair.of("/swagger-ui/index.html", "GET"));
    }

    public boolean isBypassToken(String servletPath, String method) {
        return BYPASS_ENDPOINTS.stream()
                .anyMatch(endpoint -> servletPath.startsWith(endpoint.getLeft())
                        && method.equalsIgnoreCase(endpoint.getRight()));
    }
}
