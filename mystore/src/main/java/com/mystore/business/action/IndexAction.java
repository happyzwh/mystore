package com.mystore.business.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.AdvImg;
import com.mystore.business.dto.AdvProModule;
import com.mystore.business.dto.AdvResourceModule;
import com.mystore.business.dto.Info;
import com.mystore.business.pojo.AdvMap;
import com.mystore.business.service.AdvertiseService;
import com.mystore.business.service.InfoService;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@Autowired
	private InfoService infoService;
	
	private List<AdvImg> bannerList;
	
	private List<AdvProModule> hotSaleList;
	
	private List<AdvResourceModule> hotSaleTxtList;
	
	private List<Info> noticeList;
	
	private List<AdvImg> hotSaleDownList;
	
	private List<AdvProModule> newUpList;
	
	private List<AdvResourceModule> newUpTxtList;
	
	private List<AdvImg> newUpDownList;
	
	private List<AdvProModule> allLikeList;
	
	public String index(){
		
		
		//banner
		bannerList = advertiseService.getAdvImgListByPid(null,AdvMap.INDEXBANNERADV.getBh());
		
		hotSaleList = advertiseService.getAdvProModuleListByPid(null, AdvMap.INDEXHOTSALEPROMODULE.getBh());
		
		hotSaleTxtList = advertiseService.getAdvResourceModuleListByPid(null, AdvMap.INDEXHOTSALETXTMODULE.getBh());
		
		Info info = new Info();
		info.setBh("notice");
		info.setStatus(1);
		Pager<Info> page = infoService.getInfoList(info, 1, 9);	
		if(page != null && page.getResultList() != null && page.getResultList().size() > 0){
			noticeList = page.getResultList();
		}
		
		hotSaleDownList = advertiseService.getAdvImgListByPid(null,AdvMap.INDEXHOTSALEDOWNADV.getBh());
		
		newUpList = advertiseService.getAdvProModuleListByPid(null, AdvMap.INDEXNEWUPMODULE.getBh());
		
		newUpTxtList = advertiseService.getAdvResourceModuleListByPid(null, AdvMap.INDEXNEWUPTXTMODULE.getBh());
		
		allLikeList = advertiseService.getAdvProModuleListByPid(null, AdvMap.INDEXALLLIKEMODULE.getBh());
		
		newUpDownList = advertiseService.getAdvImgListByPid(null,AdvMap.INDEXNEWUPDOWNADV.getBh());
		
		return "index";
	}

	public List<AdvImg> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<AdvImg> bannerList) {
		this.bannerList = bannerList;
	}

	public List<AdvProModule> getHotSaleList() {
		return hotSaleList;
	}

	public void setHotSaleList(List<AdvProModule> hotSaleList) {
		this.hotSaleList = hotSaleList;
	}

	public List<AdvResourceModule> getHotSaleTxtList() {
		return hotSaleTxtList;
	}

	public void setHotSaleTxtList(List<AdvResourceModule> hotSaleTxtList) {
		this.hotSaleTxtList = hotSaleTxtList;
	}

	public List<Info> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Info> noticeList) {
		this.noticeList = noticeList;
	}

	public List<AdvImg> getHotSaleDownList() {
		return hotSaleDownList;
	}

	public void setHotSaleDownList(List<AdvImg> hotSaleDownList) {
		this.hotSaleDownList = hotSaleDownList;
	}

	public List<AdvProModule> getNewUpList() {
		return newUpList;
	}

	public void setNewUpList(List<AdvProModule> newUpList) {
		this.newUpList = newUpList;
	}

	public List<AdvResourceModule> getNewUpTxtList() {
		return newUpTxtList;
	}

	public void setNewUpTxtList(List<AdvResourceModule> newUpTxtList) {
		this.newUpTxtList = newUpTxtList;
	}

	public List<AdvImg> getNewUpDownList() {
		return newUpDownList;
	}

	public void setNewUpDownList(List<AdvImg> newUpDownList) {
		this.newUpDownList = newUpDownList;
	}

	public List<AdvProModule> getAllLikeList() {
		return allLikeList;
	}

	public void setAllLikeList(List<AdvProModule> allLikeList) {
		this.allLikeList = allLikeList;
	}

}
