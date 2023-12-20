package org.example.service;

import org.example.dto.UserDTO;
import org.example.entity.User;

public interface UserService {
    User create(UserDTO userDTO);
    User edit(Integer id,UserDTO userDTO);
    boolean checkingUsernameAndPassword(String username, String password);
    boolean changePassword(String username, String password, String newPassword);
}
