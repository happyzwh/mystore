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
	
	$("#changepwd").click(function(){
		tochangimg();
	});
	
	$("#flushcode").click(function(){
		$(this).attr("src",$(this).attr("src").substr(0,$(this).attr("src").indexOf("?")+1)+new Date().getTime());
	});
	
	$(".form-element input").focus(function(){
		$(this).parent().next().removeClass("hidden");
		if($.trim($(this).val()) == ''){
			$(this).css("color","#C4C4C4");
		}else{
			$(this).css("color","#000");
		}
		
	});
	$(".form-element input").blur(function(){
		$(this).parent().next().addClass("hidden");
		if($.trim($(this).val()) == ''){
			$(this).css("color","#C4C4C4");
		}else{
			$(this).css("color","#000");
		}
	});
	$(".form-element input").keyup(function(){
		if($.trim($(this).val()) == ''){
			$(this).css("color","#C4C4C4");
		}else{
			$(this).css("color","#000");
		}
	});
	/* 注册 表单验证 validator start */
	var validator = $("#regForm").validate({  
			 submitHandler:function(form){  
				var modulus = $("#modulus").val();
				var exponent = $("#exponent").val();
				var publicKey = RSAUtils.getKeyPair(exponent, '',modulus);
				var password = RSAUtils.encryptedString(publicKey, $.trim($("#password").val()));
				$.ajax({
					url: 'user_login.dhtml',
					type: 'post',
					data: {'userName':$.trim($("#userName").val()),'password':password,'verifyCode':$.trim($("#verifyCode").val())},
					async: true,
					dataType: "text",
					success:function(data){
						$("#flushcode").attr("src",$("#flushcode").attr("src").substr(0,$("#flushcode").attr("src").indexOf("?")+1)+new Date().getTime());
						if(data == 1){
							$(".sys-error").text("登录成功！").show();
							document.location.href='user_center.dhtml';
						}else if(data == -1){
							$(".sys-error").text("系统异常请重试！").show();
						}else if(data == -2){
							$(".sys-error").text("参数错误！").show();
						}else if(data == -3){
							$(".sys-error").text("验证码错误！").show();
						}else if(data == -4){
							$(".sys-error").text("用户名或密码错误！").show();
						}else if(data == -5){
							$(".sys-error").text("用户暂不可用，请联系联服！").show();
						}
					},
					error:function(){
						form.reset();
						$("#flushcode").attr("src",$("#flushcode").attr("src").substr(0,$("#flushcode").attr("src").indexOf("?")+1)+new Date().getTime());
						$(".sys-error").text("系统异常请重试！").show();
					}
				});
	        },  
			errorPlacement: function(error, element) {
				
				if(element.attr('name') != "agreeProtocol"){
					$(element).parent().addClass('errorWrap');
				}
				
				var curcss =  {
					    'border': '0',
					    'border-radius': '0px',
					    'background-color': '#fff',
					    'color': '#ff0000',
					    'font-family': "simsun",
					    'font-size': '12px',
					    'padding-top': '0px',
					    'text-align':'left',
					    'width': '260px'
				};
				error.css(curcss);

				var top_py = 53;
				var top_margin_left = 160;
				if(element.attr('name') == "agreeProtocol"){
					top_py = 20;
					top_margin_left = 0;
				}
				
				error.css({
						display:"block",
						position:"absolute",
						left:$(element).position().left,
						top:$(element).position().top+top_py
			    });
				
				error.insertAfter(element.parent());
				
				error.css({'margin-left':top_margin_left+'px'});
			},
			success: function(label, element) {
				$(element).parent().removeClass('errorWrap');
				label.remove();
	        },
//		    debug:true ,
//			onkeyup: false,
//			focusCleanup:false,
//			onblur:false,
	        rules:{
	        	userName:{
	        		required: true,
	        		minLength:6,
	        	},
	        	password:{
	        		required: true,
	        		minLength:6,
	        	},
	        	comfirm_password:{
				    required: true,
				    minLength:6,
				    equalTo: "#password"
				 },
				 verifyCode:{
		        	required: true,
		        	minLength:4,
		         },
				 agreeProtocol: {
					required: true
			     }
	   	    },
	   	    messages:{
	   	    	comfirm_password: {
				    required: "请输入确认密码",
				    equalTo: "两次密码不一致"
				},
				agreeProtocol: {
				    required: "必选项"
				}
	   	   }   
	});
});
function tochangimg(){
    $("#changepwd").attr("src",$("#bathPath").val()+"/images/pwdicon2.png");
    $("#password").hide();
    $("#password1").show();
    var pwd=$("#password").val();
    $("#password1").val(pwd);
    window.setTimeout("rechangtopwd()",2000);
}
function rechangtopwd(){
	$("#changepwd").attr("src",$("#bathPath").val()+"/images/pwdicon.png");
	$("#password1").hide();
	$("#password").show();
}
jQuery.validator.addMethod("minLength", function(value, element, param) {  
	var length = value.length;  
	for ( var i = 0; i < value.length; i++) {  
		if (value.charCodeAt(i) > 127) {  
			length++;  
		}  
	}  
	return this.optional(element) || (length >= param);  
}, $.validator.format("长度不能小于{0}!")); 
