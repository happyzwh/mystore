package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;

public interface InfoService {

	public Pager<Info> getInfoList(Info info,Integer pageNo,Integer pageSize);
	
	public Info getInfoById(Integer id);
	
	public InfoCate getInfoCateByBh(String bh);
	
	public List<InfoCate> getInfoCateByPid(Integer pid);
	
	public List<Info> getInfoByPid(Integer pid);
	
}
