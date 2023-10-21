package com.nettaravel.com.com.nextrravel.service.impl;

import com.nettaravel.com.com.nextrravel.auth.ApplicationUser;
import com.nettaravel.com.com.nextrravel.entity.User;
import com.nettaravel.com.com.nextrravel.entity.UserRoleHasUser;
import com.nettaravel.com.com.nextrravel.repo.UserRepo;
import com.nettaravel.com.com.nextrravel.repo.UserRoleHasUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nettaravel.com.com.nextrravel.security.ApplicationUserRole.ADMIN;
import static com.nettaravel.com.com.nextrravel.security.ApplicationUserRole.DOCTOR;

public class ApplicationUserServiceImpl implements UserDetailsService {


    private final UserRepo  userRepo;

    public final UserRoleHasUserRepo userRoleHasUserRepo;


    public ApplicationUserServiceImpl(UserRepo userRepo, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User selectUser=  userRepo.findByUserName(username);
      if(selectUser==null){
          throw new UsernameNotFoundException(String.format("Username %s not found",username));
      }
    List<UserRoleHasUser> useRole= userRoleHasUserRepo.findByUserId(selectUser.getId());
      Set<SimpleGrantedAuthority> grantedAuthority=new HashSet<>();


        for(UserRoleHasUser userRoles:useRole){
            if(userRoles.getUserRole().getRoleName().equals("ADMIN")){
                grantedAuthority.addAll(ADMIN.grantedAuthorities());
            }

            if(userRoles.getUserRole().getRoleName().equals("DOCTOR")){
                grantedAuthority.addAll(DOCTOR.grantedAuthorities());
            }

        }

        return new ApplicationUser(
                grantedAuthority,
                selectUser.getPassword(),
                selectUser.getEmail(),
                selectUser.isAccountNonExpired(),
                selectUser.isAccountNonLock(),
                selectUser.isCredentialsNoExpired(),
                selectUser.isEnable());
    }
}
