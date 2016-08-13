package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.AdvImg;

public interface AdvImgMapper {
	
	public List<AdvImg> getAdvImgListByPid(Map<String,Object> map);

}
