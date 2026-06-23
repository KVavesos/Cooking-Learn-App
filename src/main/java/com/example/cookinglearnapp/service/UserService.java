package com.example.cookinglearnapp.service;

import com.example.cookinglearnapp.model.UserProgress;
import com.example.cookinglearnapp.repository.UserRepository;
import com.example.cookinglearnapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        Optional<User> user =
                userRepository.findByUsernameAndPassword(username, password);

        return user.isPresent();
    }

    public User findUser(String username, String password) {

        Optional<User> user =
                userRepository.findByUsernameAndPassword(username, password);

        return user.orElse(null);
    }


    public void saveUser(User user) {

        userRepository.save(user);

    }

}
