package com.cydeo.repository;

import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List <Account> accountList = new ArrayList<>();

    public static List<Account> findAll() {
        return accountList;
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public Account findById(UUID id) {

        return findById(id);
    }
}
