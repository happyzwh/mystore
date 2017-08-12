package com.mystore.business.service;

import com.mystore.business.dto.UserStatistics;

public interface UserStatisticsService {
	
	
	/**
	 * 查询用户统计数据
	 * 
	 * */
	public UserStatistics getUserStatisticsByUserId(Integer userId);

}
