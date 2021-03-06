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
		
		updateSelectedGoods();
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
		
		updateSelectedGoods();
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
		
		updatePirce(num,id);
		
		if($('.check[alt='+id+']').is(':checked')){
			updateSelectedGoods();
		}
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

		updatePirce(num,id);
		
		if($('.check[alt='+id+']').is(':checked')){
			updateSelectedGoods();
		}
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
		
		var id = $(this).attr("alt");
		
		updatePirce($.trim($(this).val()),id);
		
		if($('.check[alt='+id+']').is(':checked')){
			updateSelectedGoods();
		}
		
		$.ajax({
		 	url:'cart_coverCart.dhtml',
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
		
		updateSelectedGoods();
		
		$.ajax({
		 	url:'cart_coverCart.dhtml',
		 	data: {'cart':cart}
		});
	});
	$("#toOrder").click(function(){
		if($(".check:checked").length==0){
			alert("请选择商品");
			return false;
		}
		var orderGoods = [];
		$.each($(".check:checked"),function(m,v) {
			if(orderGoods.toString().length != 0){
				orderGoods.push("_");
			}
			orderGoods.push($.trim($(this).attr('alt'))+","+$(".goodsNum[alt="+$(this).attr('alt')+"]").val());
		});
		$("#orderGoods").val(orderGoods.join(''));
		$("#myform").submit();
	});
});
function selectedGoods(){
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
function updateSelectedGoods(){
	var data = selectedGoods();
	$('#selectedGoodsNum').text(data.num);
	$('#sumPrice').text('¥'+data.totalPrice.toFixed(2));
	$('#totalRePrice').text('-¥'+(data.totalMarkPrice - data.totalPrice).toFixed(2));
}
function updatePirce(num,id){
	var price = parseFloat($('.price[alt='+id+']').text());
    var markPrice = parseFloat($('.markPrice[alt='+id+']').text());
    $('.totalPrice[alt='+id+']').text((num*price).toFixed(2));
    $('.totalMarkPrice[alt='+id+']').text((num*markPrice).toFixed(2));
}
