package com.secure.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.security.model.User;
import com.secure.security.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private /*final*/ UserRepository userRepository;
    /*public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }*/
    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }
    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
}
