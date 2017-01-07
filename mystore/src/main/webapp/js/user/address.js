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
	
	$("#receiver").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic").show();
		}else{
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#address").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic").show();
		}else{
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#mobile").focus(function(){
		$(this).next().find(".accNotic").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).next().find(".accNotic span").text("请输入收货人手机号");
			$(this).next().find(".accNotic").show();
		}else{
			if(!mobileValide($.trim($(this).val()))){
				$(this).next().find(".accNotic span").text("手机号格式错误码");
				$(this).next().find(".accNotic").show();
				return false
			}
			$(this).next().find(".accNotic").hide();
		}
	});
	
	$("#save").click(function(){
		if($.trim($("#receiver").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#address").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#mobile").val())==''){
			$(this).next().find(".accNotic").show();
			return false;
		}else if(!mobileValide($.trim($("#mobile").val()))){
			$(this).next().find(".accNotic span").text("手机号格式错误码");
			$(this).next().find(".accNotic").show();
			return false
		}
		
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/address_edit.dhtml',
            data:{'id':$.trim($("#id").val()),'receiver':$.trim($("#receiver").val()),'addre':$.trim($("#address").val()),'mobile':$.trim($("#mobile").val())},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
                if(data == 1){
					alert("保存成功");
					window.location.href = $("#path").val()+'/address_index.dhtml';
				}else if(data == -1){
					alert("系统异常");
				}else if(data == -2){
					alert("参数错误");
				}
            },
            error:function(){
            	alert("系统异常");
            }
       });
	});
});
function deleteAddress(id){
	jQuery.ajax({
        type:'post',
        url:$("#path").val()+'/address_delete.dhtml',
        data:{'id':id},
        dataType:'text',
        cache:false,
        async:false,
        success:function(data){
            if(data == 1){
				alert("删除成功");
				window.location.href = $("#path").val()+'/address_index.dhtml';
			}else if(data == -1){
				alert("系统异常");
			}else if(data == -2){
				alert("参数错误");
			}
        },
        error:function(){
        	alert("系统异常");
        }
   });
}