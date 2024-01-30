package com.example.demo.account;

import com.example.demo.customsException.NotFoundHandler;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository , PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<URI> createAccount(Account account, Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()){
            account.setRoles(List.of(role.get()));
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
            return ResponseEntity.created(URI.create("/api/v1/account/" + account.getAccountId())).build();
        }
        throw new NotFoundHandler("Role with id=" + roleId + " not found");
    }
    public ResponseEntity<HttpStatus> deleteAccount(Long accountId){
        boolean isExist = accountRepository.existsById(accountId);
        if (isExist){
            accountRepository.deleteById(accountId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundHandler("Account with id=" + accountId + " not found");
    }
    public ResponseEntity<Iterable<Account>>getAllAccount() {
        return ResponseEntity.ok(accountRepository.findAll());
    }
}
