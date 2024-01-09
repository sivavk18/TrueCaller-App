package com.truecaller.controller;


import com.truecaller.dto.ApiResponseDTO;
import com.truecaller.dto.UserDTO;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity <UserDTO> createStudent(@RequestBody @Valid UserDTO userDTO) {
        UserDTO student= userService.create(userDTO);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO>getById(@PathVariable Integer userId) throws UserNotFoundException {
        UserDTO userDTO = userService.getById(userId);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping("/user")
    public List<UserDTO> listUser(){
        return userService.findAll();
    }


    @PutMapping("/user/{id}")
    public ResponseEntity <UserDTO> update(@PathVariable Integer id , @RequestBody @Valid UserDTO userDTO)throws UserNotFoundException {
        UserDTO student= userService.update(id, userDTO);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ApiResponseDTO deleteById(@PathVariable Integer userId) throws UserNotFoundException {
        userService.deleteById(userId);
        return new ApiResponseDTO("user successfully deleted",true, HttpStatus.OK);
    }


}
