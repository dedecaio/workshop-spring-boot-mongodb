package com.caioprogramador.workshopmongo.resources;

import com.caioprogramador.workshopmongo.dto.UserDTO;
import com.caioprogramador.workshopmongo.entity.User;
import com.caioprogramador.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        UserDTO userDTO = new UserDTO(obj);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
        User user = service.fromDTO(userDTO);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
