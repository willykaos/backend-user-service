package com.ditech.backend.dto;

import com.ditech.backend.model.UserDitech;

public class UserDTO {


    private String username;
    private String email;
    private boolean active;

    public UserDTO(){}

    public UserDTO(UserDitech user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.active = user.isActive();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
