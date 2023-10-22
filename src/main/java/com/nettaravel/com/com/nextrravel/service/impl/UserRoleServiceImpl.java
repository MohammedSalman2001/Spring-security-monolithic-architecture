package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.entity.UserRole;
import com.nettaravel.com.com.nextrravel.repo.UserRoleRepo;
import com.nettaravel.com.com.nextrravel.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private  UserRoleRepo userRoleRepo;



    @Override
    public void initializer() {
        if (userRoleRepo.count() == 0) {
            UserRole admin = new UserRole(1, "ADMIN", "admin", null);
            UserRole doc = new UserRole(2, "DOCTOR", "doctor", null);
            userRoleRepo.saveAll(List.of(admin, doc));
        }
    }
}
