package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;

public interface AdvertiseService {
	
	public List<AdvProModule> getAdvProModuleListByPid(Integer pid,String bh);
	
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Integer pid,String bh);
	
	public List<AdvImg> getAdvImgListByPid(Integer pid,String bh);

}
