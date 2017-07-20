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
	
});