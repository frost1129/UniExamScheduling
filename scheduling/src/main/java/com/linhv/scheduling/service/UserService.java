package com.linhv.scheduling.service;

import com.linhv.scheduling.model.User;

public interface UserService {
    User createUser(User u);
    void importUserFromCsv(String filePath);
}
