package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.UserStatisticsMapper;
import com.mystore.business.dto.UserStatistics;
import com.mystore.business.service.UserStatisticsService;

@Service("userStatisticsService")
public class UserStatisticsServiceImpl implements UserStatisticsService{
	
	@Autowired
	private UserStatisticsMapper userStatisticsMapper;

	@Override
	public UserStatistics getUserStatisticsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userStatisticsMapper.getUserStatisticsByUserId(userId);
	}

}
