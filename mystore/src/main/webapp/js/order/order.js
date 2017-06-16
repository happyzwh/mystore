$(function(){
	$('.add').click(function(){
		$('.popMask').show();
		$('.addresPopBox').show();
	});
	$('.popClose').click(function(){
		$('.popMask').hide();
		$('.addresPopBox').hide();
	});
	$('.addressList .item').click(function(){
		$('.addressList .item').removeClass('on');
		$(this).addClass('on');
	});
});