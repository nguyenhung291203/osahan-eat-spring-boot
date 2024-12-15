package org.example.osahaneatspringboot.controller;

import org.example.osahaneatspringboot.dto.response.AccountResponse;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.mapper.AccountMapper;
import org.example.osahaneatspringboot.service.account.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/accounts")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý tài khoản")
public class AccountController {
    IAccountService accountService;

    AccountMapper mapper;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy thông tin cá nhân")
    public ApiResponse<AccountResponse> getAccountMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ApiResponse.ok(mapper.toAccountResponse(accountService.findByUsername(username)));
    }
}
