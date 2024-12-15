package org.example.osahaneatspringboot.controller;

import static org.springframework.http.HttpStatus.CREATED;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.LoginRequest;
import org.example.osahaneatspringboot.dto.request.RegisterRequest;
import org.example.osahaneatspringboot.dto.response.AccountResponse;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.TokenResponse;
import org.example.osahaneatspringboot.mapper.AccountMapper;
import org.example.osahaneatspringboot.mapper.TokenMapper;
import org.example.osahaneatspringboot.service.auth.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/auth")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý xác thực")
public class AuthController {
    AccountMapper accountMapper;
    TokenMapper tokenMapper;
    IAuthService service;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    @Operation(summary = "Đăng ký tài khoản")
    public ApiResponse<AccountResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ApiResponse.ok(accountMapper.toAccountResponse(service.register(request)));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Đăng nhập")
    public ApiResponse<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.ok(tokenMapper.toTokenResponse(service.login(request)));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Đăng xuất")
    public ApiResponse<Void> logout(HttpServletRequest request) {
        return ApiResponse.ok(service.logout(request));
    }
}
