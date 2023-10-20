package com.linhv.scheduling.controller;

import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.model.dto.DTOFacultyRole;
import com.linhv.scheduling.service.AccountService;
import com.linhv.scheduling.service.TokenService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AccountUserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private DaoAuthenticationProvider authProvider;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login/")
    public String login(@RequestBody Account account) {
        Authentication authenticated = authProvider.authenticate(new UsernamePasswordAuthenticationToken(account.getId(), account.getPassword()));
        return this.tokenService.generateToken(authenticated);
    }

    @GetMapping("/current-user/")
    public ResponseEntity<User> currentUserDetails(Principal principal) {
        User user = this.userService.getUserById(Long.parseLong(principal.getName()));
        Account account = this.accountService.getById(user.getId());
        user.setImage(account.getImage());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<List<User>> getAll(@RequestBody DTOFacultyRole data) {
        return new ResponseEntity<>(this.userService.getAllByRoleAndFacultyId(data.getRole(), data.getFaculty().getId()), HttpStatus.OK);
    }

    @PostMapping("/new/")
    public ResponseEntity<User> addUser(@ModelAttribute User user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }

}
