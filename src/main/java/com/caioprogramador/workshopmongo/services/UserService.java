package com.caioprogramador.workshopmongo.services;

import com.caioprogramador.workshopmongo.dto.UserDTO;
import com.caioprogramador.workshopmongo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User insert(User obj);

    User fromDTO(UserDTO userDTO);
}
