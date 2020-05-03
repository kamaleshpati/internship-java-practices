package com.cognitree.taskmanager.springmvc.dao;

import com.cognitree.taskmanager.springmvc.model.Login;
import com.cognitree.taskmanager.springmvc.model.User;

public interface UserDao {

    int register(User user);
    User validateUser(Login login);

}