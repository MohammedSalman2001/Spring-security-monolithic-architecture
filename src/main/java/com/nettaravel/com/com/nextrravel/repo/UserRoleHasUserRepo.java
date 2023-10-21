package com.nettaravel.com.com.nextrravel.repo;

import com.nettaravel.com.com.nextrravel.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleHasUserRepo extends JpaRepository<UserRole,Long> {
}
