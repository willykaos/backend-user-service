package com.ditech.backend.service;

import com.ditech.backend.dto.UserDTO;
import com.ditech.backend.model.UserDitech;
import com.ditech.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    protected UserRepository userRepository;

    @Override
    public void saveUser(UserDTO userdto) {
        userRepository.save(mappingUserDTOtoUser(userdto));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDitech> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList()); //Se crea un Stream para mapear mas facilmente los campos del DTO
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new);

    }

    @Override
    public void deleteUser(Long id) {
        UserDitech user = userRepository.findById(id).get();
        user.setActive(false);
        userRepository.save(user);
    }

    protected UserDitech mappingUserDTOtoUser(UserDTO userdto){
        UserDitech user = new UserDitech();
        user.setEmail(userdto.getEmail());
        user.setActive(userdto.isActive());
        user.setUsername(userdto.getUsername());
        return user;
    }

}
