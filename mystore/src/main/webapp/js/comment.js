$(function(){
	 $("#commentTab li").click(function(){
		 $("#commentTab li").removeClass("curr");
		 $(this).addClass("curr");
		 subtype = $(this).index();
		 jumppage(0);
	 });
     $("#goComment").click(function(){
        $("#commentsList").show();
     });
     $("#comment_content").keyup(function(){
     	 var len = $.trim($(this).val()).length;
     	 $("#remnum_commnum").text("剩余"+(100-len)+"字符");
     });
     $("#reset_comment").click(function(){
     	$("#comment_content").val('');
     	$("#remnum_commnum").text("剩余100字符");
     });
     $("#save_comment").click(function(){
    	if(!isLogin()){
        	alert("请选登录再评论!");
         	return false;
        }
     	if($("input[name='comment_rank']:checked").val() == ''){
     	   alert("请选择评价等级!");
     	   return false;
     	}
     	if($.trim($("#comment_content").val()).length == 0){
     		alert("请输入评价内容!");
     		return false;
     	}
     	alert($("#comment_content").val());
     	$.ajax({
							url:'comment_save.dhtml',
							type: 'post',
							data: {'proId':$.trim($("#proId").val()),'score':$("input[name='comment_rank']:checked").val(),'content':$.trim($("#comment_content").val())},
							async: false,
							dataType: "text",
							success:function(data){
				                if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
				                }else if(data == -1){
				                   alert("提示：服务异常！");
				                }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				 });
     });
});
function isLogin(){
	var tf = false;
	jQuery.ajax({
        type:'post',
        url:$("#path").val()+'/user_isLogin.dhtml',
        data:{},
        dataType:'text',
        cache:false,
        async:false,
        success:function(data){
            if(data > 0){
            	tf = true;
			}else if(data == -1){
				alert("系统异常");
			}
        },
        error:function(){
        	alert("系统异常");
        }
   });
   return tf;
}