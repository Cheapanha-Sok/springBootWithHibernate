package com.example.demo.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping(value = "api/v1/account/")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService= accountService;
    }
    @PostMapping(path = "{role_id}")
    public ResponseEntity<URI> createAccount(@RequestBody Account account , @PathVariable("role_id")Long roleId) {
        return accountService.createAccount(account , roleId);
    }
    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccount(){
        return accountService.getAllAccount();
    }
    @DeleteMapping("{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("account_id")Long accountId){
        return accountService.deleteAccount(accountId);
    }
    @GetMapping("{account_id}")
    public ResponseEntity<Optional<Account>>getAccount(@PathVariable("account_id")Long accountId){
        return accountService.getAccount(accountId);
    }
}
