package com.example.demo.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);
}
