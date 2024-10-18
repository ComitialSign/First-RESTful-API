package com.comitialsign.First_RESTful_API.service;

import com.comitialsign.First_RESTful_API.domain.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    User findUserById(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
