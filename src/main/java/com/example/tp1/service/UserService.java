package com.example.tp1.service;




import com.example.tp1.entities.User;
import com.example.tp1.entities.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
