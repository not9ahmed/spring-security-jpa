package com.notahmed.springsecurityjpa.repository;

import com.notahmed.springsecurityjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //Jpa will create the implementaion
    Optional<User> findUserByUsername(String username);
}
