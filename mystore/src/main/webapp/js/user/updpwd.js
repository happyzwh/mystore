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
	
	$("#oldpwd").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())=='' || $.trim($(this).val()).length < 6 || $.trim($(this).val()).length > 16){
			$(this).next().find(".accNotic").show();
		}else{
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#pwd").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())=='' || $.trim($(this).val()).length < 6 || $.trim($(this).val()).length > 16){
			$(this).next().find(".accNotic span").text("请输入6~16位新密码");
			$(this).next().find(".accNotic").show();
		}else{
			if($("#pwdCheck").val() != '' && $.trim($(this).val()) != $.trim($("#pwdCheck").val())){
				$(this).next().find(".accNotic span").text("确认密码与新密码不一致");
				$(this).next().find(".accNotic").show();
				return false;
			}
			$(this).next().find(".accNotic").hide();
			$("#pwdCheck").next().find(".accNotic").hide();
		}
	});
	
	$("#pwdCheck").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())=='' || $.trim($(this).val()).length < 6 || $.trim($(this).val()).length > 16){
			$(this).next().find(".accNotic span").text("请输入6~16位确认密码");
			$(this).next().find(".accNotic").show();
		}else{
			if($("#pwd").val() != '' && $.trim($(this).val()) != $.trim($("#pwd").val())){
				$(this).next().find(".accNotic span").text("确认密码与新密码不一致");
				$(this).next().find(".accNotic").show();
				return false;
			}
			$(this).next().find(".accNotic").hide();
			$("#pwd").next().find(".accNotic").hide();
		}
	});
	
	$("#save").click(function(){
		
		if($.trim($("#oldpwd").val())=='' || $.trim($("#oldpwd").val()).length < 6 || $.trim($("#oldpwd").val()).length > 16){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#pwd").val())=='' || $.trim($("#pwd").val()).length < 6 || $.trim($("#pwd").val()).length > 16){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#pwdCheck").val())=='' || $.trim($("#pwdCheck").val()).length < 6 || $.trim($("#pwdCheck").val()).length > 16){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#pwd").val()) != $.trim($("#pwdCheck").val())){
			$(this).next().find(".accNotic span").text("确认密码与新密码不一致");
			$(this).next().find(".accNotic").show();
			return false;
		}
		
		var modulus = $("#modulus").val();
		var exponent = $("#exponent").val();
		
		var publicKey = RSAUtils.getKeyPair(exponent, '',modulus);
		var oldpwd = RSAUtils.encryptedString(publicKey, $.trim($("#oldpwd").val()));
		var pwd = RSAUtils.encryptedString(publicKey, $.trim($("#pwd").val()));
		var pwdCheck = RSAUtils.encryptedString(publicKey, $.trim($("#pwdCheck").val()));
		
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/security_updatePwd.dhtml',
            data:{'oldPwd':oldpwd,'pwd':pwd,'pwdCheck':pwdCheck},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
                if(data == 1){
                	$("#oldpwd").val('');
                	$("#pwd").val('');
                	$("#pwdCheck").val('');
					alert("修改成功");
				}else if(data == -1){
					alert("系统异常");
				}else if(data == -2){
					alert("参数错误");
				}else if(data == -3){
					alert("确认密码与新密码不一致");
				}else if(data == -4){
					$("#oldpwd").val('');
					alert("原密码不正码");
				}
            },
            error:function(){
            	alert("系统异常");
            }
       });
	});
});