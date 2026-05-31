package org.example.backend.controllers;

import org.example.backend.Classes.Account;
import org.example.backend.Classes.TransferRequest;
import org.example.backend.Classes.User;
import org.example.backend.Classes.AccountRequest;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("register")
    public String register(@RequestBody User user) {
        String username = user.getFirstName() + user.getLastName() ;
        user.setUsername(username);
        User saved = repository.save(user);
        return "Register successful:" + saved.getId();
    }

    @PostMapping("/loginCheck")
        public String loginCheck(@RequestBody User user) {

        System.out.println(user.getUsername() + " " + user.getPassword());
        user.setUsername(user.getFirstName()+user.getLastName());

        User u = repository.findByUsername(user.getUsername());

        if (u != null && u.getPassword().equals(user.getPassword())) {
            return "Login successful:" + u.getId();
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        System.out.println(request.getBalance());
        
        User user = repository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return "User not found";
        }
        account.setOwner(user);

        user.getAccounts().add(account);
        repository.save(user);

        return "Acc creation successful";
        
    }

    @GetMapping("/getAccounts/{userId}")
    public List<Account> getAccounts(@PathVariable long userId) {
        User u = repository.findById(userId).orElse(null);
        if (u == null) {
            return List.of();
        }
        return u.getAccounts();
    }

    @GetMapping("/acc/{userId}/{accId}")
    public Account getAccount(@PathVariable long userId, @PathVariable long accId) {
        User u = repository.findById(userId).orElse(null);
        if (u == null) {return null;}
        Account a = u.getAccounts().stream().filter(a1 -> a1.getId() == accId).findFirst().orElse(null);
        if (a == null) {return null;}
        return a;

    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestBody TransferRequest request) {

        User u = repository.findById(request.getUserId()).orElse(null);
        if (u == null) return "User neexistuje";

        Account sourceAcc = u.getAccounts().stream()
                .filter(a1 -> a1.getId() == (request.getSourceAccountId()))
                .findFirst().orElse(null);

        User targetUser = repository.findAll().stream()
                .filter(user -> user.getAccounts().stream().anyMatch(a -> a.getName().equalsIgnoreCase(request.getTargetAccountName())))
                .findFirst().orElse(null);

        if (sourceAcc == null || targetUser == null) {
            return "Ucet nebo prijemce nenalezen";
        }

        Account targetAcc = targetUser.getAccounts().stream()
                .filter(a -> a.getName().equalsIgnoreCase(request.getTargetAccountName()))
                .findFirst().get();

        // Provedeme změnu zůstatků
        sourceAcc.setBalance(sourceAcc.getBalance() - request.getAmount());
        targetAcc.setBalance(targetAcc.getBalance() + request.getAmount());

        repository.save(u);
        repository.save(targetUser);

        return "Prevod uspesny";
    }
}
