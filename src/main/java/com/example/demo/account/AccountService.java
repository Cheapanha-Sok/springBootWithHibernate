package com.example.demo.account;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public boolean createAccount(Account account, Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()){
            account.setRoles(List.of(role.get()));
            accountRepository.save(account);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean deleteAccount(Long accountId){
        boolean isExist = accountRepository.existsById(accountId);
        if (isExist){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
}
