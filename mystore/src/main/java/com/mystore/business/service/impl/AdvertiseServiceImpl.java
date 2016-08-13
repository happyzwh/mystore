package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.AdvImgMapper;
import com.mystore.business.dao.AdvProModuleMapper;
import com.mystore.business.dao.AdvResourceModuleMapper;
import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;
import com.mystore.business.service.AdvertiseService;

@Service("advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService{

	@Autowired
	private AdvProModuleMapper advProModuleMapper;
	
	@Autowired
	private AdvResourceModuleMapper advResourceModuleMapper;
	
	@Autowired
	private AdvImgMapper advImgMapper;

	@Override
	public List<AdvImg> getAdvImgListByPid(Integer pid,String bh) {
		// TODO Auto-generated method stub
		if(pid != null || StringUtils.isNoneBlank(bh)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pid", pid);
			map.put("bh", bh);
			return advImgMapper.getAdvImgListByPid(map);
		}
		return null;
	}

	@Override
	public List<AdvProModule> getAdvProModuleListByPid(Integer pid,String bh) {
		// TODO Auto-generated method stub
		if(pid != null || StringUtils.isNoneBlank(bh)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pid", pid);
			map.put("bh", bh);
			return advProModuleMapper.getAdvProModuleListByPid(map);
		}
		return null;
	}

	@Override
	public List<AdvResourceModule> getAdvResourceModuleListByPid(Integer pid,String bh) {
		// TODO Auto-generated method stub
		if(pid != null || StringUtils.isNoneBlank(bh)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pid", pid);
			map.put("bh", bh);
			return  advResourceModuleMapper.getAdvResourceModuleListByPid(map);
		}
		return null;
	}
}
