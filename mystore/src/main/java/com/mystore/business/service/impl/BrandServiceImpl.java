package com.mystore.business.service.impl;

import java.util.ArrayList;
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

	@Override
	public List<Brand> getAllParentBrandById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Brand> list = new ArrayList<Brand>();
	    if(id != null){
	    	getBrandById(list,id);
	    }
		return list;
	}

	private void getBrandById(List<Brand> list,Integer id){
    	Brand brand = brandMapper.getBrandById(id);
    	if(brand != null){
	    	if(brand.getPid() != null){
	    		getBrandById(list,brand.getPid());
	    	}
	    	list.add(brand);
    	}
    }
	
	@Override
	public List<Brand> getAllSonBrandById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Brand> list = new ArrayList<Brand>();
	    if(id != null){
	    	Brand brand = brandMapper.getBrandById(id);
	    	if(brand != null){
	    		getSonBrandById(list,id);
	    		list.add(brand);
	    	}
	    }
		return list;
	}
	
	private void getSonBrandById(List<Brand> list,Integer id){
	    List<Brand> brands = brandMapper.selectBrandListByPid(id);
	    if(brands != null && brands.size() > 0){
	    	for(Brand bran:brands){
	    		getSonBrandById(list,bran.getId());
	    		list.add(bran);
	    	}
	    }	
    }

	@Override
	public List<Brand> selectAllBrandListByCateId(Integer cateId) {
		// TODO Auto-generated method stub
		return brandMapper.selectAllBrandListByCateId(cateId);
	}

}
