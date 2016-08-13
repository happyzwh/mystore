package com.mystore.business.common;


public class PageInfo{
	
	public static String setPage(Integer pageNo,Integer pageSize,Integer totalPages,Integer totalResults){
	                 StringBuilder page= new StringBuilder("<div class=\"pager\"><div class=\"a\">");
	                 if(totalPages==1){
	                	 page.append("<a href=\"javascript:void(0);\" title=\"上一页\">上一页</a>&nbsp;&nbsp;");
	                	 	page.append("<b>1</b>&nbsp;");
	                			 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\">下一页</a>");
	                 }
	                 int totle=5;
	                 if(totalPages>1){
	                     if(pageNo>1){
	                    	 page.append("<a href=\"javascript:void(0);\" title=\"上一页\" onclick=\"javascrpt:jumppage("+(pageNo-1)+");\">上一页</a>&nbsp;&nbsp;");
	                     }else if(pageNo==1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"上一页\">上一页</a>");
	                     }
	                     
	                     StringBuilder content = new StringBuilder("");
	                     StringBuilder left = new StringBuilder("");
	                     int leftlength=0;
	                     StringBuilder right = new StringBuilder("");
	                     int rightlength=0;
	                     StringBuilder midle = new StringBuilder("");
	                     for(int i=pageNo-totle/2;i<pageNo;i++){
	                         if(i>0){
	                            left.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+i+");\">"+i+"</a>&nbsp;&nbsp;");
	                            leftlength++;
	                         }
	                     }
	                     midle.append("&nbsp;<b>"+pageNo+"</b>&nbsp;");
	                     for(int i=pageNo+1;i<=pageNo+totle/2;i++){
	                         if(i<=totalPages){
	                             right.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+i+");\">"+i+"</a>&nbsp;");
	                             rightlength++;
	                         }
	                     }
	                     
	                     if(leftlength+rightlength < totle-1){
	                        if(pageNo-leftlength-1>0 || pageNo+rightlength<totalPages){
	                             while(leftlength+rightlength<totle-1){
	                                 if(pageNo-leftlength-1>0){
	                                     leftlength++;
	                                     left = new StringBuilder("&nbsp;<a href=\"javascript:void(0);\"  onclick=\"javascrpt:jumppage("+(pageNo-leftlength)+");\">"+(pageNo-leftlength)+"</a>&nbsp;").append(left);    
	                                 }else if(pageNo+rightlength<totalPages){
	                                     rightlength++;
	                                     right.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+(pageNo+rightlength)+");\">"+(pageNo+rightlength)+"</a>&nbsp;");    
	                                 }else break;
	                             }
	                         }
	                     }
	                     content.append(left.toString()).append(midle.toString()).append(right.toString());
	                    
	                     if(pageNo-leftlength-1 > 1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage(1);\">"+1+"</a><span>...</span>");
	                     }
	                     else if(pageNo-leftlength-1 == 1) {
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage(1);\">"+1+"</a>&nbsp;");
	                     }
	                     page.append(content);
	                    
	                     if(pageNo+rightlength < totalPages-1){
	                    	 page.append("<span>...</span><a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+totalPages+");\">"+totalPages+"</a>&nbsp;");
	                    }
	                     else if(pageNo+rightlength == totalPages-1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+totalPages+");\">"+totalPages+"</a>&nbsp;");
	                     }
	                    
	                     if(pageNo<totalPages){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\" onclick=\"javascrpt:jumppage("+(pageNo+1)+");\">下一页</a>&nbsp;");
	                     }
	                     else if(pageNo==totalPages){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\">下一页</a>&nbsp;");;
	                     }
	                 }
	                 page.append("</div></div>");;
	                 return page.toString();  
	}
	
	
}
