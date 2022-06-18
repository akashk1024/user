package com.greatlearning.microservice.controller;

import com.greatlearning.microservice.dto.UserDto;
import com.greatlearning.microservice.entity.Users;
import com.greatlearning.microservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/userlist")
    public ResponseEntity<List<Users>> findAll() {
        List<Users> registeredUserList = userService.findAll();
        return new ResponseEntity<>(registeredUserList, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(@RequestParam String userName,
                                        @RequestParam String password,
                                        @RequestParam boolean enabled) {
        UserDto userDto = ControllerUtils.buildUserDto(userName, password, enabled);
        Users savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/userDetails")
    public ResponseEntity<UserDto> findByUserName(@RequestParam String userName) {
        try {
            UserDto userDto = userService.findByUserName(userName);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


}
