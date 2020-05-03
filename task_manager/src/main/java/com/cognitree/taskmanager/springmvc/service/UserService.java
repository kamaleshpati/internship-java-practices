package com.cognitree.taskmanager.springmvc.service;

import com.cognitree.taskmanager.springmvc.model.Login;
import com.cognitree.taskmanager.springmvc.model.User;

public interface UserService {

  void register(User user);
  User validateUser(Login login);
}
