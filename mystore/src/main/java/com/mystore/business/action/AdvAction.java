package com.mystore.business.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.AdvImg;
import com.mystore.business.pojo.AdvMap;
import com.mystore.business.service.AdvertiseService;

@Controller("advAction")
@Scope("prototype")
public class AdvAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	public List<AdvImg> getTopAdv(){

		return advertiseService.getAdvImgListByPid(null,AdvMap.INDEXTOPADV.getBh());		
		
	}

}
