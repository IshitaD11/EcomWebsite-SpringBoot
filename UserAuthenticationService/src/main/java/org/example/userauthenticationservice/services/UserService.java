package org.example.userauthenticationservice.services;

import org.example.userauthenticationservice.exceptions.UserNotFoundException;
import org.example.userauthenticationservice.models.User;
import org.example.userauthenticationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return user;
    }
}
