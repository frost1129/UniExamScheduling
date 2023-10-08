package com.linhv.scheduling.service;

import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {

//    READ
    Account getById(Long id);

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
