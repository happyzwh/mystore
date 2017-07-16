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
	
	//密码强度验证
	$("#password").bind("blur keydown",function() {
		var a = $("#pwdStatus span");
		a.removeClass().addClass('s_box');
		switch ( checkStrong($(this).val()) ) {
			case 1:
				a.eq(0).addClass("s_box_active1");
				break;
			case 2:
				a.eq(0).addClass("s_box_active1");
				a.eq(1).addClass("s_box_active2");
				break;
			case 3:
				a.eq(0).addClass("s_box_active1");
				a.eq(1).addClass("s_box_active2");
				a.eq(2).addClass("s_box_active3");
				break
			default :
		}
	});
	function checkStrong(sPW) {
	    Modes=0;
	    for (i=0;i<sPW.length;i++) {
	     Modes|=CharMode(sPW.charCodeAt(i));
	   }
	   return bitTotal(Modes);
	}
	function CharMode(iN) {
	   if (iN>=48 && iN <=57) return 1;//数字
	   if (iN>=65 && iN <=90) return 2;//大写字母
	   if (iN>=97 && iN <=122) return 4;//小写
	   else return 8; //特殊字符
	}
	function bitTotal(num) {
	   modes=0;
	   for (i=0;i<4;i++) {
	    if (num & 1) modes++;
	     num>>>=1;
	    }
	   return modes;
	}
	//密码强度验证 end
	
	$("#changepwd").click(function(){
		tochangimg();
	});
	
	$("#flushcode").click(function(){
		$(this).attr("src",$(this).attr("src").substr(0,$(this).attr("src").indexOf("?")+1)+new Date().getTime());
	});
	
	$(".form-element input").focus(function(){
		$(this).parent().next().removeClass("hidden");
		$(this).css({"outline":"none"});
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
					url: $("#path").val()+'/user_register.dhtml',
					type: 'post',
					data: {'userName':$.trim($("#userName").val()),'password':password,'verifyCode':$.trim($("#verifyCode").val())},
					async: true,
					dataType: "text",
					success:function(data){
						$("#flushcode").attr("src",$("#flushcode").attr("src").substr(0,$("#flushcode").attr("src").indexOf("?")+1)+new Date().getTime());
						if(data == 1){
							$(".sys-error").text("注册成功！").show();
						}else if(data == -1){
							$(".sys-error").text("系统异常请重试！").show();
						}else if(data == -2){
							$(".sys-error").text("参数错误！").show();
						}else if(data == -3){
							$(".sys-error").text("验证码错误！").show();
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
	        		isAccountExist:true
	        	},
	        	password:{
	        		required: true,
	        		minLength:6
	        	},
	        	comfirm_password:{
				    required: true,
				    minLength:6,
				    equalTo: "#password"
				 },
				 verifyCode:{
		        	required: true,
		        	minLength:4
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
    $("#changepwd").attr("src",$("#path").val()+"/images/pwdicon2.png");
    $("#password").hide();
    $("#password1").show();
    var pwd=$("#password").val();
    $("#password1").val(pwd);
    window.setTimeout("rechangtopwd()",2000);
}
function rechangtopwd(){
	$("#changepwd").attr("src",$("#path").val()+"/images/pwdicon.png");
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

jQuery.validator.addMethod("isAccountExist", function(value, element, param) {  
	if(jQuery.trim(value) != ''){
        return !isAccountExist(jQuery.trim(value)); 
    }
    return true; 
}, $.validator.format("用户名已存在!")); 

function isAccountExist(value){
    var tf = false;
    jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/user_isAccountExist.dhtml',
            data:{'userName':jQuery.trim(value)},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
                if(data == 1){
					tf = true;
				}
            },
            error:function(){
            	
            }
       });
       return tf;
}
