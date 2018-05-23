package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.UserDTO;
import com.pszymanski.library.entity.User;
import com.pszymanski.library.repository.UserRepository;
import com.pszymanski.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.getOne(id);
    }

    public Integer getUserId(String username) {
        User user = userRepository.findByUsername(username);
        return user.getId();
    }

    @Override
    public List<User> search(String value, String option) {
        List<User> searchList = null;
        switch (option) {
            case "1":
                searchList = new ArrayList<>();
                searchList.add(userRepository.getOne(1));
                break;
            case "2":
                searchList = userRepository.findByName(value);
                break;
        }
        return searchList;
    }

    @Override
    public User dtoToEntity(UserDTO object) {

        User user = new User();

        if (object.getId() != null) user.setId(object.getId());
        user.setName(object.getName());
        user.setDateOfBirth(object.getDateOfBirth());
        user.setStreet(object.getStreet());
        user.setCity(object.getCity());
        user.setEmail(object.getEmail());
        user.setPhone(object.getPhone());
        user.setUsername(object.getUsername());
        user.setPassword(passwordEncoder.encode(object.getPassword()));
        user.setRole("ROLE_USER");
        return user;
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(dtoToEntity(user));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
