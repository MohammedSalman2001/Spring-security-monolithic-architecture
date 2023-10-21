package com.nettaravel.com.com.nextrravel.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserKey implements Serializable {

    @Column(name = "user_id")
    private long user;

    @Column(name = "user_role")
    private long userRole;
}
