package com.ditech.backend.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.ditech.backend.dto.UserDTO;
import com.ditech.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testSaveUser() throws Exception {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Willy");
        userDTO.setEmail("willy.londono@grupoditech.es");

        String userJson = objectMapper.writeValueAsString(userDTO);

        // Act & Assert
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());

        verify(userService).saveUser(any(UserDTO.class));
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        UserDTO user1 = new UserDTO();

        user1.setUsername("Willy");
        user1.setEmail("willy.londono@grupoditech.es");

        UserDTO user2 = new UserDTO();
        user2.setUsername("Felipe");
        user2.setEmail("felipe.siachoque@grupoditech.es");

        List<UserDTO> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("Willy"))
                .andExpect(jsonPath("$[0].email").value("willy.londono@grupoditech.es"))
                .andExpect(jsonPath("$[1].username").value("Felipe"))
                .andExpect(jsonPath("$[1].email").value("felipe.siachoque@grupoditech.es"));


        verify(userService).getAllUsers();
    }
}
