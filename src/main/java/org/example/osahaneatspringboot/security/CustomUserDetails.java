package org.example.osahaneatspringboot.security;

import java.util.Collection;
import java.util.List;

import org.example.osahaneatspringboot.entity.Account;
import org.example.osahaneatspringboot.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails {
    String id;
    String username;
    String password;
    String isActive;

    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public static Account toAccount(CustomUserDetails customUserDetails) {
        Role role = new Role();

        if (customUserDetails.getAuthorities() != null
                && !customUserDetails.getAuthorities().isEmpty()) {
            String roleCode =
                    customUserDetails.getAuthorities().iterator().next().getAuthority();
            role.setCode(roleCode);
        }

        return Account.builder()
                .username(customUserDetails.getUsername())
                .password(customUserDetails.getPassword())
                .role(role)
                .build();
    }

    public static CustomUserDetails toCustomUserDetails(Account account) {
        List<SimpleGrantedAuthority> authorityList =
                List.of(new SimpleGrantedAuthority(account.getRole().getCode()));
        return CustomUserDetails.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(authorityList)
                .build();
    }
}
