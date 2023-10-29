package com.example.soloblog.repository;

import com.example.soloblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<Object> findByNickname(String nickname);

    Optional<Object> findByEmail(String email);
}
