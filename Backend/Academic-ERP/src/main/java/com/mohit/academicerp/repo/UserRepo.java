package com.mohit.academicerp.repo;


import com.mohit.academicerp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}