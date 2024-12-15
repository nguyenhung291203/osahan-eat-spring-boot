package org.example.osahaneatspringboot.mapper;

import java.util.Date;

import org.example.osahaneatspringboot.dto.request.RegisterRequest;
import org.example.osahaneatspringboot.dto.response.AccountResponse;
import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toAccount(RegisterRequest request) {
        return Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public Profile toProfile(RegisterRequest request) {
        return Profile.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .gender(request.isGender())
                .dateOfBirth(request.getDateOfBirth())
                .fullName(request.getFullName())
                .build();
    }

    public AccountResponse toAccountResponse(Account request) {
        return AccountResponse.builder()
                .id(request.getId())
                .role(request.getRole())
                .fullName(request.getProfile().getFullName())
                .avatar(request.getProfile().getAvatar())
                .address(request.getProfile().getAddress())
                .gender(request.getProfile().isGender())
                .username(request.getUsername())
                .dateOfBirth(request.getProfile().getDateOfBirth())
                .createAt(request.getCreateAt())
                .updateAt(getLatestUpdate(request.getUpdateAt(), request.getUpdateAt()))
                .build();
    }

    private Date getLatestUpdate(Date profileUpdate, Date accountUpdate) {
        if (profileUpdate == null) return accountUpdate;
        if (accountUpdate == null) return profileUpdate;
        return profileUpdate.after(accountUpdate) ? profileUpdate : accountUpdate;
    }
}
