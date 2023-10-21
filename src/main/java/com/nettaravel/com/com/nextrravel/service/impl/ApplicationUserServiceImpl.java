package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.repo.UserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleHasUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepo  userRepo;

    public final UserRoleHasUserRepo userRoleHasUserRepo;


    public ApplicationUserServiceImpl(UserRepo userRepo, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
