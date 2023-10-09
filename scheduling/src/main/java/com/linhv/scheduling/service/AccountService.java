package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService extends UserDetailsService {

//    READ
    Account getById(Long id);
    boolean authAccount(Long id, String password);

//    CREATE
    Account newAccount(User user);

//    UPDATE
    boolean updatePassword(Long id, String password);
    boolean updateImage(Long id, MultipartFile imageFile);
    void lockAccount(Long id);
    void unlockAccount(Long id);

//    DELETE
    boolean deleteAccount(Long id);
}
