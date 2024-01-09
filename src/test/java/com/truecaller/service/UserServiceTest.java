package com.truecaller.service;

import com.truecaller.dto.UserDTO;
import com.truecaller.entity.User;
import com.truecaller.exception.UserAlreadyRegisteredException;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.mapper.UserMapper;
import com.truecaller.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.truecaller.builder.MocksAndAssertions.BuildStudentDTO;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @InjectMocks
    private UserService userService;

    @Test
    void whenEmployeeCreated() throws UserAlreadyRegisteredException {
        // given
        UserDTO expectedUserDTO = BuildStudentDTO();
        User expectedSavedUser = userMapper.toModel(expectedUserDTO);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expectedSavedUser);

        // then
        UserDTO createdUserDTO = userService.create(expectedUserDTO);

        assertThat(createdUserDTO.getId(), is(equalTo(expectedUserDTO.getId())));
        assertThat(createdUserDTO.getFirstName(), is(equalTo(expectedUserDTO.getFirstName())));
        assertThat(createdUserDTO.getLastName(), is(equalTo(expectedUserDTO.getLastName())));
        assertThat(createdUserDTO.getEmail(), is(equalTo(expectedUserDTO.getEmail())));
    }


    @Test
    void whenListEmployeeIsCalledThenReturnAListOfEmployee() {
        // given
        UserDTO expectedUserDTO = BuildStudentDTO();
        User expectedUser = userMapper.toModel(expectedUserDTO);

        //when
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(expectedUser));

        //then
        List<UserDTO> foundListUserDTO = userService.findAll();

        assertThat(foundListUserDTO, is(not(empty())));
        assertThat(foundListUserDTO.get(0), is(equalTo(expectedUserDTO)));
    }

    @Test
    void whenListEmployeeIsCalledThenReturnAnEmptyListOfEmployee() {
        //when
        Mockito.when(userRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<UserDTO> foundListUserDTO = userService.findAll();

        assertThat(foundListUserDTO, is(empty()));
    }

    @Test
    void whenAEmployeeShouldBeDeleted() throws UserNotFoundException {
        // given
        UserDTO expectedDeletedUserDTO = BuildStudentDTO();
        User expectedDeletedUser = userMapper.toModel(expectedDeletedUserDTO);

        //when
        Mockito.when(userRepository.findById(expectedDeletedUserDTO.getId())).thenReturn(Optional.of(expectedDeletedUser));
        doNothing().when(userRepository).deleteById(expectedDeletedUserDTO.getId());

        //then
        userService.deleteById(expectedDeletedUserDTO.getId());

        verify(userRepository, times(1)).findById(expectedDeletedUserDTO.getId());
        verify(userRepository, times(1)).deleteById(expectedDeletedUserDTO.getId());
    }
}
