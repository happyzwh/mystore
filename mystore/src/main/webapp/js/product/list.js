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
	
	var sortIndex = $("#orderType").val();
	var asc = $("#asc").val();
	$(".order li:eq("+sortIndex+")").addClass("first");
	if(asc == 0){
		$(".order li:eq("+sortIndex+")").find("a").attr("name","0");
		$(".order li:eq("+sortIndex+")").find("i").css("background-position","-65px -108px");
	}else{
		$(".order li:eq("+sortIndex+")").find("a").attr("name","1");
		$(".order li:eq("+sortIndex+")").find("i").css("background-position","-86px -108px");
	}
	
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
	$("#minPrice,#maxPrice").blur(function(){
		var regExp = /^(([1-9]\d*)|0|([0-9]\d*\.\d{1,2}))$/; 
		if($.trim($(this).val()) != '' && !regExp.test($.trim($(this).val()))){
			$(this).val("");
			alert("提示：价格只能为数值！");
			return false;
		}
		if($.trim($("#minPrice").val()) != '' && $.trim($("#maxPrice").val()) != ''){
			if(parseFloat($.trim($("#minPrice").val())) > parseFloat($.trim($("#maxPrice").val()))){
				alert("提示：起始价格不能高于终止价格！");
			}
		}
	});
});
function search(){
	var regExp = /^(([1-9]\d*)|0|([0-9]\d*\.\d{1,2}))$/;
	if($.trim($("#minPrice").val()) != '' && !regExp.test($.trim($("#minPrice").val()))){
		$("#minPrice").val("");
		alert("提示：价格只能为数值！");
		return false;
	}
	if($.trim($("#maxPrice").val()) != '' && !regExp.test($.trim($("#maxPrice").val()))){
		$("#maxPrice").val("");
		alert("提示：价格只能为数值！");
		return false;
	}
	if($.trim($("#minPrice").val()) != '' && $.trim($("#maxPrice").val())){
		if(parseFloat($.trim($("#minPrice").val())) > parseFloat($.trim($("#maxPrice").val()))){
			alert("提示：起始价格不能高于终止价格！");
			return false;
		}
	}
	$("#lowPrice").val($.trim($("#minPrice").val()));
	$("#highPrice").val($.trim($("#maxPrice").val()));
   
	$("#pageNo").val(1);
	$("#keyword").val($("#keywords").val());
	
	$("#searchForm").submit();
}
function jumppage(pageNo){
	$("#pageNo").val(pageNo);
	$("#searchForm").submit();
}

