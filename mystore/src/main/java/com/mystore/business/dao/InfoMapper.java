package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;

public interface InfoMapper {
	
	public List<Info> getInfoList(Info info);
	
	public Integer getInfoCount(Info info);
	
	public Info getInfoById(Integer id);
	
	public InfoCate getInfoCateByBh(String bh);
	
	public List<InfoCate> getInfoCateByPid(Integer pid);
	
	public List<Info> getInfoByPid(Integer pid);
	
	public List<Integer> getAllInfoId();
}
