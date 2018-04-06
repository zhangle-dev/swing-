package com.orderfood.mapper;

import java.util.List;

import com.orderfood.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> findUserByUsernameAndPassword(User user);

	List<User> findUsers();
}