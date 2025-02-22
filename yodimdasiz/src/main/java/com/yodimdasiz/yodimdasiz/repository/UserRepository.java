package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhone(String phone);
}
