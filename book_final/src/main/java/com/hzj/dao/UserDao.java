package com.hzj.dao;

import com.hzj.pojo.User;

/**
 * @author hzj
 * @create 2022-02-25 11:30
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，则说明没有这个用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password密码
     * @return 如果返回null，则说明用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他是执行sql语句操作影响的行数
     */
    public int saveUser(User user);





}
