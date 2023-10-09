package com.linhv.scheduling.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.linhv.scheduling.model.Account;
import com.linhv.scheduling.model.Post;
import com.linhv.scheduling.model.User;
import com.linhv.scheduling.repository.AccountRepository;
import com.linhv.scheduling.service.AccountService;
import com.linhv.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("userDetailsService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Account getById(Long id) {
        return accountRepo.findById(id).get();
    }

    @Override
    public boolean authAccount(Long id, String password) {
        try {
            Account account = this.getById(id);
            return this.encoder.matches(password, account.getPassword());
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Override
    public Account newAccount(User user) {
        Account account = new Account();

        account.setRole(user.getRole());
        account.setStatus(Account.ACTIVE);
        account.setImage(Account.DEFAULT_IMAGE);

        account.setPassword(this.encoder.encode(Account.DEFAULT_PASSWORD));

        account.setUser(user);

        return this.accountRepo.save(account);
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        try {
            Account curAcc = this.getById(id);
            curAcc.setPassword(this.encoder.encode(password));
            this.accountRepo.save(curAcc);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Override
    public boolean updateImage(Long id, MultipartFile imageFile) {
        try {
            Account curAcc = this.getById(id);
            Map res = this.cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            curAcc.setImage(res.get("secure_url").toString());
            return true;
        } catch (NoSuchElementException ex) {
            Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, "Không tồn tại account với id này", ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, "IO Exception", ex);
            return false;
        }
    }

    @Override
    public void lockAccount(Long id) {
        Account account = this.getById(id);
        if (Objects.equals(account.getStatus(), Account.ACTIVE))
            account.setStatus(Account.LOCK);
    }

    @Override
    public void unlockAccount(Long id) {
        Account account = this.getById(id);
        if (Objects.equals(account.getStatus(), Account.LOCK))
            account.setStatus(Account.ACTIVE);
    }

    @Override
    public boolean deleteAccount(Long id) {
        try {
            Account account = this.getById(id);
            accountRepo.delete(account);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = this.getById(Long.parseLong(username));
        if (user == null)
            throw new UsernameNotFoundException(String.format("Không tồn tại người dùng với ID %s!", username));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(), user.getPassword(), authorities
        );
    }
}
