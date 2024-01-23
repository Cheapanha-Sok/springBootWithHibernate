package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping(value = "api/v1/account/")

public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService= accountService;
    }
    @PostMapping("{role_id}")
    public ResponseEntity<?> createAccount(@RequestBody Account account , @PathVariable("role_id")Long roleId) {
        boolean isAdded = accountService.createAccount(account , roleId);
        if (isAdded) {
            return ResponseEntity.created(URI.create("/api/v1/account/" + account.getAccountId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccount(){
        return ResponseEntity.ok(accountService.getAllAccount());
    }
    @DeleteMapping("{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("account_id")Long accountId){
        boolean isDelete = accountService.deleteAccount(accountId);
        if (isDelete) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
