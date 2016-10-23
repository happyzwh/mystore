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

		// 图片上下滚动
		var count = $("#imageMenu li").length - 5; /* 显示 6 个 li标签内容 */
		var interval = $("#imageMenu li:first").width();
		var curIndex = 0;
		
		$('.scrollbutton').click(function(){
			if( $(this).hasClass('disabled') || $("#imageMenu li").length == 6) return false;
			
			if ($(this).hasClass('smallImgUp')) --curIndex;
			else ++curIndex;
			
			$('.scrollbutton').removeClass('disabled');
			if (curIndex == 0) $('.smallImgUp').addClass('disabled');
			if (curIndex == count-1) $('.smallImgDown').addClass('disabled');
			
			$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
		});	
		// 解决 ie6 select框 问题
		$.fn.decorateIframe = function(options) {
	        if ($.browser.msie && $.browser.version < 7) {
	            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
	            $(this).each(function() {
	                var $myThis = $(this);
	                //创建一个IFRAME
	                var divIframe = $("<iframe />");
	                divIframe.attr("id", opts.iframeId);
	                divIframe.css("position", "absolute");
	                divIframe.css("display", "none");
	                divIframe.css("display", "block");
	                divIframe.css("z-index", opts.iframeZIndex);
	                divIframe.css("border");
	                divIframe.css("top", "0");
	                divIframe.css("left", "0");
	                if (opts.width == 0) {
	                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                if (opts.height == 0) {
	                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
	                }
	                divIframe.css("filter", "mask(color=#fff)");
	                $myThis.append(divIframe);
	            });
	        }
	    };
	    $.fn.decorateIframe.defaults = {
	        iframeId: "decorateIframe1",
	        iframeZIndex: -1,
	        width: 0,
	        height: 0
	    };
	    //放大镜视窗
	    $("#bigView").decorateIframe();
	    //点击到中图
	    var midChangeHandler = null;
		
	    $("#imageMenu li img").bind("click", function(){
			if ($(this).attr("id") != "onlickImg") {
				midChange($(this).attr("src").replace("small", "mid"));
				$("#imageMenu li").removeAttr("id");
				$(this).parent().attr("id", "onlickImg");
			}
		}).bind("mouseover", function(){
			if ($(this).attr("id") != "onlickImg") {
				window.clearTimeout(midChangeHandler);
				midChange($(this).attr("src").replace("small", "mid"));
				$(this).css({ "border": "3px solid #959595" });
			}
		}).bind("mouseout", function(){
			if($(this).attr("id") != "onlickImg"){
				$(this).removeAttr("style");
				midChangeHandler = window.setTimeout(function(){
					midChange($("#onlickImg img").attr("src").replace("small", "mid"));
				}, 1000);
			}
		});
	    function midChange(src) {
	        $("#midimg").attr("src", src).load(function() {
	            changeViewImg();
	        });
	    }
	    //大视窗看图
	    function mouseover(e) {
	        if ($("#winSelector").css("display") == "none") {
	            $("#winSelector,#bigView").show();
	        }
	        $("#winSelector").css(fixedPosition(e));
	        e.stopPropagation();
	    }
	    function mouseOut(e) {
	        if ($("#winSelector").css("display") != "none") {
	            $("#winSelector,#bigView").hide();
	        }
	        e.stopPropagation();
	    }
	    $("#midimg").mouseover(mouseover); //中图事件
	    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件

	    var $divWidth = $("#winSelector").width(); //选择器宽度
	    var $divHeight = $("#winSelector").height(); //选择器高度
	    var $imgWidth = $("#midimg").width(); //中图宽度
	    var $imgHeight = $("#midimg").height(); //中图高度
	    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

	    function changeViewImg() {
	        $("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));
	    }
	    changeViewImg();
	    $("#bigView").scrollLeft(0).scrollTop(0);
	    function fixedPosition(e) {
	        if (e == null) {
	            return;
	        }
	        var $imgLeft = $("#midimg").offset().left; //中图左边距
	        var $imgTop = $("#midimg").offset().top; //中图上边距
	        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
	        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
	        X = X < 0 ? 0 : X;
	        Y = Y < 0 ? 0 : Y;
	        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
	        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

	        if ($viewImgWidth == null) {
	            $viewImgWidth = $("#bigView img").outerWidth();
	            $viewImgHeight = $("#bigView img").height();
	            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
	                $viewImgWidth = $viewImgHeight = 800;
	            }
	            $height = $divHeight * $viewImgHeight / $imgHeight;
	            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
	            $("#bigView").height($height);
	        }
	        var scrollX = X * $viewImgWidth / $imgWidth;
	        var scrollY = Y * $viewImgHeight / $imgHeight;
	        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
	        $("#bigView").css({ "top": 0, "left":$(".preview").width() });

	        return { left: X, top: Y };
	    }
	    
	    $("#number").keyup(function(){
	    	if($.trim($("#number").val())==''){
	    		$("#number").val(1);
	    	}
	    	var reg = new RegExp("^[1-9][0-9]*$");
	        if(!reg.test($("#number").val())){
	        	$("#number").val(1);
	        }
	    });
	    
	    $("#decrease").click(function(){
	    	if(parseInt($("#number").val()) > 0){
	    		$("#number").val(parseInt($("#number").val())-1);
	    	}
	    });
	    
	    $("#increase").click(function(){
	    		$("#number").val(parseInt($("#number").val())+1);
	    });
	    
	    $(".tab_menu li").click(function(){
	    	$(".tab_menu li").removeClass("select");  
	    	$(this).addClass("select");
	    	$(".detail").hide();
	    	$("."+$(this).attr("name")).show();
	    	if($(this).index() == 1){
	    		type = 0;
	    		subtype=0;
	    		getCommentContent(0,1,pageSize);
	    	}else if($(this).index() == 2){
	    		type = 1;
	    		subtype=0;
	    		getConsultContent(0,1,pageSize);
	    	}
	    });    
});
var type=0;
var subtype=0;
var pageSize = 10;
function getCommentContent(type,pageNo,pageSize){
	$(".goodscomment").load("comment_getCommentPage.dhtml", {'proId':$("#proId").val(),'type':type,'pageNo':pageNo,'pageSize':pageSize}, function(){});
}
function getConsultContent(type,pageNo,pageSize){
	$(".goodsconsult").load("consult_getConsultPage.dhtml", {'proId':$("#proId").val(),'type':type,'pageNo':pageNo,'pageSize':pageSize}, function(){});
}
function jumppage(pageNo){
	if(type == 0){
		$(".goodscomment").load("comment_getCommentPage.dhtml", {'proId':$("#proId").val(),'type':subtype,'pageNo':pageNo,'pageSize':pageSize}, function(){});
	}else if(type == 1){
		$(".goodsconsult").load("consult_getConsultPage.dhtml", {'proId':$("#proId").val(),'type':subtype,'pageNo':pageNo,'pageSize':pageSize}, function(){});
	}
}
	
