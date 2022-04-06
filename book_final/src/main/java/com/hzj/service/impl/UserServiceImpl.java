package com.hzj.service.impl;

import com.hzj.dao.UserDao;
import com.hzj.dao.impl.UserDaoImpl;
import com.hzj.pojo.User;
import com.hzj.service.UserService;
/**
 * @author hzj
 * @create 2022-02-25 11:58
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {

        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username) == null){

            return false;
        }
        return true;
    }
}
