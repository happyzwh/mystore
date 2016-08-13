package com.mystore.business.service;

import java.util.Map;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Consult;

public interface ConsultService {
	
	/**
	 * 增加商品资询
	 * 
	 * */
	public void addConsult(Consult consult);
	
	/**
	 * 根据主键查询商品咨询
	 * 
	 * */
	public Pager<Consult> getProConsultByProId(Integer proId,String type,Integer pageNum,Integer pageSize);
	
	/**
	 * 根据主键查询商品咨询统计
	 * 
	 * */
	public Map<String,Object> getConsultStatisByProId(Integer proId);

}
