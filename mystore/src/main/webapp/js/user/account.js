$(function(){
	 $(".tab_menu li").click(function(){
	    	$(".tab_menu li").removeClass("select");  
	    	$(this).addClass("select");
	    	$(".detail").hide();
	    	$("."+$(this).attr("name")).show();
	    	if($(this).index() == 0){
	    		type = 0;
	    		getAccountContent(1,pageSize);
	    	}
	 }); 
	 $(".tab_menu li").eq(0).click();
});
var type=0;
var pageSize = 10;
function getAccountContent(pageNo,pageSize){
	if(type == 0){
		$(".accountlog").load("account_list.dhtml", {'pageNo':pageNo,'pageSize':pageSize}, function(){});
	}
}