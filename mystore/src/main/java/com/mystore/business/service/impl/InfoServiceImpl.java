package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.InfoMapper;
import com.mystore.business.dto.Info;
import com.mystore.business.dto.InfoCate;
import com.mystore.business.service.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InfoMapper infoMapper;

	@Override
	public Pager<Info> getInfoList(Info info, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			info.setPageIndex(pageIndex);
			info.setPageSize(pageSize);
		}
		Pager<Info> pager = new Pager<Info>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = infoMapper.getInfoCount(info);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Info> list = infoMapper.getInfoList(info);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public Info getInfoById(Integer id) {
		// TODO Auto-generated method stub
		if(id != null){
			return infoMapper.getInfoById(id);
		}
		return null;
	}

	@Override
	public InfoCate getInfoCateByBh(String bh) {
		// TODO Auto-generated method stub
		return infoMapper.getInfoCateByBh(bh);
	}

	@Override
	public List<InfoCate> getInfoCateByPid(Integer pid) {
		// TODO Auto-generated method stub
		return infoMapper.getInfoCateByPid(pid);
	}

	@Override
	public List<Info> getInfoByPid(Integer pid) {
		// TODO Auto-generated method stub
		return infoMapper.getInfoByPid(pid);
	}

	@Override
	public List<Integer> getAllInfoId() {
		// TODO Auto-generated method stub
		return infoMapper.getAllInfoId();
	}


}
