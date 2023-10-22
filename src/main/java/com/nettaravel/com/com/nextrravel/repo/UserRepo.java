package com.nettaravel.com.com.nextrravel.repo;

import com.nettaravel.com.com.nextrravel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM  user WHERE email=?", nativeQuery = true)
    User findByUserName(String name);

}
