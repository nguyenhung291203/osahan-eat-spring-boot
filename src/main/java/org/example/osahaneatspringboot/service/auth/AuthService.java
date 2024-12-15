package org.example.osahaneatspringboot.service.auth;

import static org.example.osahaneatspringboot.constant.code.RoleCode.CUSTOMER;
import static org.example.osahaneatspringboot.constant.error.AccountErrorCode.ACCOUNT_LOCKED;
import static org.example.osahaneatspringboot.constant.message.AccountErrorMessage.ACCOUNT_EXISTED;
import static org.example.osahaneatspringboot.constant.message.AccountErrorMessage.INCORRECT_PASSWORD;
import static org.example.osahaneatspringboot.constant.message.RoleErrorMessage.ROLE_NOT_FOUND;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.dto.request.LoginRequest;
import org.example.osahaneatspringboot.dto.request.RegisterRequest;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Profile;
import org.example.osahaneatspringboot.entity.Role;
import org.example.osahaneatspringboot.entity.Token;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.example.osahaneatspringboot.mapper.AccountMapper;
import org.example.osahaneatspringboot.repository.IAccountRepository;
import org.example.osahaneatspringboot.repository.IProfileRepository;
import org.example.osahaneatspringboot.repository.IRoleRepository;
import org.example.osahaneatspringboot.repository.ITokenRepository;
import org.example.osahaneatspringboot.service.token.ITokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService {
    ITokenService tokenService;
    IAccountRepository accountRepository;
    IRoleRepository roleRepository;
    IProfileRepository profileRepository;
    ITokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    AccountMapper accountMapper;

    @Override
    @Transactional
    public Account register(RegisterRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new ValidateException(Map.of("username", ACCOUNT_EXISTED));
        }
        Account account = accountMapper.toAccount(request);
        if (request.getRoleId() == null) {
            request.setRoleId(CUSTOMER);
        }
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> {
            Map<String, String> error = new HashMap<>();
            error.put("roleId", ROLE_NOT_FOUND);
            return new ValidateException(error);
        });
        account.setRole(role);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setIsActive(true);
        account = accountRepository.save(account);
        Profile profile = accountMapper.toProfile(request);
        profile.setAccount(account);
        account.setProfile(profile);
        return account;
    }

    @Override
    @Transactional
    public Token login(LoginRequest request) {
        Account account =
                accountRepository.findByUsername(request.getUsername()).orElse(null);
        if (account == null || !passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            Map<String, String> errors = new HashMap<>();
            errors.put("username", INCORRECT_PASSWORD);
            errors.put("password", INCORRECT_PASSWORD);
            throw new ValidateException(errors);
        }
        if (!Boolean.TRUE.equals(account.getIsActive())) {
            throw new ApiException(ACCOUNT_LOCKED);
        }
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.store(account);
    }

    @Override
    @Transactional
    public Void logout(HttpServletRequest request) {
        String jwt = tokenService.getTokenFromRequest(request);
        tokenService.revokeToken(jwt);
        SecurityContextHolder.clearContext();
        return null;
    }
}
