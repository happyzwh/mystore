function setCookie(name,value,expires){
	var exp = new Date();
	exp.setTime(exp.getTime() + expires);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg)){
		return unescape(arr[2].substring(1,arr[2].length-1));
	}else{
		return null;
	}
}
function delCookie(name){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if(cval != null){
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
}
function addCart(proId,count){
	$.ajax({
		url:'cart_addCart.dhtml',
		type: 'post',
		data: {'proId':proId,'count':count},
		async: true,
		dataType: "json",
		success:function(data){
			if(data != null && data.code == 1){
				$(".cart_num").text(data.count);
			}
		},
		error:function(){
		}
	});
}
