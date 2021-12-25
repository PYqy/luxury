package com.yqy.dao;
//用户持久层接口

import com.yqy.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
