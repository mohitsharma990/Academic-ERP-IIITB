package com.mohit.academicerp.repositories;

import com.mohit.academicerp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM users u JOIN u.employee e WHERE u.password = ?2 AND e.email = ?1")
    User validateUser(String email, String password);

    @Query("SELECT u FROM users u JOIN u.employee e WHERE e.email = ?1")
    User findUserByEmail(String email);
}