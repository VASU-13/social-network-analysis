package com.vasu.sna.service;

import com.vasu.sna.entity.User;
import com.vasu.sna.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceImpl {

    public User addUser(User user);

    public void removeUser(Integer userId) throws UserNotFoundException;

    public List<User> getAllUsers();

}
