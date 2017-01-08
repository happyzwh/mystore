package com.mystore.business.dao;

import com.mystore.business.dto.User;


public interface UserMapper {
	//注册增加新用户
	public int addUser(User user);
	//用户登录
	public User getUserByAccount(String account);
	//更新用户
	public int updateUser(User user);
	
}
