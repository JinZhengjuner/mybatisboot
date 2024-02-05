package com.jzj.demo.service;

import com.jzj.demo.entity.User;
import com.jzj.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }


    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    public int Update(User user) {
        return userMapper.update(user);
    }

    public User save(User user) {
        int save = userMapper.save(user);
        return user;
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
