package com.notahmed.springsecurityjpa.security;

import com.notahmed.springsecurityjpa.model.MyUserDetails;
import com.notahmed.springsecurityjpa.model.User;
import com.notahmed.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//Implementing the spring security UserDetailsService
@Service
public class MyUserDetailsService implements UserDetailsService {


    // depenency injection the old way
    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // this can connect to any other source to retrieve the user information given the username


        // hard coded user
//        return new MyUserDetails(username);


        // calling the jpa method
        // userRepository.findByUsername()
        // then it should return the user instance

        // gets user from db but with type User
        Optional<User> user = userRepository.findUserByUsername(username);


        // if user not found throw this
        user.orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));


        // return UserDetails
        MyUserDetails userDetails = user.map(MyUserDetails::new).get();

        return userDetails;

    }
}
