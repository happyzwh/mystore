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
	$(".yzType .res").hover(
		function(){
			$(this).addClass("on");
			$(this).next().show();
		},
		function(){
			$(this).removeClass("on");
			$(this).next().hide();
		}
	);
	$(".yzType .edit").hover(
			function(){
				$(this).prev().addClass("on");
				$(this).show();
			},
			function(){
				$(this).prev().removeClass("on");
				$(this).hide();
			}
   );
});