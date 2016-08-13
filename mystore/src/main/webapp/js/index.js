$(function(){ 
	if($("#bannerul li").length > 1){
		timeoutid = setTimeout("banner()", 5000 );
	}
	if($(".smallUl li").length > 1){
		   $(".smallUl li").hover(
		    		 function(){
		    			    clearTimeout(timeoutid);
		    				$(".smallUl li").removeClass("on");
		    				$(this).addClass("on");
		    				$("#bannerul li").hide(100);
		    				$("#bannerul li").eq($(this).index()).show(500);
				     },
				     function(){
					    	index = $(this).index();
			    			timeoutid = setTimeout("banner()", 5000 );
				     }
		   );
	}
	$(".fc_sale_rank .item").hover(function(){
		$(".fc_sale_rank .item").removeClass("hover");
		$(this).addClass("hover");
	});
});
var index = 0;
var timeoutid;
function banner(){
	index++;
	if(index >= $("#bannerul li").length){
		index = 0;
	}
	$("#bannerul li").hide(100);
	$(".smallUl li").removeClass("on");
	$(".smallUl li").eq(index).addClass("on");
	$("#bannerul li").eq(index).show(500);
	timeoutid = setTimeout("banner()", 5000 );
}