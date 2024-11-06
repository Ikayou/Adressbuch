package com.example.Adressbuch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameAndTel(String name, String tel);
}
