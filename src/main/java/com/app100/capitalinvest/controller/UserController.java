package com.app100.capitalinvest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app100.capitalinvest.dtos.CreateUserDto;
import com.app100.capitalinvest.dtos.UpdatedUserDto;
import com.app100.capitalinvest.entity.User;
import com.app100.capitalinvest.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto creatreUserDto){
        var userId = userService.createUser(creatreUserDto);

        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        var user = userService.getById(userId);
        // return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                            @RequestBody UpdatedUserDto updatedUserDto){
        userService.updatedUserById(userId, updatedUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId){
        userService.deleteUserById(userId);
        // return ResponseEntity.ok(null);
        return ResponseEntity.noContent().build();
    }
    
}
