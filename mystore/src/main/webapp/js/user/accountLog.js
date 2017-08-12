function jumppage(pageNo){
	if(type == 0){
		$(".accountlog").load("account_list.dhtml", {'pageNo':pageNo,'pageSize':pageSize}, function(){});
	}
}