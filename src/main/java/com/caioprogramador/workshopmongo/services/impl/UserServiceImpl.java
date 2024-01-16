package com.caioprogramador.workshopmongo.services.impl;

import com.caioprogramador.workshopmongo.entity.User;
import com.caioprogramador.workshopmongo.repository.UserRepository;
import com.caioprogramador.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
