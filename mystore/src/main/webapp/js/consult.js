$(function(){
	 $("#consultTab li").click(function(){
		 $("#consultTab li").removeClass("curr");
		 $(this).addClass("curr");
		 subtype = $(this).index();
		 jumppage(0);
	 });
     $("#goConsult").click(function(){
        $("#consultsList").show();
     });
     $("#consult_content").keyup(function(){
     	 var len = $.trim($(this).val()).length;
     	 $("#remnum_connum").text("剩余"+(100-len)+"字符");
     });
     $("#reset_consult").click(function(){
     	$("#consult_content").val('');
     	$("#remnum_connum").text("剩余100字符");
     });
     $("#save_consult").click(function(){
     	if($("input[name='consult_type']:checked").val() == ''){
     	   alert("请选择咨 询类别!");
     	   return false;
     	}
     	if($.trim($("#consult_content").val()).length == 0){
     		alert("请输入咨询内容!");
     		return false;
     	}
     	$.ajax({
							url:'consult_save.dhtml',
							type: 'post',
							data: {'proId':$.trim($("#proId").val()),'type':$("input[name='consult_type']:checked").val(),'content':$.trim($("#consult_content").val())},
							async: true,
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