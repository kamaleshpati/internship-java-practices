package com.cognitree.taskmanager.springmvc.service;

import com.cognitree.taskmanager.springmvc.dao.UserDao;
import com.cognitree.taskmanager.springmvc.model.Login;
import com.cognitree.taskmanager.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public void register(User user) {
    userDao.register(user);
  }

  public User validateUser(Login login) {
    return userDao.validateUser(login);
  }

}
