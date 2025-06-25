package com.ditech.backend.service;


import com.ditech.backend.dto.UserDTO;
import com.ditech.backend.model.UserDitech;
import com.ditech.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<UserDitech> userCaptor;


    @Test
    void testSaveUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Willy");
        userDTO.setEmail("willy.londono@grupoditech.es");

        UserDitech userDitech = new UserDitech();
        userDitech.setId(1L);
        userDitech.setUsername("John");
        userDitech.setEmail("willy.londono@grupoditech.es");

        doReturn(userDitech).when(userService).mappingUserDTOtoUser(userDTO);
        when(userRepository.save(any(UserDitech.class))).thenReturn(userDitech);

        // Act
        userService.saveUser(userDTO);

        // Assert
        verify(userRepository).save(userCaptor.capture());

        UserDitech savedUser = userCaptor.getValue();
        assertNotNull(savedUser.getId(), "El ID no debería ser nulo");
        assertEquals(1L, savedUser.getId(), "El ID debería ser 1");
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        UserDitech user = new UserDitech();
        user.setId(userId);
        user.setUsername("willy");
        user.setEmail("willy.londono@grupoditech.es");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDTO> result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals("willy", result.get().getUsername());
        assertEquals("willy.londono@grupoditech.es", result.get().getEmail());

        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserByIdNotFoundThrowsException() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDTO> result = userService.getUserById(userId);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository).findById(userId);
    }
}
