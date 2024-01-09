package com.truecaller.controller;

import com.truecaller.dto.UserDTO;
import com.truecaller.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.truecaller.builder.MocksAndAssertions.BuildStudentDTO;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


    private static final String LOCALHOST = "http://localhost:";
    private static final String CONTEXT_PATH = "/api/v1";
    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @LocalServerPort
    private int port;


    @Test
    void whenPOSTIsCalledThenAStudentIsCreated() {
        // given
        UserDTO userDTO = BuildStudentDTO();
        // then
        String Url = LOCALHOST + port + CONTEXT_PATH + "/student";
        HttpEntity<UserDTO> requestEntity =new HttpEntity<>(userDTO);
        ResponseEntity<UserDTO> response = restTemplate.exchange(Url,HttpMethod.POST,  requestEntity , UserDTO.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        UserDTO student = response.getBody();
        Assertions.assertNotNull(student);
        assertEquals(student.getFirstName(),"sk");
        assertEquals(student.getLastName(),"p");
    }

    @Test
    void whenGETListStudentIsCalled() {
        UserDTO userDTO = BuildStudentDTO();
        // then
        String Url = LOCALHOST + port + CONTEXT_PATH + "/student";
        HttpEntity<UserDTO> requestEntity =new HttpEntity<>(userDTO);
        ResponseEntity<List> response = restTemplate.getForEntity(Url,List.class);
        List<UserDTO> student = response.getBody();
        Assertions.assertNotNull(student);
    }
    @Test
    void whenUpdateStudent(){
        int id = 1;
        String Url = LOCALHOST + port + CONTEXT_PATH;
        UserDTO userDTO = restTemplate.getForObject(Url + "/student/"+id , UserDTO.class);
        userDTO.setFirstName("skp");
        userDTO.setLastName("k");
        userDTO.setEmail("sk@gmail.com");
        restTemplate.put(Url, userDTO);
        UserDTO updateStudent= restTemplate.getForObject(Url + "/student/"+id , UserDTO.class);
        assertNotNull(updateStudent);
    }

    @Test
    void whenDeleteStudent(){
        int id = 3;
        String Url = LOCALHOST + port + CONTEXT_PATH;
        UserDTO userDTO = restTemplate.getForObject(Url + "/student/"+id , UserDTO.class);
        assertNotNull(userDTO);
        restTemplate.delete(Url + "/student/"+id);
        try {
            userDTO = restTemplate.getForObject(Url + "/student/"+id , UserDTO.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
