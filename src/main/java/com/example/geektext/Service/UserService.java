package com.example.geektext.Service;

import com.example.geektext.Entity.User;
import com.example.geektext.Repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
