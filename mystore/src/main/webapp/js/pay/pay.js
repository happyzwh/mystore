$(function(){
	$(".paymenWrap ul li").mouseover(function(){
		$(this).addClass("on");
    }).mouseout(function(){
    	$(this).removeClass("on");
    }).click(function(){
    	$(".paymenWrap ul li b").hide();
    	$(".paymenWrap ul li").removeClass("selected");
    	$(this).addClass("selected");
    	$(this).find("b").show();
    });
	$(".payBtn").click(function(){
		if($(".paymenWrap ul li.selected").length == 0){
			alert("请选择支付平台");
			return false;
		}
		var payChannel = $(".paymenWrap ul li.selected").attr("value");
		if(payChannel == ''){
			alert("请选择支付平台");
			return false;
		}
		$("#payChannel").val(payChannel);
		$("#myform").submit();
	});
});