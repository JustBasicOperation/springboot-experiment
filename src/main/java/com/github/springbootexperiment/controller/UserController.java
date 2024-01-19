package com.github.springbootexperiment.controller;

import com.github.springbootexperiment.dto.UserDTO;
import com.github.springbootexperiment.entity.UserEntity;
import com.github.springbootexperiment.repo.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Resource
    UserRepository userRepository;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity GetUser(@RequestParam(value = "id") Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        user.ifPresent(System.out::println);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity CreateUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId((long) userDTO.getId());
        userEntity.setName(userDTO.getName());
        userRepository.save(userEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
