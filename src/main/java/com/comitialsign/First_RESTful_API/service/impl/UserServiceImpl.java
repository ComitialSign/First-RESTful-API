package com.comitialsign.First_RESTful_API.service.impl;

import com.comitialsign.First_RESTful_API.domain.model.User;
import com.comitialsign.First_RESTful_API.domain.repository.UserRepository;
import com.comitialsign.First_RESTful_API.service.IUserService;
import com.comitialsign.First_RESTful_API.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> user = existingUser(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFoundException();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {
        return existingUser(id).map(userUpdated -> {
            userUpdated.setId(id);
            userUpdated.setName(user.getName());
            userUpdated.setEmail(user.getEmail());
            userUpdated.setAge(user.getAge());
            return userRepository.save(userUpdated);
        }).orElseThrow(NotFoundException::new);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private Optional<User> existingUser(Long id) {
        return userRepository.findById(id);
    }
}
