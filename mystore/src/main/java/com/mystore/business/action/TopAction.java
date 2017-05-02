package com.mystore.business.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.Category;
import com.mystore.business.pojo.AdvMap;
import com.mystore.business.pojo.CatePojo;
import com.mystore.business.service.AdvertiseService;
import com.mystore.business.service.CategoryService;

@Controller("topAction")
@Scope("prototype")
public class TopAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@Autowired
	private CategoryService categoryService;
	
	private AdvImg advImg;
	
	private List<CatePojo> firstCateList;
	
	private List<List<CatePojo>> secondCateList;
	
	public String top() throws Exception{

		List<AdvImg> list = advertiseService.getAdvImgListByPid(null,AdvMap.INDEXTOPADV.getBh());		
		
		if(list != null && list.size() > 0){
			advImg = list.get(0);
		}
		
		if(redisTemplate.hasKey(Constants.KEY_CACHE_CATE_FIRST) && redisTemplate.hasKey(Constants.KEY_CACHE_CATE_SECOND)){
			firstCateList = (List<CatePojo>)redisTemplate.opsForValue().get(Constants.KEY_CACHE_CATE_FIRST);
			secondCateList = (List<List<CatePojo>>)redisTemplate.opsForValue().get(Constants.KEY_CACHE_CATE_SECOND);
		}else{
			List<Category> cateList = categoryService.getAllSonCategoryById(-1);
			if(cateList != null){
				firstCateList = new ArrayList<CatePojo>();
				secondCateList = new ArrayList<List<CatePojo>>();
				for(Category cate:cateList){
					CatePojo catePojo = new CatePojo();
					catePojo.setId(cate.getId());
					catePojo.setName(cate.getName());
					firstCateList.add(catePojo);
					
					List<CatePojo> secondCateLists = new ArrayList<CatePojo>();
					catePojo.setSons(secondCateLists);
					if(cate.getSons() != null){
						List<CatePojo> secondCateListss = new ArrayList<CatePojo>();
						secondCateList.add(secondCateListss);
						for(Category son:cate.getSons()){
							CatePojo sonCatePojo = new CatePojo();
							sonCatePojo.setId(son.getId());
							sonCatePojo.setName(son.getName());
							secondCateLists.add(sonCatePojo);
							
							CatePojo sonCatePojos = new CatePojo();
							sonCatePojos.setId(son.getId());
							sonCatePojos.setName(son.getName());
							secondCateListss.add(sonCatePojos);
							
							List<CatePojo> thirdCateLists = new ArrayList<CatePojo>();
							sonCatePojos.setSons(thirdCateLists);
							
							if(son.getSons() != null){
								for(Category sson:son.getSons()){
									CatePojo sonSonCatePojo = new CatePojo();
									sonSonCatePojo.setId(sson.getId());
									sonSonCatePojo.setName(sson.getName());
									thirdCateLists.add(sonSonCatePojo);
								}
							}
						}
					}
				}
			}
			if(firstCateList.size() > 0){
				redisTemplate.opsForValue().set(Constants.KEY_CACHE_CATE_FIRST, (Serializable)firstCateList);
				redisTemplate.expire(Constants.KEY_CACHE_CATE_FIRST, Constants.VALUE_TIME_CATE_ALL, TimeUnit.HOURS);	
			}
			if(secondCateList.size() > 0){
				redisTemplate.opsForValue().set(Constants.KEY_CACHE_CATE_SECOND, (Serializable)secondCateList);
				redisTemplate.expire(Constants.KEY_CACHE_CATE_SECOND, Constants.VALUE_TIME_CATE_ALL, TimeUnit.HOURS);	
			}
		}
		return "top";
	}

	public AdvImg getAdvImg() {
		return advImg;
	}

	public void setAdvImg(AdvImg advImg) {
		this.advImg = advImg;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<CatePojo> getFirstCateList() {
		return firstCateList;
	}

	public void setFirstCateList(List<CatePojo> firstCateList) {
		this.firstCateList = firstCateList;
	}

	public List<List<CatePojo>> getSecondCateList() {
		return secondCateList;
	}

	public void setSecondCateList(List<List<CatePojo>> secondCateList) {
		this.secondCateList = secondCateList;
	}


}
