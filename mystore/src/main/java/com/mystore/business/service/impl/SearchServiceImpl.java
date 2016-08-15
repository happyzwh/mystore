package com.mystore.business.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.mystore.business.common.ConfigReader;
import com.mystore.business.common.Pager;
import com.mystore.business.dao.CategoryMapper;
import com.mystore.business.dao.SearchMapper;
import com.mystore.business.dto.Category;
import com.mystore.business.dto.DocPoJo;
import com.mystore.business.dto.SearchProPoJo;
import com.mystore.business.service.SearchService;
import com.mystore.business.util.CommUtils;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void createIndex() throws Exception{
		// TODO Auto-generated method stub
		try{
			System.out.println("-----------------创建索引开始!");
			List<DocPoJo> list = searchMapper.selectDocPoJo();
			if(list != null && list.size() > 0){
				
				Analyzer analyzer = new IKAnalyzer(true);
				Directory directory = new SimpleFSDirectory(new File(ConfigReader.getPath_index()));
			    IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			    iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			    IndexWriter iwriter = new IndexWriter(directory, iwConfig);
				
				for(DocPoJo docPojo:list){
					 Document doc = new Document();
					 
					 FieldType type_fieldType = new FieldType(); 
					 type_fieldType.setIndexed(false);
					 type_fieldType.setTokenized(false);
					 type_fieldType.setStored(true); 
					 Field type_field = new Field("type", String.valueOf(docPojo.getType()), type_fieldType); 
					 doc.add(type_field);
					 
					 FieldType id_fieldType = new FieldType(); 
					 id_fieldType.setIndexed(false);
					 id_fieldType.setTokenized(false);
					 id_fieldType.setStored(true); 
					 Field id_field = new Field("id", String.valueOf(docPojo.getId()), id_fieldType); 
					 doc.add(id_field);
					 
				     
					 FieldType name_fieldType = new FieldType(); 
					 name_fieldType.setIndexed(true);
					 name_fieldType.setTokenized(true);
					 name_fieldType.setStored(true); 
					 Field name_field = new Field("name", docPojo.getName(), name_fieldType); 
					 doc.add(name_field);
					 
					 
					 if(docPojo.getId_brand() != null){
						 FieldType id_brandfieldType = new FieldType(); 
						 id_brandfieldType.setIndexed(true);
						 id_brandfieldType.setTokenized(false);
						 id_brandfieldType.setStored(true); 
						 Field id_brandfield = new Field("id_brand", String.valueOf(docPojo.getId_brand()), id_brandfieldType); 
						 doc.add(id_brandfield);
						 
					 }
					 
					 if(docPojo.getId_cate() != null){
						 FieldType id_catefieldType = new FieldType(); 
						 id_catefieldType.setIndexed(true);
						 id_catefieldType.setTokenized(false);
						 id_catefieldType.setStored(true); 
						 Field id_catefield = new Field("id_cate", String.valueOf(docPojo.getId_cate()), id_catefieldType); 
						 doc.add(id_catefield);

					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getEnTitle())){
						 FieldType enTitle_fieldType = new FieldType(); 
						 enTitle_fieldType.setIndexed(true);
						 enTitle_fieldType.setTokenized(true);
						 enTitle_fieldType.setStored(true); 
						 Field enTitle_field = new Field("enTitle", docPojo.getEnTitle(), enTitle_fieldType); 
						 doc.add(enTitle_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getRome())){
						 FieldType rome_fieldType = new FieldType(); 
						 rome_fieldType.setIndexed(true);
						 rome_fieldType.setTokenized(true);
						 rome_fieldType.setStored(true); 
						 Field rome_field = new Field("rome", docPojo.getRome(), rome_fieldType); 
						 doc.add(rome_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getJianPin())){
						 FieldType jianPin_fieldType = new FieldType(); 
						 jianPin_fieldType.setIndexed(true);
						 jianPin_fieldType.setTokenized(true);
						 jianPin_fieldType.setStored(true); 
						 Field jianPin_field = new Field("jianPin", docPojo.getJianPin(), jianPin_fieldType); 
						 doc.add(jianPin_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getKeyWords())){
						 FieldType keyWords_fieldType = new FieldType(); 
						 keyWords_fieldType.setIndexed(true);
						 keyWords_fieldType.setTokenized(true);
						 keyWords_fieldType.setStored(true); 
						 Field keyWords_field = new Field("keyWords", docPojo.getKeyWords(), keyWords_fieldType); 
						 doc.add(keyWords_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getSn())){
						 FieldType sn_fieldType = new FieldType(); 
						 sn_fieldType.setIndexed(true);
						 sn_fieldType.setTokenized(false);
						 sn_fieldType.setStored(true); 
						 Field sn_field = new Field("sn", docPojo.getSn(), sn_fieldType); 
						 doc.add(sn_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getShortTitle())){
						 FieldType shortTitle_fieldType = new FieldType(); 
						 shortTitle_fieldType.setIndexed(true);
						 shortTitle_fieldType.setTokenized(true);
						 shortTitle_fieldType.setStored(true); 
						 Field shortTitle_field = new Field("shortTitle", docPojo.getShortTitle(), shortTitle_fieldType); 
						 doc.add(shortTitle_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getSubTitle())){
						 FieldType subTitle_fieldType = new FieldType(); 
						 subTitle_fieldType.setIndexed(true);
						 subTitle_fieldType.setTokenized(true);
						 subTitle_fieldType.setStored(true); 
						 Field subTitle_field = new Field("subTitle", docPojo.getSubTitle(), subTitle_fieldType); 
						 doc.add(subTitle_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getPath_pic())){
						 FieldType path_pic_fieldType = new FieldType(); 
						 path_pic_fieldType.setIndexed(false);
						 path_pic_fieldType.setTokenized(false);
						 path_pic_fieldType.setStored(true); 
						 Field path_pic_field = new Field("path_pic", docPojo.getPath_pic(), path_pic_fieldType); 
						 doc.add(path_pic_field);
					
					 }
					 
					 if(docPojo.getShopPrice() != null){
						 FieldType shopPrice_fieldType = new FieldType(); 
						 shopPrice_fieldType.setIndexed(true);
						 shopPrice_fieldType.setTokenized(false);
						 shopPrice_fieldType.setStored(true); 
						 Field shopPrice_field = new Field("shopPrice", String.valueOf(docPojo.getShopPrice()), shopPrice_fieldType); 
						 doc.add(shopPrice_field);
						 
					 }
					 
					 if(docPojo.getMarkPrice() != null){
						 FieldType markPrice_fieldType = new FieldType(); 
						 markPrice_fieldType.setIndexed(true);
						 markPrice_fieldType.setTokenized(false);
						 markPrice_fieldType.setStored(true); 
						 Field markPrice_field = new Field("markPrice", String.valueOf(docPojo.getMarkPrice()), markPrice_fieldType); 
						 doc.add(markPrice_field);
						 
					 }
					 
					 if(docPojo.getOnSaleTime() != null){
						 FieldType onSaleTime_fieldType = new FieldType(); 
						 onSaleTime_fieldType.setIndexed(true);
						 onSaleTime_fieldType.setTokenized(false);
						 onSaleTime_fieldType.setStored(true); 
						 Field onSaleTime_field = new Field("onSaleTime", CommUtils.transferDateToString(docPojo.getOnSaleTime(), CommUtils.DATE_FORMATE_24h2b), onSaleTime_fieldType); 
						 doc.add(onSaleTime_field);

					 }
					 
					 if(docPojo.getId_country() != null){
						 FieldType id_countryfieldType = new FieldType(); 
						 id_countryfieldType.setIndexed(true);
						 id_countryfieldType.setTokenized(false);
						 id_countryfieldType.setStored(true); 
						 Field id_countryfield = new Field("id_country", String.valueOf(docPojo.getId_country()), id_countryfieldType); 
						 doc.add(id_countryfield);
					 }
					 
					 if(docPojo.getId_province() != null){
						 FieldType id_provincefieldType = new FieldType(); 
						 id_provincefieldType.setIndexed(true);
						 id_provincefieldType.setTokenized(false);
						 id_provincefieldType.setStored(true); 
						 Field id_provincefield = new Field("id_province", String.valueOf(docPojo.getId_province()), id_provincefieldType); 
						 doc.add(id_provincefield);
					 }
					 
					 if(docPojo.getType() == 0){
						 List<Category> cate_list = getAllParentCategoryById(docPojo.getId_cate());
						 if(cate_list != null && cate_list.size() > 0){
							 StringBuilder ids_cate = new StringBuilder("");
							 for(Category cate:cate_list){
								 ids_cate.append(cate.getId()).append(" "); 
							 }
							 FieldType ids_catefieldType = new FieldType(); 
							 ids_catefieldType.setIndexed(true);
							 ids_catefieldType.setTokenized(true);
							 ids_catefieldType.setStored(true); 
							 Field ids_catefield = new Field("ids_cate",ids_cate.toString() , ids_catefieldType); 
							 doc.add(ids_catefield);
							 
						 }
					 }
					 
				     iwriter.addDocument(doc);
				}
				
				iwriter.close();
			}
			System.out.println("-----------------创建索引结束!");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private List<Category> getAllParentCategoryById(Integer id){
		List<Category> list = new ArrayList<Category>();
	    if(id != null){
	    	getCategoryById(list,id);
	    }
		return list;
	}
	
    private void getCategoryById(List<Category> list,Integer id){
    	Category cate = categoryMapper.getCateById(id);
    	if(cate != null){
	    	list.add(cate);
	    	if(cate.getPid() != null){
	    		getCategoryById(list,cate.getPid());
	    	}
    	}
    }
    
    /**
     *  分类-品牌-国籍-产地-价格区间-排序属性-排序
     * 
     * 
     * */
    public Pager<SearchProPoJo> search(Integer id_category,Integer id_brand,Integer id_country,Integer id_province,Integer price_low,Integer price_high,Integer type_sort,Integer type_asc,Integer pageNo,Integer pageSize){
    	
    	Pager<SearchProPoJo> pager = new Pager<SearchProPoJo>();
    	List<SearchProPoJo> list = new ArrayList<SearchProPoJo>();
    	pager.setResultList(list);
    	pager.setPageNo(pageNo);
    	pager.setPageSize(pageSize);
    	
    	try{
    		Analyzer analyzer = new IKAnalyzer(true);
			Directory directory = FSDirectory.open(new File(ConfigReader.getPath_index()));
//    		Directory directory = FSDirectory.open(new File("d:/index"));
			IndexReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			BooleanQuery booleanQuery = new BooleanQuery();
			Sort sort = null;
			SortField[] sortField = new SortField[2];
			if(id_category != null && id_category != 0){
				QueryParser ids_cate_queryParser = new QueryParser(Version.LUCENE_47, "ids_cate", analyzer);
				Query ids_cate_query = ids_cate_queryParser.parse(String.valueOf(id_category));
				booleanQuery.add(ids_cate_query, BooleanClause.Occur.MUST);
			}
			
			if(id_brand != null && id_brand != 0){
				QueryParser id_brand_queryParser = new QueryParser(Version.LUCENE_47, "id_brand", analyzer);
				Query id_brand_query = id_brand_queryParser.parse(String.valueOf(id_brand));
				booleanQuery.add(id_brand_query, BooleanClause.Occur.MUST);
			}
			
			if(id_country != null && id_country != 0){
				QueryParser id_country_queryParser = new QueryParser(Version.LUCENE_47, "id_country", analyzer);
				Query id_country_query = id_country_queryParser.parse(String.valueOf(id_country));
				booleanQuery.add(id_country_query, BooleanClause.Occur.MUST);
			}
			
			if(id_province != null && id_province != 0){
				QueryParser id_province_queryParser = new QueryParser(Version.LUCENE_47, "id_province", analyzer);
				Query id_province_query = id_province_queryParser.parse(String.valueOf(id_province));
				booleanQuery.add(id_province_query, BooleanClause.Occur.MUST);
			}
			
			if(price_low != null ||  price_high != null){
				
				boolean boolean_price_low = price_low==null?false:true;
				boolean boolean_price_high = price_high==null?false:true;
				
				Query shopPrice_query = NumericRangeQuery.newDoubleRange("shopPrice", price_low != null?Double.valueOf(price_low):null, price_high!= null?Double.valueOf(price_high):null, boolean_price_low, boolean_price_high);
				
				booleanQuery.add(shopPrice_query, BooleanClause.Occur.MUST);
			}
    		
			if(type_sort != null && type_sort != 0){
				String sortFieldName = null;
				SortField.Type type = null;
				if(type_sort == 1){
					sortFieldName="shopPrice";
					type = SortField.Type.DOUBLE;
				}else if(type_sort == 4){
					sortFieldName="onSaleTime";
					type = SortField.Type.STRING;
				}
				sortField[0]=new SortField(sortFieldName, type, ( type_asc==null || type_asc==0 )?true:false);
			}
			
			sortField[1]=new SortField("sort", SortField.Type.INT , true);
			
			sort = new Sort(sortField);
			
//			TopDocs hits = isearcher.search(booleanQuery,100);
//			ScoreDoc[] hit = hits.scoreDocs;
//			if (hit != null && hit.length > 0){
//	        	for (int i = 0; i < hit.length; i++){
//	        		int num = hit[i].doc;
//	        	    Document document = isearcher.doc(num);	
//	        	    
//	        	     SearchProPoJo searchProPoJo = new SearchProPoJo();
//	        	     list.add(searchProPoJo);
//	        	     
//	        	     searchProPoJo.setType(Integer.valueOf(document.get("type")));
//	        	     searchProPoJo.setId(Integer.valueOf(document.get("id")));
//	        	     searchProPoJo.setName(document.get("name"));
//	        	     searchProPoJo.setPath_pic(document.get("path_pic"));
//	        	     searchProPoJo.setMarkPrice(document.get("markPrice"));
//	        	     searchProPoJo.setShopPrice(document.get("shopPrice"));
//	        	}
//			}
			
			TopFieldCollector c = TopFieldCollector.create(sort, pageNo*pageSize, false, false, false, false);
			System.out.println(booleanQuery.toString());
			isearcher.search(booleanQuery, c);
	        ScoreDoc[] hits = c.topDocs((pageNo-1)*pageSize, pageSize).scoreDocs;
	        if (hits == null || hits.length < 1){
	        	pager.setRowCount(c.getTotalHits());
	        	System.out.println("-------------总记录:"+c.getTotalHits());
	        	for (int i = 0; i < hits.length; i++){
	        		int num = hits[i].doc;
	        	    Document document = isearcher.doc(num);	
	        	    
	        	     SearchProPoJo searchProPoJo = new SearchProPoJo();
	        	     list.add(searchProPoJo);
	        	     
	        	     searchProPoJo.setId(Integer.valueOf(document.get("id")));
	        	     searchProPoJo.setName(document.get("name"));
	        	     searchProPoJo.setPath_pic(document.get("path_pic"));
	        	     searchProPoJo.setMarkPrice(document.get("markPrice"));
	        	     searchProPoJo.setShopPrice(document.get("shopPrice"));
	        	}
	        }
	        
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    
    	return pager;
    }
    
    public List<SearchProPoJo> mhSearch(String keys){
    	
    	
    	List<SearchProPoJo> list = new ArrayList<SearchProPoJo>();
    	try{
    		Analyzer analyzer = new IKAnalyzer(true);
			Directory directory = FSDirectory.open(new File(ConfigReader.getPath_index()));
//    		Directory directory = FSDirectory.open(new File("d:/index"));
			IndexReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			BooleanQuery booleanQuery = new BooleanQuery();
    		
			QueryParser name_queryParser = new QueryParser(Version.LUCENE_47, "name", analyzer);
			Query name_query = name_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(name_query, BooleanClause.Occur.SHOULD);
			
			QueryParser enTitle_queryParser = new QueryParser(Version.LUCENE_47, "enTitle", analyzer);
			Query enTitle_query = enTitle_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(enTitle_query, BooleanClause.Occur.SHOULD);

			QueryParser rome_queryParser = new QueryParser(Version.LUCENE_47, "rome", analyzer);
			Query rome_query = rome_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(rome_query, BooleanClause.Occur.SHOULD);
			
			QueryParser jianPin_queryParser = new QueryParser(Version.LUCENE_47, "jianPin", analyzer);
			Query jianPin_query = jianPin_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(jianPin_query, BooleanClause.Occur.SHOULD);
			
			QueryParser keyWords_queryParser = new QueryParser(Version.LUCENE_47, "keyWords", analyzer);
			Query keyWords_query = keyWords_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(keyWords_query, BooleanClause.Occur.SHOULD);
			
			QueryParser sn_queryParser = new QueryParser(Version.LUCENE_47, "sn", analyzer);
			Query sn_query = sn_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(sn_query, BooleanClause.Occur.SHOULD);
			
			QueryParser shortTitle_queryParser = new QueryParser(Version.LUCENE_47, "shortTitle", analyzer);
			Query shortTitle_query = shortTitle_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(shortTitle_query, BooleanClause.Occur.SHOULD);
			
			QueryParser subTitle_queryParser = new QueryParser(Version.LUCENE_47, "subTitle", analyzer);
			Query subTitle_query = subTitle_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(subTitle_query, BooleanClause.Occur.SHOULD);
    		
			TopDocs hits = isearcher.search(booleanQuery,100);
			ScoreDoc[] hit = hits.scoreDocs;
			if (hit != null && hit.length > 0){
	        	for (int i = 0; i < hit.length; i++){
	        		int num = hit[i].doc;
	        	    Document document = isearcher.doc(num);	
	        	    
	        	     SearchProPoJo searchProPoJo = new SearchProPoJo();
	        	     list.add(searchProPoJo);

	        	     if(Integer.valueOf(document.get("type")) == 0){
	        	    	 searchProPoJo.setUrl("/pro/proAction!detail.dhtml?id="+document.get("id"));
	        	     }else if(Integer.valueOf(document.get("type")) == 1){
	        	    	 searchProPoJo.setUrl("/pro/proAction!list.dhtml?keys="+document.get("id")+"_"+0+"_"+0+"_"+0+"_"+0+"_"+0+"_"+0+"_"+0);
	        	     }else if(Integer.valueOf(document.get("type")) == 2){
	        	    	 searchProPoJo.setUrl("/pro/proAction!list.dhtml?keys="+0+"_"+document.get("id")+"_"+0+"_"+0+"_"+0+"_"+0+"_"+0+"_"+0);
	        	     }
	        	     searchProPoJo.setName(document.get("name"));
	        	}
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
    
	public static void main(String[] args) throws Exception{
		
		SearchServiceImpl service = new SearchServiceImpl();
		
//		Pager<SearchProPoJo> pager = service.search(2, 3, 8, 7, 0, null, 1, 0, 1, 10);
//		
//		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
//			
//			for(SearchProPoJo pojo:pager.getResultList()){
//				System.out.println(pojo.getType()+" "+pojo.getId()+" "+pojo.getName()+" "+pojo.getPath_pic()+" "+pojo.getMarkPrice()+" "+pojo.getShopPrice()+" "+pojo.getUrl());
//			}
//			
//		}
		
		List<SearchProPoJo> list = service.mhSearch("test");
		if(list != null && list.size() > 0){
			for(SearchProPoJo pojo:list){
				System.out.println(pojo.getType()+" "+pojo.getId()+" "+pojo.getName()+" "+pojo.getPath_pic()+" "+pojo.getMarkPrice()+" "+pojo.getShopPrice()+" "+pojo.getUrl());
			}
		}
	}
}