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
		
		var data = selectedGoodsNum();
		$('#selectedGoodsNum').text(data.num);
		$('#sumPrice').text('¥'+data.totalPrice.toFixed(2));
		$('#totalRePrice').text('-¥'+(data.totalMarkPrice - data.totalPrice).toFixed(2));
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
		
		var data = selectedGoodsNum();
		$('#selectedGoodsNum').text(data.num);
		$('#sumPrice').text('¥'+data.totalPrice.toFixed(2));
		$('#totalRePrice').text('-¥'+(data.totalMarkPrice - data.totalPrice).toFixed(2));
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
		
		var id = $.trim($(this).parent().attr("alt"));
		var price = parseFloat($('.price[alt='+id+']').text());
	    var markPrice = parseFloat($('.markPrice[alt='+id+']').text());
	    
	    $('.totalPrice[alt='+id+']').text((num*price).toFixed(2));
	    $('.totalMarkPrice[alt='+id+']').text((num*markPrice).toFixed(2));
		
		var data = selectedGoodsNum();
		$('#selectedGoodsNum').text(data.num);
		$('#sumPrice').text('¥'+data.totalPrice.toFixed(2));
		$('#totalRePrice').text('-¥'+(data.totalMarkPrice - data.totalPrice).toFixed(2));
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
		
		var id = $.trim($(this).parent().attr("alt"));
		var price = parseFloat($('.price[alt='+id+']').text());
	    var markPrice = parseFloat($('.markPrice[alt='+id+']').text());
	    
	    $('.totalPrice[alt='+id+']').text(num*price.toFixed(2));
	    $('.totalMarkPrice[alt='+id+']').text(num*markPrice.toFixed(2));
		
		var data = selectedGoodsNum();
		$('#selectedGoodsNum').text(data.num);
		$('#sumPrice').text('¥'+data.totalPrice.toFixed(2));
		$('#totalRePrice').text('-¥'+(data.totalMarkPrice - data.totalPrice).toFixed(2));
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
function selectedGoodsNum(){
	var data = {};
	var num = 0;
	var totalPrice = 0;
	var totalMarkPrice = 0;
	$('.check:checked').each(function(){
		var id = $.trim($(this).attr('alt'));
		num += parseInt($(".goodsNum[alt="+id+"]").val());
		totalPrice += parseFloat($(".totalPrice[alt="+id+"]").text());
		totalMarkPrice += parseFloat($(".totalMarkPrice[alt="+id+"]").text());
	});
	data.num = num;
	data.totalPrice = totalPrice;
	data.totalMarkPrice = totalMarkPrice;
	return data;
}


