package com.caioprogramador.workshopmongo.services.impl;

import com.caioprogramador.workshopmongo.dto.UserDTO;
import com.caioprogramador.workshopmongo.entity.User;
import com.caioprogramador.workshopmongo.repository.UserRepository;
import com.caioprogramador.workshopmongo.services.UserService;
import com.caioprogramador.workshopmongo.services.exception.ObjectNotFoundException;
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

    @Override
    public User findById(String id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        return user;
    }

    @Override
    public User insert(UserDTO obj) {
        return repository.insert(fromDTO(obj));
    }

    @Override
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public User update(UserDTO obj) {
        User newObj = repository.findById(obj.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        updateData(newObj,obj);
        return repository.save(newObj);
    }

    private void updateData(User newObj, UserDTO obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    private User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
