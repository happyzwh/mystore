package com.mystore.business.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.Category;
import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.CommentService;
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
	
	private Map<String,Object> proCommentStatis;
	
	@Autowired
	private CommentService commentService;
	
	private List<Map<String,Object>> proAttrMaps;
	
	private List<Category> categorys;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	public String detail() throws Exception{
		
		if(id == null){
			return "detail";
		}
		
		product = productService.getProById(id);
		
		if(product == null){
			return "detail";
		}
		
		categorys = categoryService.getAllParentCategoryById(product.getId_cate());
		
		proAttrMaps = productService.getProAttrMapByProId(id);
		
		proImgs = productService.getProImgListByProId(id);
		
		proInfo = productService.getProInfoByProId(id);
		
		proPrice = productService.getProPriceByProId(id);
		
		proCommentStatis = commentService.getCommentStatisByProId(id);
		
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

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public Map<String, Object> getProCommentStatis() {
		return proCommentStatis;
	}

	public void setProCommentStatis(Map<String, Object> proCommentStatis) {
		this.proCommentStatis = proCommentStatis;
	}

	
}
