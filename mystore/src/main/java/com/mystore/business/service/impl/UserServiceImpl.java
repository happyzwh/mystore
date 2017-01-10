package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.UserMapper;
import com.mystore.business.dto.User;
import com.mystore.business.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper UserMapper;
	
	@Override
	public int addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return UserMapper.addUser(user);
	}

	@Override
	public User getUserByAccount(String account) {
		// TODO Auto-generated method stub
		return UserMapper.getUserByAccount(account);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return UserMapper.updateUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return UserMapper.getUserById(id);
	}

}
