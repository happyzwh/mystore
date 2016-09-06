package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.BrandMapper;
import com.mystore.business.dto.Brand;
import com.mystore.business.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandMapper brandMapper;

	@Override
	public Brand getBrandById(Integer id) {
		// TODO Auto-generated method stub
		return brandMapper.getBrandById(id);
	}

	@Override
	public List<Brand> selectBrandListByCateId(Integer cateId) {
		// TODO Auto-generated method stub
		return brandMapper.selectBrandListByCateId(cateId);
	}

}
