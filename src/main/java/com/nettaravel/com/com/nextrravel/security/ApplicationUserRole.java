package com.nettaravel.com.com.nextrravel.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    DOCTOR(Sets.newHashSet()),
    USER(Sets.newHashSet());


    private final Set<ApplicationUserPermission> permission;

     ApplicationUserRole(Set<ApplicationUserPermission> permission){
        this.permission=permission;
    }

    public Set<ApplicationUserPermission> getPermission(){
         return permission;
    }


    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermission()
                .stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
                collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
