package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.dto.request.RequestUserDto;
import com.nettaravel.com.com.nextrravel.entity.User;
import com.nettaravel.com.com.nextrravel.entity.UserRole;
import com.nettaravel.com.com.nextrravel.entity.UserRoleHasUser;
import com.nettaravel.com.com.nextrravel.repo.UserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleHasUserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleRepo;
import com.nettaravel.com.com.nextrravel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    private final UserRoleHasUserRepo userRoleHasUserRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserRoleHasUserRepo userRoleHasUserRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signup(RequestUserDto dto) {
        UserRole userRole;
        if (dto.getId() == 1) {
            userRole = userRoleRepo.findUserRoleByName("ADMIN");
        } else {
            userRole = userRoleRepo.findUserRoleByName("DOCTOR");
        }
        if (userRole == null) {
            throw new RuntimeException("User Role Not found");
        }

        User user = new User(
                dto.getId(),
                dto.fullName, dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                true, true, true, true, null
        );

        UserRoleHasUser data = new UserRoleHasUser(user, userRole);
        userRepo.save(user);
        userRoleHasUserRepo.save(data);


    }
}
