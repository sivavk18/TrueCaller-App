package com.truecaller.service;


import com.truecaller.dto.UserDTO;
import com.truecaller.entity.User;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.mapper.UserMapper;
import com.truecaller.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toModel(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No user by ID: " + userId));
        return userMapper.toDTO(user);
    }

    public void deleteById(Integer id) throws UserNotFoundException {
       User user= userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No user by ID: " + id));
        userRepository.delete(user);
    }

    public UserDTO update(Integer id, UserDTO userDto) throws UserNotFoundException {
        User user1 = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No user by ID: " + id));
        user1.setId(userDto.getId());
        user1.setDateOfBirth(userDto.getDateOfBirth());
        user1.setEmail(userDto.getEmail());
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setPhoneNumber(userDto.getPhoneNumber());
        User updatePro= userRepository.save(user1);
        return userMapper.toDTO(updatePro);
    }

}
