package me.gom.springbootdeveloper.repository;

import me.gom.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    }
