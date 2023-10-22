package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.dto.request.RequestUserDto;
import com.nettaravel.com.com.nextrravel.entity.User;
import com.nettaravel.com.com.nextrravel.entity.UserRole;
import com.nettaravel.com.com.nextrravel.entity.UserRoleHasUser;
import com.nettaravel.com.com.nextrravel.exceptions.EntryNotFoundException;
import com.nettaravel.com.com.nextrravel.jwt.JwtConfig;
import com.nettaravel.com.com.nextrravel.repo.UserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleHasUserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleRepo;
import com.nettaravel.com.com.nextrravel.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final JwtConfig jwtConfig;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, UserRoleHasUserRepo userRoleHasUserRepo, PasswordEncoder passwordEncoder, JwtConfig jwtConfig) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
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

    @Override
    public boolean verifyUser(String type, String token) {
        String realToken = token.replace(jwtConfig.getTokenPrefix(), "");
        Jwt<Header, Claims> claimsJwt = Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJwt(realToken);
        String userName = claimsJwt.getBody().getSubject();
        User selectUser = userRepo.findByUserName(userName);

        if(selectUser!=null){
            throw new EntryNotFoundException("User name Not found");
        }
        for(UserRoleHasUser roleHasUser:selectUser.getUserRoleHasUser()){
            if(roleHasUser.getUserRole().getRoleName().equals(type)){
                return  true;
            }
        }
        return false;
    }
}
