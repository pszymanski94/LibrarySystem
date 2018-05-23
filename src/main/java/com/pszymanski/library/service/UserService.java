package com.pszymanski.library.service;

import com.pszymanski.library.dto.UserDTO;
import com.pszymanski.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOne(Integer id);

    List<User> search(String value, String options);

    User dtoToEntity(UserDTO user);

    void save(UserDTO user);

    void delete(Integer id);
}
