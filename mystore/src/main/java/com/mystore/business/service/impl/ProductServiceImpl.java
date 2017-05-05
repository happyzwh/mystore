package com.mystore.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.ProductMapper;
import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Product getProById(Integer id) {
		// TODO Auto-generated method stub
		return productMapper.getProById(id);
	}

	@Override
	public List<ProAttr> getProAttrByProId(Integer proId) {
		// TODO Auto-generated method stub
		return productMapper.getProAttrByProId(proId);
	}
	
	@Override
	public List<Map<String,Object>> getProAttrMapByProId(Integer proId){
		// TODO Auto-generated method stub
		return productMapper.getProAttrMapByProId(proId);
	}

	@Override
	public List<ProImg> getProImgListByProId(Integer proId) {
		// TODO Auto-generated method stub
		return productMapper.getProImgListByProId(proId);
	}

	@Override
	public ProInfo getProInfoByProId(Integer proId) {
		// TODO Auto-generated method stub
		return productMapper.getProInfoByProId(proId);
	}

	@Override
	public ProPrice getProPriceByProId(Integer proId) {
		// TODO Auto-generated method stub
		return productMapper.getProPriceByProId(proId);
	}

	@Override
	public List<Map<String, Object>> getProAttrMapByCateId(Integer cateId) {
		// TODO Auto-generated method stub
		return productMapper.getProAttrMapBycateId(cateId);
	}

	@Override
	public List<Integer> getAllProId() {
		// TODO Auto-generated method stub
		return productMapper.getAllProId();
	}

}
