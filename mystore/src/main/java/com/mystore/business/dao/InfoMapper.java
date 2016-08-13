package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Info;

public interface InfoMapper {
	
	public List<Info> getInfoList(Info info);
	
	public Integer getInfoCount(Info info);
	
	public Info getInfoById(Integer id);
	
}
