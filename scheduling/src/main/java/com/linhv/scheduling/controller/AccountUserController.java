package com.linhv.scheduling.controller;

import com.linhv.scheduling.service.AccountService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AccountUserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;


}
