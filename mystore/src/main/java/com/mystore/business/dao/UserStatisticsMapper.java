package com.mystore.business.dao;

import com.mystore.business.dto.UserStatistics;

public interface UserStatisticsMapper {
	
	/**
	 * 查询用户统计数据
	 * 
	 * */
	public UserStatistics getUserStatisticsByUserId(Integer userId);

}
