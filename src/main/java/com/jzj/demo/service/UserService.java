package com.jzj.demo.service;

import com.jzj.demo.entity.User;
import com.jzj.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

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

        transactionTemplate.execute(transactionStatus -> {
            try {
                int save = userMapper.save(user);
                int save2 = userMapper.saveUser1(user);
                int i = 1/0;
            }catch (Exception e){
                log.info("fail");
                transactionStatus.setRollbackOnly();
                throw e;
            }
            log.info("success");
            return null;
        });




        return user;
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
