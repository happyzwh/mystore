$(function(){
	
	$(".categoryBox_out").hide();
	$(".navi_left_out").hover(
   		 function(){
   			$(".categoryBox_out").show();
	     },
	     function(){
	    	 $(".categoryBox_out").hide();
	     }
	);

});