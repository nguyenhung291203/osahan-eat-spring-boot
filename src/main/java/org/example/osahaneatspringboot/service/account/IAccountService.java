package org.example.osahaneatspringboot.service.account;

import org.example.osahaneatspringboot.entity.Account;

public interface IAccountService {
    Account findByUsername(String username);
}
