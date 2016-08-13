package com.mystore.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.ConsultMapper;
import com.mystore.business.dto.Consult;
import com.mystore.business.service.ConsultService;

@Service("consultService")
public class ConsultServiceImpl implements ConsultService{
	
	@Autowired
	private ConsultMapper consultMapper;

	@Override
	public void addConsult(Consult consult) {
		// TODO Auto-generated method stub
		consultMapper.addConsult(consult);
	}

	@Override
	public Pager<Consult> getProConsultByProId(Integer proId,String type,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		Consult consult = new Consult();
		consult.setId_pro(proId);
		consult.setType(type);
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			consult.setPageIndex(pageIndex);
			consult.setPageSize(pageSize);
		}
		Pager<Consult> pager = new Pager<Consult>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = consultMapper.getProConsultCountByProId(consult);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Consult> list = consultMapper.getProConsultByProId(consult);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public Map<String, Object> getConsultStatisByProId(Integer proId) {
		// TODO Auto-generated method stub
		return consultMapper.getConsultStatisByProId(proId);
	}

}
