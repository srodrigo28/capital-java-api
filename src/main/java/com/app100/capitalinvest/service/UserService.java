package com.app100.capitalinvest.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app100.capitalinvest.dtos.CreateUserDto;
import com.app100.capitalinvest.dtos.UpdatedUserDto;
import com.app100.capitalinvest.entity.User;
import com.app100.capitalinvest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UUID createUser(CreateUserDto createUserDto){
        var entity = new User(
            UUID.randomUUID(),
            createUserDto.username(),
            createUserDto.email(),
            createUserDto.password(),
            Instant.now(),
            null
        );
        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }
    
    public Optional<User> getById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void updatedUserById(String userId, UpdatedUserDto uptadatedDTO){
        var id = UUID.fromString(userId);
        var useEntity = userRepository.findById(id);

        if(useEntity.isPresent()){
            var user = useEntity.get();

            if(uptadatedDTO.username() != null){
                user.setUsername(uptadatedDTO.username());
            }
            if(uptadatedDTO.password() != null){
                user.setPassword(uptadatedDTO.password());
            }
            userRepository.save(user);
        }
    }

    public void deleteUserById(String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);
        if(userExists){
            userRepository.deleteById(id);
        }
    }
}
