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
	$(".choose").hover(
		function(){
			$(this).addClass("item_checked");
			$(this).parent().find(".box").hide();
			$(this).next().show();
		},
		function(){
			$(this).removeClass("item_checked");
			$(this).next().hide();
		}
	);
	$(".box").hover(
		function(){
			$(this).prev().addClass("item_checked");
			$(this).show();
		},
		function(){
			$(this).prev().removeClass("item_checked");
			$(this).hide();
		}
	);
	$(".sort").click(function(){
		
		$(".order li i").css("background-position","-65px -108px");
		
		if($(this).attr("name") == '0'){
			$(this).attr("name","1");
			$("#asc").val("1");
			$(this).parent().find("i").css("background-position","-86px -108px");
		}else{
			$(this).attr("name","0");
			$("#asc").val("0");
			$(this).parent().find("i").css("background-position","-65px -108px");
		}
		$(".sort").not(this).attr("name","0");
		
		$(".order li").removeClass("first");
		$(this).parent().addClass("first");
		
		$("#orderType").val($(this).parent().index());
		
	});
	$(".selectable a").click(function(){
		
		if($(this).attr("class").indexOf("item_checked") != -1){
			return false;
		}
		
		var name = $(this).attr("name");
		var title = $(this).attr("alt");
		var type = name.split("_")[0];
		var id = name.split("_")[1];
		
		if(type == "brand"){
			if($("#brandIds").val() == ''){
				$("#brandIds").val(",");
			}
			$("#brandIds").val($("#brandIds").val()+id+",");
			
			
			var content = '<a href="javascript:void(0);"><span class="type">品牌：'+title+'</span><span name="'+name+'" class="remove"></span></a>';
			
			$(".selected .options").prepend(content);
			
		}else if(type=="attr"){
			if($("#attrValueIds").val() == ''){
				$("#attrValueIds").val(",");
			}
			$("#attrValueIds").val($("#attrValueIds").val()+id+",");
			
			var attrName = title.split("_")[0];
			var attrValue = title.split("_")[1];
			
			var content = '<a href="javascript:void(0);"><span class="type">'+attrName+'：'+attrValue+'</span><span name="'+name+'" class="remove"></span></a>';
			
			$(".selected .options").append(content);
			
		}
		
		$(this).addClass("item_checked");
		
	});
	
	$(".remove").live('click',function(){
		
		var name = $(this).attr("name");
		var type = name.split("_")[0];
		var id = name.split("_")[1];
		
		$('.selectable a[name = "'+name+'"]').removeClass("item_checked");
		
		if(type == "brand"){
			$("#brandIds").val($("#brandIds").val().replace(","+id+",",","));
		}else if(type == "attr"){
			$("#attrValueIds").val($("#attrValueIds").val().replace(","+id+",",","));
		}
		
		$(this).parent().remove();
		 
	});
	
});

