$(function(){
	initPrivince();
	initCity();
	initCounty();
	$(".categoryBox_out").hide();
	$(".navi_left_out").hover(
   		 function(){
   			$(".categoryBox_out").show();
	     },
	     function(){
	    	 $(".categoryBox_out").hide();
	     }
	);
	
	$("#receiver").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic").show();
		}else{
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#address").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic").show();
		}else{
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#mobile").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic span").text("请输入收货人手机号");
			$(this).next().find(".accNotic").show();
		}else{
			if(!mobileValide($.trim($(this).val()))){
				$(this).next().find(".accNotic span").text("手机号格式错误");
				$(this).next().find(".accNotic").show();
				return false
			}
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#provinceId").change(function(){
		setCity();
		setCounty();
	});
	
	$("#cityId").change(function(){
		setCounty();
	});
	
	$("#provinceId,#cityId,#countyId").change(function(){
		checkArea();
	});

	
	$("#save").click(function(){
		if($.trim($("#receiver").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#address").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#mobile").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}else if(!mobileValide($.trim($("#mobile").val()))){
			$(this).next().find(".accNotic span").text("手机号格式错误");
			$(this).next().find(".accNotic").show();
			return false
		}
		checkArea();
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/address_edit.dhtml',
            data:{'id':$.trim($("#id").val()),'receiver':$.trim($("#receiver").val()),'addre':$.trim($("#address").val()),'mobile':$.trim($("#mobile").val()),
            	'provinceId':$.trim($("#provinceId").val()),'cityId':$.trim($("#cityId").val()),'countyId':$.trim($("#countyId").val())},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
                if(data == 1){
					alert("保存成功");
					window.location.href = $("#path").val()+'/address_index.dhtml';
				}else if(data == -1){
					alert("系统异常");
				}else if(data == -2){
					alert("参数错误");
				}
            },
            error:function(){
            	alert("系统异常");
            }
       });
	});
	
	
});
function deleteAddress(id){
	jQuery.ajax({
        type:'post',
        url:$("#path").val()+'/address_delete.dhtml',
        data:{'id':id},
        dataType:'text',
        cache:false,
        async:false,
        success:function(data){
            if(data == 1){
				alert("删除成功");
				window.location.href = $("#path").val()+'/address_index.dhtml';
			}else if(data == -1){
				alert("系统异常");
			}else if(data == -2){
				alert("参数错误");
			}
        },
        error:function(){
        	alert("系统异常");
        }
   });
}
function checkArea(){
	if($("#provinceId").val() =='' || $("#cityId").val() =='' || $("#countyId").val() ==''){
		$(".area #receiverTS").show();
		return false;
	}else{
		$(".area #receiverTS").hide();
	}
}
function setPrivince(){
	$("#provinceId").empty();
	$("#cityId").empty();
	$("#countyId").empty();
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {   
		content.push("<option value='"+value.id+"'>"+value.name+"</option>");       
    });  
    $("#provinceId").append(content.join(""));   
}
function initPrivince(){
	setPrivince();
	if($("#provinceId").attr("alt") != ''){
	    $("#provinceId").val($("#provinceId").attr("alt"));
	}
}
function setCity(){
	$("#cityId").empty();
	$("#countyId").empty();
	if($("#provinceId").val() == ''){
		$("#cityId").append("<option value=''>请选择</option>");
		return false;
	}
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {  
		if(value.id == $("#provinceId").val()){
			$.each(value.sons,function(m,v) { 
				content.push("<option value='"+v.id+"'>"+v.name+"</option>");  
			});
		}
    });  
    $("#cityId").append(content.join(""));   
}
function initCity(){
	setCity();
	if($("#cityId").attr("alt") != ''){
	    $("#cityId").val($("#cityId").attr("alt"));
	}
}
function setCounty(){
	$("#countyId").empty();
	if($("#provinceId").val() == '' || $("#cityId").val() == ''){
		$("#countyId").append("<option value=''>请选择</option>");
		return false;
	}
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {  
		if(value.id == $("#provinceId").val()){
			$.each(value.sons,function(m,v) { 
				if(v.id == $("#cityId").val()){
					$.each(v.sons,function(na,va) { 
						content.push("<option value='"+va.id+"'>"+va.name+"</option>");  
					});
				}
			});
		}
    });   
    $("#countyId").append(content.join(""));   
}
function initCounty(){
	setCounty();
	if($("#countyId").attr("alt") != ''){
	    $("#countyId").val($("#countyId").attr("alt"));
	}
}