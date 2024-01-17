package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "api/v1/account")

public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService= accountService;
    }
    @PostMapping("{role_id}")
    public ResponseEntity<?> createFaculty(@RequestBody Account account , @PathVariable("role_id")Long roleId) {
        boolean isAdded = accountService.createAccount(account , roleId);
        Map<String, Object> map = new LinkedHashMap<>();
        if (isAdded) {
            map.put("status", 1);
            map.put("message", "Faculty is Saved Successfully");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("status", 0);
            map.put("message", "role with id " + roleId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllAccount(){
        Map<String, Object> map = new LinkedHashMap<String , Object>();
        List<Account> accounts = accountService.getAllAccount();
        if (!accounts.isEmpty()){
            map.put("status", 1);
            map.put("data", accounts);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("account_id")Long accountId){
        Map<String, Object> map = new LinkedHashMap<String , Object>();
        boolean isDelete = accountService.deleteAccount(accountId);
        if (isDelete) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Account with id " + accountId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }



}
