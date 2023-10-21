package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.repo.UserRepo;

public class ApplicationUserServiceImpl {

    private final UserRepo  userRepo;


    public ApplicationUserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;

    }
}
