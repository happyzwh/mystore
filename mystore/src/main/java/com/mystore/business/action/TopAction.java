package com.mystore.business.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.AdvImg;
import com.mystore.business.pojo.AdvMap;
import com.mystore.business.service.AdvertiseService;

@Controller("topAction")
@Scope("prototype")
public class TopAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	private AdvImg advImg;
	
	public String top(){

		List<AdvImg> list = advertiseService.getAdvImgListByPid(null,AdvMap.INDEXTOPADV.getBh());		
		
		if(list != null && list.size() > 0){
			advImg = list.get(0);
		}
		
		return "top";
	}

	public AdvImg getAdvImg() {
		return advImg;
	}

	public void setAdvImg(AdvImg advImg) {
		this.advImg = advImg;
	}

}
