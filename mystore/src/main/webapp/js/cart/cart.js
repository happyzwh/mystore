var regExp = /^[1-9]\d*$/;
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
	$(".checkAll").click(function(){
		if($(this).is(':checked')){
			$(".checkAll").attr("checked","true");
			$(".check").attr("checked","true");
		}else{
			$(".checkAll").removeAttr("checked");
			$(".check").removeAttr("checked");
		}
	});
	$(".check").click(function(){
		if($(this).is(':checked')){
			var count = $(".check:checked").length;
			var total = $(".check").length;
			if(count == total){
				$(".checkAll").attr("checked","true");
			}
		}else{
			$(".checkAll").removeAttr("checked");
		}
	});
	$(".increment").click(function(){
		var num = $(this).prev().val();
		if(num == '')num=0;
		if(!regExp.test(num)){
			num=0;
		}
		num = parseInt(num)+1;
		$(this).prev().val(num);
		$(".goodsNum").change();
	});
	$(".decrement").click(function(){
		var num = $(this).next().val();
		if(num == '')num=2;
		if(!regExp.test(num)){
			num=2;
		}
		num = parseInt(num) - 1;
		if(num < 1) num = 1;
		$(this).next().val(num);
		$(".goodsNum").change();
	});
	$(".goodsNum").keyup(function(){
		if($.trim($(this).val()) == ''){
			$(this).val(1);
			return false;
		}
		if(!regExp.test($(this).val())){
			$(this).val(1);
			return false;
		}
	});
	$(".goodsNum").change(function(){
		var num = 0;
		var cart = "";
		$(".goodsNum").each(function(){
			var value= $.trim($(this).val());
			var id = $.trim($(this).attr("alt"));
			if(cart != '')cart += '-';
			cart += id+','+value;
			num += parseInt($.trim($(this).val()));
		});
		$(".cart_num").text(num);
		$.ajax({
		 	url: path+'/cart/cartAction!coverCart.dhtml',
		 	data: {'cart':cart}
		});
	});
	$('.cart-remove').click(function(){
		var id = $.trim($(this).attr("alt"));
		$(".item[id="+id+"]").remove();
		var num = 0;
		var cart = "";
		$(".goodsNum").each(function(){
			var value= $.trim($(this).val());
			var id = $.trim($(this).attr("alt"));
			if(cart != '')cart += '-';
			cart += id+','+value;
			num += parseInt($.trim($(this).val()));
		});
		$(".cart_num").text(num);
		$.ajax({
		 	url: path+'/cart/cartAction!coverCart.dhtml',
		 	data: {'cart':cart}
		});
	});
});