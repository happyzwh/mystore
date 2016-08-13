package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.AdvProModule;

public interface AdvProModuleMapper {
	
	public List<AdvProModule> getAdvProModuleListByPid(Map<String,Object> map);
	
}
