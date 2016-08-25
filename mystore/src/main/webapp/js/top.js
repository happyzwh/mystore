var path;
$(function(){
	 path = $("#path").val();
	 $(".list_pro_search li").live("click",function(){
         if($(this).attr("name") != ""){
             window.top.location.href=path+$(this).attr("name");   
         }
     });
	 $("#keys").blur(function(){
		 if($(".list_pro_search").is(":visible")){
			 $(".list_pro_search").delay(500).hide(0);
		 }
	 });
     $("#keys").keyup(function(){
         if($.trim($(this).val()) == ''){
             $(this).val('');
             $(".list_pro_search").hide();
             $(".list_pro_search ul").empty();
         }else{
            $.ajax({
					url: path+'/search/searchAction!mhSearch.dhtml',
					type: 'post',
					data: {'keys':$.trim($("#keys").val())},
					async: true,
					dataType: "json",
					success:function(data){
					     $(".list_pro_search ul").empty();
					     if(data && data.returnCode == 0 && data.list && data.list.length > 0){
					          var content = [];
					          $.each(data.list,function(ind,ent){
					             if(ind == data.list.length-1){
					           	 	content.push('<li  class="li_last" name="'+ent.url+'">');
					           	 }else{
					           	    content.push('<li name="'+ent.url+'">'); 
					           	 }
					           	 content.push(ent.name);
					             content.push('</li>');
					          });
					          if(content.lenght == 0)content.push('<li class="li_last">暂无相关商品</li>');
					          $(".list_pro_search ul").append(content.join(''));
					         
					     }else{
					     	$(".list_pro_search ul").append('<li class="li_last">暂无相关商品</li>');
					     }
					     $(".list_pro_search").show();
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		 	});
         }
     });
     $(".catItem").hover(
    		 function(){
		    	 $(this).addClass("on");
		    	 $(this).parent().parent().find(".menuBox").eq($(this).index()).show();
		    	 $(this).css({"border-left":"1px solid #ce171f"});
		     },
		     function(){
		    	 $(this).removeClass("on");
		    	 $(this).parent().parent().find(".menuBox").eq($(this).index()).hide();
		    	 $(this).css({"border-left":"1px solid #ffffff"});
		     }
     );
     $(".menuBox").hover(
    		 function(){
		    	 $(this).show();
		    	 $(this).parent().parent().find(".catItem").eq($(this).index()-1).addClass("on");
		       	 $(this).parent().parent().find(".catItem").eq($(this).index()-1).css({"border-left":"1px solid #ce171f"});
		     },
		     function(){
		    	 $(this).hide();
		    	 $(this).parent().parent().find(".catItem").eq($(this).index()-1).removeClass("on");
		    	 $(this).parent().parent().find(".catItem").eq($(this).index()-1).css({"border-left":"1px solid #ffffff"});
		     }
     );
});