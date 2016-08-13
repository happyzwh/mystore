package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.AdvResourceModule;

public interface AdvResourceModuleMapper {
	
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Map<String,Object> map);

}
