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
	
	$("#paypwd").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())=='' || $.trim($(this).val()).length < 6 || $.trim($(this).val()).length > 16){
			$(this).next().find(".accNotic span").text("请输入6~16位支付密码");
			$(this).next().find(".accNotic").show();
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
	
		if($.trim($("#paypwd").val())=='' || $.trim($("#paypwd").val()).length < 6 || $.trim($("#paypwd").val()).length > 16){
			$(this).next().find(".accNotic").show();
			return false;
		}
		
		if($.trim($("#checkCode").val())==''){
			$(this).next().next().find(".accNotic").show();
			return false;
		}
		
		var modulus = $("#modulus").val();
		var exponent = $("#exponent").val();
		
		var publicKey = RSAUtils.getKeyPair(exponent, '',modulus);
		var paypwd = RSAUtils.encryptedString(publicKey, $.trim($("#paypwd").val()));
		
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/security_updPayPwd.dhtml',
            data:{'paypwd':paypwd,'checkCode':$.trim($("#checkCode").val())},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
            	$("#flushcode").click();
            	$("#checkCode").val('');
                if(data == 1){
                	$("#paypwd").val('');
					alert("支付密码保存成功");
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