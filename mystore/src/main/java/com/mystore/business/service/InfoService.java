package com.mystore.business.service;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Info;

public interface InfoService {

	public Pager<Info> getInfoList(Info info,Integer pageNo,Integer pageSize);
	
	public Info getInfoById(Integer id);
	
}
