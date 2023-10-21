package com.nettaravel.com.com.nextrravel.repo;

import com.nettaravel.com.com.nextrravel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
}
