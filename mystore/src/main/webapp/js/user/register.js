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