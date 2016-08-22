jQuery.extend(jQuery.validator.messages,   {
	required:  "必选字段不能为空",
	remote:  "请修正该字段",
	email:  "请输入正确格式的电子邮件",
	url:  "请输入合法的网址",
	date:  "请输入合法的日期",
	dateISO:  "请输入合法的日期 (ISO).",
	number:  "请输入合法的数字",
	digits:  "只能输入整数",
	creditcard:  "请输入合法的信用卡号",
	equalTo:  "请再次输入相同的值",
	accept:  "请输入拥有合法后缀名的字符串",
	maxlength:  jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
	minlength:  jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength:  jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range:  jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max:  jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min:  jQuery.validator.format("请输入一个最小为 {0} 的值")
});

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
	var length = value.length;
	for(var i = 0; i < value.length; i++){
		if(value.charCodeAt(i) > 127){
			length++;
		}
	}
	return this.optional(element) || ( length >= param[0] && length <= param[1] );
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));


// 邮政编码验证   
jQuery.validator.addMethod("zip", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

//金钱格式，保留小数点后四位
jQuery.validator.addMethod("money", function(value, element) {
	var tel = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$/;
	return this.optional(element) || (tel.test(value));
}, "请输入数字，小数点后最多保留4位");
//金钱格式，保留小数点后四位
jQuery.validator.addMethod("money1", function(value, element) {
	var tel = /^([1-9]{1}\d*)$/;
	return this.optional(element) || (tel.test(value));
}, "输入的金额必须为整数");

jQuery.validator.addMethod("limitMoneyInput", function(value, element) {
	var temp=parseInt(value);
	return this.optional(element) || (temp>=100);
}, "转让金额不能小于100元");

jQuery.validator.addMethod("limitMoneyInput1", function(value, element) {
	var temp=parseInt(value);
	return this.optional(element) || (temp>=100);
}, "认购金额不能小于100元");

jQuery.validator.addMethod("limitMoneyInput2", function(value, element) {
	var lastLotVal=parseInt($("#lastLot").text());
	var temp=parseInt(value);
	return this.optional(element) || (lastLotVal-temp>=100 || lastLotVal-temp <=0);
}, "剩余金额不足100，请将剩余金额全部认购");

jQuery.validator.addMethod("limitMoneyInput3", function(value, element) {
	var lastLotVal=parseInt($("#lastLot").text());
	var temp=parseInt(value);
	return this.optional(element) || (lastLotVal-temp>=0);
}, "剩余可投债权不足");

//吨等重量格式 保留小数点后两位
jQuery.validator.addMethod("weight", function(value, element) {
	var tel = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/;
	return this.optional(element) || (tel.test($.trim(value)));
}, "请输入数字，小数点后最多保留2位");


//电话格式 保留小数点后两位
jQuery.validator.addMethod("tel", function(value, element) {
	var tel = /^[\d\-\(\)]{0,18}$/;
	return this.optional(element) || (tel.test(value));
}, "您正确填写您的电话");


//手机格式 保留小数点后两位
jQuery.validator.addMethod("mobel", function(value, element) {
	var tel = /^[\d\-\(\)]{0,18}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的手机号");

//手机格式 保留小数点后两位
jQuery.validator.addMethod("mobelInfo", function(value, element) {
	var tel = /^1[0-9]{10}/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的手机号");


//qq
jQuery.validator.addMethod("qq", function(value, element) {
	var tel = /^[1-9][0-9]{4,11}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的QQ号");

// new add ryq
// ie placeholder 影响为空验证的处理方法
jQuery.validator.addMethod("checkplaceholder", function(value, element) {

	var _thisplaceholder= $(element).attr("placeholder"),
		_thisVal = $(element).val(),
		isreturn = true;
	if(_thisplaceholder == _thisVal){
		isreturn=false;
	}
	return isreturn;
}, "必选字段不能为空");

/*手机号*/
jQuery.validator.addMethod("chkmobile", function(value, element) {

	//var regex = /^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9]|16[0-9]|19[0-9]|12[0-9])\d{8}$/;
	var regex = /^(1){1}[0-9]{10}$/;
	return regex.test(value);

}, "请输入正确的手机号！");

/*username*/
jQuery.validator.addMethod("chkusername", function(value, element) {
	var regUsername = "^[a-zA-Z0-9_\u4e00-\u9fa5]{2,30}$",
		regex = new RegExp(regUsername);
	// var regex = /^[a-zA-Z0-9\-_\u4e00-\u9fa5]{5,30}$/;
	return regex.test(value);

}, "2~30位中英文、数字及下划线");

jQuery.validator.addMethod("chkusernameNo", function(value, element) {
	var regUsername = "^(?!\\d*$)",
		regex = new RegExp(regUsername);
	// var regex = /^[a-zA-Z0-9\-_\u4e00-\u9fa5]{5,30}$/;
	return regex.test(value);

}, "用户名不允许全部为数字");
/*username*/
jQuery.validator.addMethod("chkpwd", function(value, element) {
	var regPwd = "^[~`!@#\\$%\\^&\\*\\(\\)_\\-\\+=\\|\\\\\\}\\]\\{\\[:;<,>\\.\\?\\/\"'a-zA-Z0-9]{6,16}$",
		regex = new RegExp(regPwd);
	return regex.test(value);

}, "6~16位字符，支持英文、数字及英文符号");

jQuery.validator.addMethod("chkrepwd", function(value, element) {

	var toid = $(element).attr('data-toid');
	if(value == $("#"+toid).val()){
		return true;
	}else{
		return false;
	}

}, "两次密码不一致！");

/*checkIdNo*/
jQuery.validator.addMethod("checkIdNo", function(value, element) {
	var idNo = value;
	var datatoid = $(element).attr("data-toid");
	var realName  = $("#"+datatoid).val();
	var successIdNo = $("#successIdNo").val();
	var successName = $("#successName").val();
	//验证IdNo开始

	istrue = false;
	if(idNo == successIdNo && realName == successName){
		istrue=true;
	}else{
		if(idNo!=""){


			$.ajax({
				type: "post", //用POST方式传输
				dataType: "json", //数据格式:JSON
				url: '/member_isAuth.html', //目标地址
				data : {idNo : idNo,realName:realName},
				async:false,
				success : function(data) {

					if(data[0].msg=="false"){//验证失败

						istrue = false;
					}
					else if(data[0].msg=="true"){//验证成功
						istrue = true;
						$("#isAuth").val(1);
						$("#successIdNo").val(idNo);
						$("#successName").val(realName);
						//return true;
					}
				}


			});
		}
	}
	return istrue;
	//验证IdNo结束
//}, "姓名与身份证号信息不匹配，请核对后重新输入！");
}, "已绑定其他投资方，不能再次绑定！");

/*checkPhoneNo*/
jQuery.validator.addMethod("checkPhoneNo", function(value, element) {
	var phone = value;
	var istrue = false;
	function dealcheckCode(element,isdisabled){
		var dealid = $(element).attr("data-toid");
		var dealidbtn  = $("#"+dealid);

		if(dealidbtn){
			var oribgcolor = '#4BD3FF',
				oricolor = '#fff';
			if(isdisabled){
				dealidbtn.attr("disabled", false);
				dealidbtn.css("color", oricolor);
				dealidbtn.css("background-color", oribgcolor);
			}else{
				dealidbtn.attr("disabled", true);
				dealidbtn.css("color", "#B6B6B6");
				dealidbtn.css("background-color", "#F0EFEF");
			}

		}
		//20151022
		var dealida = $("#useVoiceBtn");
		var dealidabak = $("#useVoiceBtn_bak");
		if(dealida){
			if(isdisabled){
				dealida.css("display","inline");
				dealidabak.css("display","none");
			}else{
				dealida.css("display","none");
				dealidabak.css("display","inline");
			}
		}
	}

	$.ajax({
		type: "post", //用POST方式传输
		dataType: "json", //数据格式:JSON
		url: '/member_checkPhone.html', //目标地址
		data : {phone : phone},
		async:false,
		success : function(data) {
			if(data[0].msg=="false"){//验证失败
				istrue = false;
				dealcheckCode(element,istrue);
			}
			else if(data[0].msg=="true"){//验证成功
				istrue = true;
				dealcheckCode(element,istrue);
				/*$("#isAuth").val(1);
				 $("#successIdNo").val(idNo);
				 $("#successName").val(realName);*/
				//return true;
			}
		}
	});
	//}
	//}
	return istrue;
	//验证手机号结束
}, "同一手机不能绑定多个帐号！");

jQuery.validator.addMethod("checkName", function(value, element) {
	debugger;
	var realName = value;
	var datatoid = $(element).attr("data-value");
	var idNo  = $("#"+datatoid).val();
	var successIdNo = $("#successIdNo").val();
	var successName = $("#successName").val();
	//验证IdNo开始
	istrue = false;
	if(idNo == successIdNo && realName == successName){
		istrue=true;
	}else{
		if(idNo!=""&&realName!=""){


			$.ajax({
				type: "post", //用POST方式传输
				dataType: "json", //数据格式:JSON
				url: '/member_isAuth.html', //目标地址
				data : {idNo : idNo,realName:realName},
				async:false,
				success : function(data) {
					if(data[0].msg=="false"){//验证失败
						istrue = false;
//				var eldom = $("#"+datatoid)
//				var  oktip = $(eldom).parent().find('.oktip');
//				 if(oktip.length!=0){
//				 	 oktip.remove();
//				 } 
						//return false;
					}
					else if(data[0].msg=="true"){//验证成功
						istrue = true;
						$("#isAuth").val(1);
						$("#successIdNo").val(idNo);
						$("#successName").val(realName);
						//return true;
					}
				}


			});
		}
	}
	return istrue;
	//验证IdNo结束
}, "姓名与身份证号信息不匹配，请核对后重新输入！");

jQuery.validator.addMethod("userinfocheck", function(value, element) {
	var istrue = false;

	var email =  $(element).val();
	$.ajax({
		type : "post",
		url : "/resetpwd_check_email.html",
		data : {
			email : email
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data[0].message == "1") {
				//return true;
				istrue = true;
			} else {

				//return false;
				istrue = false;
			}
		}
	});

	return istrue;
}, "会员信息不存在");

/*ckeckVerifyCode*/
jQuery.validator.addMethod("ckeckVerifyCode", function(value, element) {
	var code = value;

	//开始验证 验证码
	var sendParams = {
		ckeckCode : code
	};
	$.ajax({
		type: "post", //用POST方式传输
		dataType: "json", //数据格式:JSON
		url: 'js/ckecktel.json', //目标地址
		params : sendParams,
		beforeSend: function(){

		},
		success : function(res) {
			if(res.msg_code==-1){//验证失败
				return false;
			}
			else if(res.msg_code==0){//验证成功
				return true;
			}

		},
		failure : function(res) {
			return false;
		}
	});
	//开始验证 验证码 end


}, "请输入正确的验证码！");

jQuery.validator.addMethod("ckeckEmail", function(value, element) {
	var code = value;
	var ret;
	//开始验证 验证码
	$.ajax({
		type: "post", //用POST方式传输
		async:false,
		dataType: "text", //数据格式:text
		url: '/regist_validform.html?name=memberReq.email&param='+value, //目标地址
		success : function(res) {
			if(res == 'y'){//验证成功
				ret = true;
			}else{
				ret = false;
			}
		},
		failure : function(res) {
			ret = false;
		}
	});
	return ret;
}, "邮箱已被注册，请更换邮箱!");

jQuery.validator.addMethod("ckeckMobile", function(value, element) {
	var code = value;
	var ret;
	//开始验证 验证码
	$.ajax({
		type: "post", //用POST方式传输
		async:false,
		dataType: "text", //数据格式:text
		url: '/regist_validform.html?name=memberReq.boundPhone&param='+value, //目标地址
		success : function(res) {
			if(res == 'y'){//验证成功
				ret = true;
			}else{
				ret = false;
			}
		},
		failure : function(res) {
			ret = false;
		}
	});
	return ret;
}, "手机号已被注册，请更换手机号!");

jQuery.validator.addMethod("ckeckUserName", function(value, element) {
	var code = value;
	var ret;
	//开始验证 验证码
	$.ajax({
		type: "post", //用POST方式传输
		async:false,
		dataType: "text", //数据格式:text
		url: '/regist_validform.html?name=memberReq.name&param='+value, //目标地址
		success : function(res) {
			if(res == 'y'){//验证成功
				ret = true;
			}else{
				ret = false;
			}
		},
		failure : function(res) {
			ret = false;
		}
	});
	return ret;
}, "用户名已被注册，请更换用户名!");

// jQuery.validator.addMethod("checkAuthCode", function(value, element) {
//     var code = value;
//     var ret;
//         //开始验证 验证码
//         $.ajax({
//             type: "post", //用POST方式传输
//             async:false,
//             dataType: "text", //数据格式:text
//             url: '/regist_validform.html?name=registVcode&param='+value, //目标地址
//             success : function(res) {
//                 if(res == 'y'){//验证成功
//                 	ret = true;
//                 }else{
//                 	ret = false;
//                 	$("#checkcode").attr("src",
//         					"/CheckCode.pic?date=" + new Date().getTime());
//                 }
//             },
//             failure : function(res) {
//             	ret = false;
//             	$("#checkcode").attr("src",
//     					"/CheckCode.pic?date=" + new Date().getTime());
//             }
//         });
//         return ret;
// }, "验证码错误!");

var result="验证码输入不正确!";
jQuery.validator.addMethod("checkCodenew", function(value, element) {
	var flat=true;
	if(value.length==6){
		//debugger;
		flat=false;
		//开始验证 验证码
		$.ajax({
			url : "/member_checkCode1.html",
			type : "POST",
			async : false,
			data : {
				phoneCode :value
			},
			dataType : "json",
			success : function(message) {

				if("success"==message[0].message){

					flat=true;
				}else{

					flat=false;
				}

			}
		});
		return flat;
	}

}, result);
//

jQuery.validator.addMethod("checkIDCard", function(value, element) {
	//debugger;
	var flat;
	//开始验证 验证码

	$.ajax({
		url : "/member_checkIDCard.html",
		type : "POST",
		async : false,
		data : {
			IDCard :value
		},
		dataType : "html",
		success : function(message) {

			if("success"==message){

				flat=true;
			}else{
				flat=false;
			}

		}
	});


	return flat;

}, "身份证号输入不正确!");


/*  自己添加  */
//身份证
jQuery.validator.addMethod("idNo", function(value, element) {
	var idNo = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	return this.optional(element) || (idNo.test(value));
}, "请正确填写您的身份证号");
/*  自己添加  */
//办公电话
jQuery.validator.addMethod("officeTel", function(value, element) {
	var officeTel = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	return this.optional(element) || (officeTel.test(value));
}, "请正确填写您的办公电话号");
/*  自己添加  */
//银行卡号
jQuery.validator.addMethod("bankCard1", function(value, element) {
	var bankCard = /^\d{14,19}$/;
	return this.optional(element) || (bankCard.test(value.replace(/\s/g,'')));
}, "请正确填写您的银行卡号");
jQuery.validator.addMethod("checkBankCard1", function(value, element) {
	var flat;
	$.ajax({
		url : "/member_checkBank.html",
		type : "POST",
		async : false,
		data : {
			bankCard :value.replace(/\s/g,'')
		},
		dataType : "html",
		success : function(message) {

			if("success"==message){

				flat=true;
			}else{
				flat=false;
			}

		}
	});
	return flat;
}, "您填写的银行卡号已被绑定，请重新输入！");
/**
 * 计算实际支付金额
 */
jQuery.validator.addMethod("calculateMoney", function(value, element) {


	var zId=$("#zId").val();//债权id

	var flat=false;
	if(zId!=""){
		$.ajax({
			url : "/zCreditassign_calculateMoney.html",
			type : "POST",
			async : false,
			data : {
				subscriptionLot :value,
				zId:zId
			},
			dataType : "json",
			success : function(message) {
				if(message.length>0){
					$("#fPrice").html(message[0].paymoney);
					$("#subscribeAmount").val(message[0].subscriptionAmount);
					$("#interest").val(message[0].interest);
					$("#paymoney").val(message[0].paymoney);
					$("#subscribeLot1").val(message[0].subscriptionLot);
					//$("#lastLot").html(message[0].lastLot);
					$("#yssy").html((Number(message[0].paymoney)-Number(message[0].subscriptionLot)).toFixed(2));
					flat=true;
				}


			}
		});
	}
	return flat;
}, "实际支付金额计算出错，请重新计算！");
/**
 * 检查认购者是否与债转者为同一人
 */
jQuery.validator.addMethod("isLogined", function(value, element) {
	var zId=$("#zId").val();//债权id

	var flat=false;
	if(zId!=""){
		$.ajax({
			url : "/zCreditassign_checkUser.html",
			type : "POST",
			async : false,
			data : {
				zId:zId

			},
			dataType : "json",
			success : function(result) {

				if(result.length>0){

					if(result[0].code==0){
						flat=true;
					}
				}


			}
		});
	}
	return flat;

}, "登录超时，请重启登录！");
/**
 * 检查认购者是否与债转者为同一人
 */
jQuery.validator.addMethod("checkUser", function(value, element) {
	var zId=$("#zId").val();//债权id

	var flat=false;
	if(zId!=""){
		$.ajax({
			url : "/zCreditassign_checkUser.html",
			type : "POST",
			async : false,
			data : {
				zId:zId

			},
			dataType : "json",
			success : function(result) {

				if(result.length>0){

					if(result[0].message=="sucess"){

						flat=true;
					}
				}


			}
		});
	}
	return flat;

}, "本人不能认购自己债转的债权！");
/**
 * 检查余额是否充足
 */
jQuery.validator.addMethod("checkPaymoney", function(value, element) {
	var flat=false;
	var zId=$("#zId").val();//债权id

	$.ajax({
		url : "/zCreditassign_checkPaymoney.html",
		type : "POST",
		async : false,
		data : {

			subscriptionLot:value,
			zId:zId
		},
		dataType : "json",
		success : function(result) {

			if(result[0].message=="success"){//余额充足
				flat=true;
			}
		}
	});


	return flat;

}, "余额不足！");
/**
 * 检查余额是否充足
 */
jQuery.validator.addMethod("checkPaymoney1", function(value, element) {

	var flat=false;
	var subscribeLot=$("#subscribeLot").val();
	var zId=$("#id").val();//债权id

	$.ajax({
		url : "/zCreditassign_checkPaymoney.html",
		type : "POST",
		async : false,
		data : {

			subscriptionLot:subscribeLot,
			zId:zId
		},
		dataType : "json",
		success : function(result) {

			if(result[0].message=="success"){//余额充足
				flat=true;
			}
		}
	});


	return flat;

}, "余额不足！");
jQuery.validator.addMethod("checkSubscribLot", function(value, element) {
	var flat=false;
	var zId=$("#zId").val();//债权id
	if(zId!=""){
		$.ajax({
			url : "/zCreditassign_checkSubscribLot.html",
			type : "POST",
			async : false,
			data : {
				subscribLot:value,
				zId:zId
			},
			dataType : "json",
			success : function(result) {

				//alert(message[0].subscribeAmount);

				if(result.length>0){

					if(result[0].message=="success"){
						flat=true;

					}

				}


			}
		});
	}

	return flat;
}, "您认购的份额超出剩余份额,请刷新后重试！");
/**
 * 检查余额是否充足
 */
jQuery.validator.addMethod("checkpwd", function(value, element) {
	var flat=false;

	$.ajax({
		url : "/zCreditassign_checkPwd.html",
		type : "POST",
		async : false,
		data : {

			pwd:value
		},
		dataType : "json",
		success : function(result) {
			if(result[0].message=="success"){//余额充足
				flat=true;
			}
		}
	});

	return flat;

}, "密码不正确，请重新输入！");


jQuery.validator.addMethod("inptagree", function(value, element) {

	var flat=false;
	if($("#inptagree").attr("checked")){//同意条款
		if($("remainchkbox2")){
			flat=true;
		}

	}
	return flat;


}, "同意条款后方可提交！");
/*  自己添加  */
/*  资金密码*/
jQuery.validator.addMethod("paypwd", function(value, element) {
	var regex = /^(?![~`!@#\$%\^&\*\(\)\+=\|\\\}\]\{\[_\\.:;<,>\?\/"']*$)(?![a-zA-Z]*$)(?![0-9]*$)[~`!@#\$%\^&\*\(\)\+=\|\\\}\]\{\[_\\.:;<,>\?\/"'a-zA-Z0-9]{8,16}$/;
	// var regex = /^[a-zA-Z0-9\-_\u4e00-\u9fa5]{5,30}$/;
	return regex.test(value);

}, "8~16位字符，支持英文、数字或英文符号任意两种组合!");
/*  自己添加  */
/*  确认资金密码  */
jQuery.validator.addMethod("chkpaypwd", function(value, element) {
//	if(value == acct_pwd.value){
//	    return true;
//	}else{
//		return false;
//	}

	var toid = $(element).attr('data-toid');
	if(value == $("#"+toid).val()){
		return true;
	}else{
		return false;
	}


}, "两次密码不一致！");

jQuery.validator.addMethod("chknewpwd", function(value, element) {


	var toid = $("#oldPwd");
	if(value == toid.val()){
		return false;
	}else{
		return true;
	}


}, "请输入与旧密码不同的新密码！");

//检查金宝宝可转出余额

jQuery.validator.addMethod("checkjbboutmoney", function(value, element) {
	var flat;
	$.ajax({
		url : "/jIncome_checkOutTotalMoney.html",
		type : "POST",
		async : false,
		data : {
			outMoney :value
		},
		dataType : "html",
		success : function(message) {

			if("success"==message){

				flat=true;
			}else{
				flat=false;
			}

		}
	});
	return flat;
}, "余额不足！");
jQuery.validator.addMethod("checkUserType", function(value, element) {

	var flat;
	var type=$("#type").val();
	if(type=="2"){
		flat=false;
	}else{
		flat=true;
	}
	return flat;
}, "借款方不能认购！");
//判断两次输入的银行卡账号是否一致
jQuery.validator.addMethod("isSamebankNo", function(value, element) {
	var toid = $(element).attr('data-toid');
	var toidVal = $("#"+toid).val();
	value = value.replace(/\s/g,'');
	if(value == toidVal.replace(/\s/g,'')){
		return true;
	}else{
		return false;
	}

}, "两次输入的银行卡账号不一致");





