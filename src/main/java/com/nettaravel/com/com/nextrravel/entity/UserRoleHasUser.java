package com.nettaravel.com.com.nextrravel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class UserRoleHasUser {
    @EmbeddedId
private UserRoleHasUserKey id=new UserRoleHasUserKey();

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @ManyToOne
    @MapsId("userRole")
    @JoinColumn(name = "user_role",nullable = false)
    private UserRole userRole;
}
