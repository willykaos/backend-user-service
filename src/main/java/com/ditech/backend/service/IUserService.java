package com.ditech.backend.service;

import com.ditech.backend.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void saveUser(UserDTO user);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    void deleteUser(Long id);
}
