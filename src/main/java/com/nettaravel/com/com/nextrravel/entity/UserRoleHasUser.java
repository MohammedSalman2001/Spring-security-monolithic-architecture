package com.nettaravel.com.com.nextrravel.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
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


    public UserRoleHasUser(User user, UserRole userRole) {
        this.user = user;
        this.userRole = userRole;
    }
}
