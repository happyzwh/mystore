var re = /(\d{3})(\d{4})(\d{4})/g;
$(function(){
	$('.add').click(function(){
		$("#addressId").val('');
		$('.popMask').show();
		$('.addresPopBox').show();
		$(".addPro").hide();
		$("#receiver").val('');
		initPrivince();
		initCity();
		initCounty();
		$("#address").val('');
		$("#mobile").val('');
		
	});
	$('.popClose').click(function(){
		$('.popMask').hide();
		$('.addresPopBox').hide();
	});
	$('.addressList .item').live('click',function(){
		$('.addressList .item').removeClass('on');
		$(this).addClass('on');
	});
	initPrivince();
	initCity();
	initCounty();
	
	$("#receiver").focus(function(){
		$(this).parent().find(".addPro").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).parent().find(".addPro").show();
		}else{
			$(this).parent().find(".addPro").hide();
		}
	});
	
	$("#address").focus(function(){
		$(this).parent().find(".addPro").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).parent().find(".addPro").show();
		}else{
			$(this).parent().find(".addPro").hide();
		}
	});
	
	$("#mobile").focus(function(){
		$(this).parent().find(".addPro").hide();
	}).blur(function(){
		if($.trim($(this).val())==''){
			$(this).parent().find(".addPro").text("请输入收货人手机号");
			$(this).parent().find(".addPro").show();
		}else{
			if(!mobileValide($.trim($(this).val()))){
				$(this).parent().find(".addPro").text("手机号格式错误");
				$(this).parent().find(".addPro").show();
				return false
			}
			$(this).parent().find(".addPro").hide();
		}
	});
	
	$("#provinceId").change(function(){
		setCity();
		setCounty();
	});
	
	$("#cityId").change(function(){
		setCounty();
	});
	
	$("#provinceId,#cityId,#countyId").change(function(){
		checkArea();
	});
	
	$(".addAlter").live('click',function(){
		var id = $(this).attr("id");
		$("#addressId").val(id);
		$("#receiver").val($("#"+id+"_receiver").val());
		$("#address").val($("#"+id+"_address").val());
		$("#mobile").val($("#"+id+"_mobile").val());
		initPrivince($("#"+id+"_provinceId").val());
		initCity($("#"+id+"_cityId").val());
		initCounty($("#"+id+"_countyId").val());
		$('.popMask').show();
		$('.addresPopBox').show();
	});
	$(".addDelete").live('click',function(){
		var id = $.trim($(this).attr("id"));
		if(confirm("确定删除？")){
			jQuery.ajax({
	            type:'post',
	            url:$("#path").val()+'/address_delete.dhtml',
	            data:{'id':id},
	            dataType:'text',
	            cache:false,
	            async:false,
	            success:function(data){
	            	if(data == 1){
	            		$('.addressList .item[name="'+id+'"]').remove();
	            		alert("删除城功!");
	            	}
	            }
			});  	
		}
	});
	$(".addSubmit").click(function(){
		if($.trim($("#receiver").val()) == ''){
			$("#receiver").parent().find(".addPro").show();
			return false;
		}
		if(!checkArea()){
			return false;
		}
		if($.trim($("#address").val())==''){
			$("#address").next().find(".accNotic").show();
			return false;
		}
		if($.trim($("#mobile").val())==''){
			$("#mobile").parent().find(".addPro").show();
			return false;
		}else if(!mobileValide($.trim($("#mobile").val()))){
			$("#mobile").parent().find(".addPro").text("手机号格式错误");
			$("#mobile").parent().find(".addPro").show();
			return false
		}
		jQuery.ajax({
            type:'post',
            url:$("#path").val()+'/address_edit.dhtml',
            data:{'id':$.trim($("#addressId").val()),'receiver':$.trim($("#receiver").val()),'addre':$.trim($("#address").val()),'mobile':$.trim($("#mobile").val()),
            	'provinceId':$.trim($("#provinceId").val()),'cityId':$.trim($("#cityId").val()),'countyId':$.trim($("#countyId").val())},
            dataType:'text',
            cache:false,
            async:false,
            success:function(data){
                if(data > 0){
					alert("保存成功");
					if($.trim($("#addressId").val()) != ''){
						var id= $.trim($("#addressId").val());
						$("#"+id+"_receiver").val($.trim($("#receiver").val()));
						$("#"+id+"_address").val($.trim($("#address").val()));
						$("#"+id+"_mobile").val($.trim($("#mobile").val()));
						$("#"+id+"_provinceId").val($.trim($("#provinceId").val()));
						$("#"+id+"_cityId").val($.trim($("#cityId").val()));
						$("#"+id+"_countyId").val($.trim($("#countyId").val()));
					    
						$("#"+id+"_receiver_text").text($.trim($("#receiver").val()));
						$("#"+id+"_area_text").text($("#provinceId option:selected").text()+$("#cityId option:selected").text()+$("#countyId option:selected").text()+$.trim($("#address").val()));
						$("#"+id+"_mobile_text").text($.trim($("#mobile").val()).replace(re,"$1****$3"));
					
					}else{
						var content = [];
						
						content.push('<li name="'+data+'" class="item" einv="false">');
							content.push('<input type="hidden" id="'+data+'_receiver" value="'+$.trim($("#receiver").val())+'"/>');
							content.push('<input type="hidden" id="'+data+'_provinceId" value="'+$.trim($("#provinceId").val())+'"/>');
							content.push('<input type="hidden" id="'+data+'_cityId" value="'+$.trim($("#cityId").val())+'"/>');
							content.push('<input type="hidden" id="'+data+'_countyId" value="'+$.trim($("#countyId").val())+'"/>');
							content.push('<input type="hidden" id="'+data+'_address" value="'+$.trim($("#address").val())+'"/>');
							content.push('<input type="hidden" id="'+data+'_mobile" value="'+$.trim($("#mobile").val())+'"/>');
							content.push('<div class="address-tit">');
								content.push('<b id="'+data+'_receiver_text">'+$.trim($("#receiver").val())+'</b>');
								content.push('<span id="'+data+'_mobile_text">'+$.trim($("#mobile").val()).replace(re,"$1****$3")+'</span>');
							content.push('</div>');
							content.push('<div class="address-con" id="'+data+'_area_text">'+$("#provinceId option:selected").text()+$("#cityId option:selected").text()+$("#countyId option:selected").text()+$.trim($("#address").val())+'</div>');
							content.push('<div class="address-ope">');
								content.push('<a class="addAlter" href="javascript:;" id="'+data+'">修改</a>');
								content.push('<a class="addDelete" href="javascript:;" id="'+data+'">删除</a>');
							content.push('</div>');
							content.push('<div class="defaultBtn" id="'+data+'">设为默认地址</div>');
							content.push('<div class="addDefault oIcon"></div>');
						content.push('</li>');
						$(content.join('')).insertBefore(".add");
					}
					$('.popMask').hide();
					$('.addresPopBox').hide();
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
	$("#invtop_type_personal").click(function(){
		$(".inv_top").hide();
	});
	$("#invtop_type_company").click(function(){
		$(".inv_top").show();
	});
	$(".subBtn").click(function(){
		var addressId = $(".addressList li.on").attr("name");
		if(addressId == ''){
			alert("请设置收货地址");
		}
		var payWay = $("input[name='sel_pay']").val();
		if(payWay == ''){
			alert("请选择支付方式");
		}
		if($("#is_inv:checked")){
			$("#isInv").val(1);
			$("#invType").val($("#inv_type:checked").val());
			$("#invToptype").val($("#invtop_type:checked").val());
			$("#invTop").val($("#inv_top").val());
			$("#invCon").val($("#inv_con:checked").val());
		}
		$("#addressId").val(addressId);
		$("#payWay").val(payWay);
		$("#myform").submit();
	});
});
function checkArea(){
	if($("#provinceId").val() =='' || $("#cityId").val() =='' || $("#countyId").val() ==''){
		$("#provinceId").parent().find(".addPro").show();
		return false;
	}else{
		$("#provinceId").parent().find(".addPro").hide();
	}
	return true;
}
function setPrivince(){
	$("#provinceId").empty();
	$("#cityId").empty();
	$("#countyId").empty();
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {   
		content.push("<option value='"+value.id+"'>"+value.name+"</option>");       
    });  
    $("#provinceId").append(content.join(""));   
}
function initPrivince(privinceId){
	setPrivince();
	if(privinceId != null){
	    $("#provinceId").val(privinceId);
	}
}
function setCity(){
	$("#cityId").empty();
	$("#countyId").empty();
	if($("#provinceId").val() == ''){
		$("#cityId").append("<option value=''>请选择</option>");
		return false;
	}
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {  
		if(value.id == $("#provinceId").val()){
			$.each(value.sons,function(m,v) { 
				content.push("<option value='"+v.id+"'>"+v.name+"</option>");  
			});
		}
    });  
    $("#cityId").append(content.join(""));   
}
function initCity(cityId){
	setCity();
	if(cityId != null){
	    $("#cityId").val(cityId);
	}
}
function setCounty(){
	$("#countyId").empty();
	if($("#provinceId").val() == '' || $("#cityId").val() == ''){
		$("#countyId").append("<option value=''>请选择</option>");
		return false;
	}
	var content = [];
	content.push("<option value=''>请选择</option>");
	$.each(region,function(n,value) {  
		if(value.id == $("#provinceId").val()){
			$.each(value.sons,function(m,v) { 
				if(v.id == $("#cityId").val()){
					$.each(v.sons,function(na,va) { 
						content.push("<option value='"+va.id+"'>"+va.name+"</option>");  
					});
				}
			});
		}
    });   
    $("#countyId").append(content.join(""));   
}
function initCounty(countyId){
	setCounty();
	if(countyId != null){
	    $("#countyId").val(countyId);
	}
}