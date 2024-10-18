package com.comitialsign.First_RESTful_API.domain.repository;

import com.comitialsign.First_RESTful_API.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
