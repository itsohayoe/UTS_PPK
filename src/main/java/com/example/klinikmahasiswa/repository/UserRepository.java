package com.example.klinikmahasiswa.repository;

import com.example.klinikmahasiswa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

}
