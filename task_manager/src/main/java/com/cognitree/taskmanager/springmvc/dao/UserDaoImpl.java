package com.cognitree.taskmanager.springmvc.dao;

import com.cognitree.taskmanager.springmvc.model.Login;
import com.cognitree.taskmanager.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int register(User user) {

        String sql = "insert into users values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
                user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
        return 0;
    }

    public User validateUser(Login login) {
        String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
                + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }
}
