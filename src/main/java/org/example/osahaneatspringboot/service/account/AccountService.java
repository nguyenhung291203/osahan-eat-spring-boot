package org.example.osahaneatspringboot.service.account;

import java.util.HashMap;
import java.util.Map;

import org.example.osahaneatspringboot.constant.cache.CacheKey;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.example.osahaneatspringboot.repository.IAccountRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService implements IAccountService {
    IAccountRepository repository;

    @Override
    @Cacheable(value = CacheKey.ACCOUNT_KEY, key = "#username")
    public Account findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> {
            Map<String, String> errors = new HashMap<>();
            errors.put("username", "Tài khoản hoặc mật khẩu không chính xác");
            errors.put("password", "Tài khoản hoặc mật khẩu không chính xác");
            return new ValidateException(errors);
        });
    }
}
