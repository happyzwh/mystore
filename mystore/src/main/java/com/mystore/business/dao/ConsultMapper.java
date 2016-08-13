package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Consult;

public interface ConsultMapper {
	
	/**
	 * 增加商品资询
	 * 
	 * */
	public void addConsult(Consult consult);
	
	/**
	 * 根据主键查询商品咨询数量
	 * 
	 * */
	public Integer getProConsultCountByProId(Consult consult);
	
	/**
	 * 根据主键查询商品咨询
	 * 
	 * */
	public List<Consult> getProConsultByProId(Consult consult);
	
	/**
	 * 根据主键查询商品咨询统计
	 * 
	 * */
	public Map<String,Object> getConsultStatisByProId(Integer proId);

}
