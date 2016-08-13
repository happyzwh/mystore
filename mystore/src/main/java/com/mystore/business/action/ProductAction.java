package com.mystore.business.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.service.ProductService;

@Controller("productAction")
@Scope("prototype")
public class ProductAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Product product;
	
	private List<ProImg> proImgs;
	
	private List<ProAttr> proAttrs;
	
	private ProPrice proPrice;
	
	private ProInfo proInfo;
	
	private List<Map<String,Object>> proAttrMaps;
	
	@Autowired
	private ProductService productService;
	
	public String detail(){
		
		if(id == null){
			return "detail";
		}
		
		product = productService.getProById(id);
		
		if(product == null){
			return "detail";
		}
		
		proAttrMaps = productService.getProAttrMapByProId(id);
		
		proImgs = productService.getProImgListByProId(id);
		
		proInfo = productService.getProInfoByProId(id);
		
		proPrice = productService.getProPriceByProId(id);
		
		return "detail";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProImg> getProImgs() {
		return proImgs;
	}

	public void setProImgs(List<ProImg> proImgs) {
		this.proImgs = proImgs;
	}

	public List<ProAttr> getProAttrs() {
		return proAttrs;
	}

	public void setProAttrs(List<ProAttr> proAttrs) {
		this.proAttrs = proAttrs;
	}

	public ProPrice getProPrice() {
		return proPrice;
	}

	public void setProPrice(ProPrice proPrice) {
		this.proPrice = proPrice;
	}

	public ProInfo getProInfo() {
		return proInfo;
	}

	public void setProInfo(ProInfo proInfo) {
		this.proInfo = proInfo;
	}

	public List<Map<String, Object>> getProAttrMaps() {
		return proAttrMaps;
	}

	public void setProAttrMaps(List<Map<String, Object>> proAttrMaps) {
		this.proAttrMaps = proAttrMaps;
	}

	
}
