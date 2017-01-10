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
	
	$("#flushcode").click(function(){
		$(this).attr("src",$(this).attr("src").substr(0,$(this).attr("src").indexOf("?")+1)+new Date().getTime());
	});
	
	$("#mail").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic span").text("请输入邮箱");
			$(this).next().find(".accNotic").show();
		}else{
			if(!mailValide($.trim($(this).val()))){
				$(this).next().find(".accNotic span").text("邮箱格式错误");
				$(this).next().find(".accNotic").show();
				return false
			}
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#checkCode").focus(function(){
		$(this).next().next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().next().find(".accNotic").show();
		}else{
			$(this).next().next().find(".accNotic").hide();
		}
	});
	
	$("#save").click(function(){
	
		if($.trim($("#mail").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}else if(!mailValide($.trim($("#mail").val()))){
			$(this).next().find(".accNotic span").text("邮箱格式错误");
			$(this).next().find(".accNotic").show();
			return false
		}
		
		if($.trim($("#checkCode").val())==''){
			$(this).next().next().find(".accNotic").show();
			return false;
		}
		
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/security_mailEdit.dhtml',
            data:{'mail':$.trim($("#mail").val()),'checkCode':$.trim($("#checkCode").val())},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
            	$("#flushcode").click();
            	$("#checkCode").val('');
                if(data == 1){
                	$("#mail").val('');
					alert("验证邮件发送成功，请前去邮箱验证");
				}else if(data == -1){
					alert("系统异常");
				}else if(data == -2){
					alert("参数错误");
				}else if(data == -3){
					alert("验证码错误");
				}
            },
            error:function(){
            	alert("系统异常");
            }
       });
	});
});