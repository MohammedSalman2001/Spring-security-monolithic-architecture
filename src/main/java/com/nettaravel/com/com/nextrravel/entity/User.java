package com.nettaravel.com.com.nextrravel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    public long id;
    public String fullName;
    public String email;
    public String password;
    public boolean isAccountNonExpired;
    public boolean isCredentialsNoExpired;
    public boolean isAccountNonLock;
    public boolean isEnable;
}
