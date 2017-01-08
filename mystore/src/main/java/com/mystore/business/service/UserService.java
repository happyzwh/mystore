package com.mystore.business.service;

import com.mystore.business.dto.User;

public interface UserService {
	//注册增加新用户
	public int addUser(User user) throws Exception;
	//用户登录
	public User getUserByAccount(String account);
	//更新用户
	public int updateUser(User user);
	
}
