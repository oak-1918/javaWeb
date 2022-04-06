package com.hzj.test;

import com.hzj.dao.UserDao;
import com.hzj.dao.impl.UserDaoImpl;
import com.hzj.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-03-03 23:09
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null ){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {

        if ( userDao.queryUserByUsernameAndPassword("admin","admin") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(new User(null,"hzj168", "123456", "hzj168@qq.com")));
    }
}