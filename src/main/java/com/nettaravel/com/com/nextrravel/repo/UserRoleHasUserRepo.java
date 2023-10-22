package com.nettaravel.com.com.nextrravel.repo;

import com.nettaravel.com.com.nextrravel.entity.UserRole;
import com.nettaravel.com.com.nextrravel.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser,Long> {
    @Query(value = "SELECT * FROM  user_role_has_user WHERE user_id=?1",nativeQuery = true)
    List<UserRoleHasUser> findByUserId(long id);
}
