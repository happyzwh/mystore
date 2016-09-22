package com.mystore.business.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
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
import com.mystore.business.dao.ProductMapper;
import com.mystore.business.dao.SearchMapper;
import com.mystore.business.dto.Brand;
import com.mystore.business.dto.Category;
import com.mystore.business.dto.DocPoJo;
import com.mystore.business.dto.SearchProPoJo;
import com.mystore.business.pojo.SearchPojo;
import com.mystore.business.service.BrandService;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.SearchService;
import com.mystore.business.util.CommUtils;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductMapper productMapper;
	
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
					 type_fieldType.setIndexed(true);
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
						 rome_fieldType.setStored(false); 
						 Field rome_field = new Field("rome", docPojo.getRome(), rome_fieldType); 
						 doc.add(rome_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getJianPin())){
						 FieldType jianPin_fieldType = new FieldType(); 
						 jianPin_fieldType.setIndexed(true);
						 jianPin_fieldType.setTokenized(true);
						 jianPin_fieldType.setStored(false); 
						 Field jianPin_field = new Field("jianPin", docPojo.getJianPin(), jianPin_fieldType); 
						 doc.add(jianPin_field);
						 
					 }
					 
					 if(StringUtils.isNoneBlank(docPojo.getKeyWords())){
						 FieldType keyWords_fieldType = new FieldType(); 
						 keyWords_fieldType.setIndexed(true);
						 keyWords_fieldType.setTokenized(true);
						 keyWords_fieldType.setStored(false); 
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
						 shopPrice_fieldType.setNumericType(FieldType.NumericType.DOUBLE);
						 shopPrice_fieldType.setIndexed(true);
						 shopPrice_fieldType.setTokenized(false);
						 shopPrice_fieldType.setStored(true); 
						 Field shopPrice_field = new DoubleField("shopPrice", docPojo.getShopPrice(), shopPrice_fieldType); 
						 doc.add(shopPrice_field);
						 
					 }
					 
					 if(docPojo.getMarkPrice() != null){
						 FieldType markPrice_fieldType = new FieldType(); 
						 markPrice_fieldType.setNumericType(FieldType.NumericType.DOUBLE);
						 markPrice_fieldType.setIndexed(true);
						 markPrice_fieldType.setTokenized(false);
						 markPrice_fieldType.setStored(true); 
						 Field markPrice_field = new DoubleField("markPrice", docPojo.getMarkPrice(), markPrice_fieldType); 
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
						 id_countryfieldType.setStored(false); 
						 Field id_countryfield = new Field("id_country", String.valueOf(docPojo.getId_country()), id_countryfieldType); 
						 doc.add(id_countryfield);
					 }
					 
					 if(docPojo.getId_province() != null){
						 FieldType id_provincefieldType = new FieldType(); 
						 id_provincefieldType.setIndexed(true);
						 id_provincefieldType.setTokenized(false);
						 id_provincefieldType.setStored(false); 
						 Field id_provincefield = new Field("id_province", String.valueOf(docPojo.getId_province()), id_provincefieldType); 
						 doc.add(id_provincefield);
					 }
					 
					 if(docPojo.getType() == 0){
						 List<Category> cate_list = categoryService.getAllParentCategoryById(docPojo.getId_cate());
						 if(cate_list != null && cate_list.size() > 0){
							 StringBuilder ids_cate = new StringBuilder("");
							 for(Category cate:cate_list){
								 ids_cate.append(cate.getId()).append(" "); 
							 }
							 FieldType ids_catefieldType = new FieldType(); 
							 ids_catefieldType.setIndexed(true);
							 ids_catefieldType.setTokenized(true);
							 ids_catefieldType.setStored(false); 
							 Field ids_catefield = new Field("ids_cate",ids_cate.toString() , ids_catefieldType); 
							 doc.add(ids_catefield);
							 
						 }
						 
						 List<Brand> brand_list = brandService.getAllParentBrandById(docPojo.getId_brand());
						 if(brand_list != null && brand_list.size() > 0){
							 StringBuilder ids_brand = new StringBuilder("");
							 for(Brand brand:brand_list){
								 ids_brand.append(brand.getId()).append(" "); 
							 }
							 FieldType ids_BrandfieldType = new FieldType(); 
							 ids_BrandfieldType.setIndexed(true);
							 ids_BrandfieldType.setTokenized(true);
							 ids_BrandfieldType.setStored(false); 
							 Field ids_brandfield = new Field("ids_brand",ids_brand.toString() , ids_BrandfieldType); 
							 doc.add(ids_brandfield);
							 
						 }
						 
						 if(docPojo.getSort() != null){
							 FieldType sortfieldType = new FieldType(); 
							 sortfieldType.setIndexed(false);
							 sortfieldType.setTokenized(false);
							 sortfieldType.setStored(true); 
							 Field sortfield = new Field("sort", String.valueOf(docPojo.getSort()), sortfieldType); 
							 doc.add(sortfield);
						 }
						 
						 if(docPojo.getCount_sale() != null){
							 FieldType countSalefieldType = new FieldType(); 
							 countSalefieldType.setIndexed(false);
							 countSalefieldType.setTokenized(false);
							 countSalefieldType.setStored(true); 
							 Field countSalefield = new Field("count_sale", String.valueOf(docPojo.getCount_sale()), countSalefieldType); 
							 doc.add(countSalefield);
						 }
						 
						 if(docPojo.getCount_comment() != null){
							 FieldType countCommentfieldType = new FieldType(); 
							 countCommentfieldType.setIndexed(false);
							 countCommentfieldType.setTokenized(false);
							 countCommentfieldType.setStored(true); 
							 Field countCommentfield = new Field("count_comment", String.valueOf(docPojo.getCount_comment()), countCommentfieldType); 
							 doc.add(countCommentfield);
						 }
						 
						 if(docPojo.getCredit() != null){
							 FieldType creditfieldType = new FieldType(); 
							 creditfieldType.setIndexed(false);
							 creditfieldType.setTokenized(false);
							 creditfieldType.setStored(false); 
							 Field creditfield = new Field("credit", String.valueOf(docPojo.getCredit()), creditfieldType); 
							 doc.add(creditfield);
						 }
						 
						 List<Map<String,Object>> attrMap = productMapper.getProAttrMapByProId(docPojo.getId());
						 
						 if(attrMap != null && attrMap.size() > 0){
							 
							 StringBuilder attrName = new StringBuilder("");
							 StringBuilder attrValue = new StringBuilder("");
							 StringBuilder attrValueId = new StringBuilder("");
							 
							 for(Map<String,Object> map:attrMap ){
								 attrName.append(String.valueOf(map.get("name"))).append(" ");
								 
								 String[] values = String.valueOf(map.get("value")).split(",");
								 for(String s:values){
									 attrValue.append(s).append(" ");
								 }
								 
								 String[] vids = String.valueOf(map.get("vid")).split(",");
								 for(String s:vids){
									 attrValueId.append(s).append(" ");
								 }
							 }
							 
							 if(StringUtils.isNotBlank(attrName)){
								  FieldType attrNamefieldType = new FieldType(); 
								  attrNamefieldType.setIndexed(true);
								  attrNamefieldType.setTokenized(true);
								  attrNamefieldType.setStored(false); 
								  Field attrNamefield = new Field("attrName", attrName.toString(), attrNamefieldType); 
								  doc.add(attrNamefield);
							 }
							 
							 if(StringUtils.isNotBlank(attrValue)){
								  FieldType attrValuefieldType = new FieldType(); 
								  attrValuefieldType.setIndexed(true);
								  attrValuefieldType.setTokenized(true);
								  attrValuefieldType.setStored(false); 
								  Field attrValuefield = new Field("attrValue", attrValue.toString(), attrValuefieldType); 
								  doc.add(attrValuefield);
							 }
							 
							 if(StringUtils.isNotBlank(attrValueId)){
								  FieldType attrValueIdfieldType = new FieldType(); 
								  attrValueIdfieldType.setIndexed(true);
								  attrValueIdfieldType.setTokenized(true);
								  attrValueIdfieldType.setStored(false); 
								  Field attrValueIdfield = new Field("attrValueId", attrValueId.toString(), attrValueIdfieldType); 
								  doc.add(attrValueIdfield);
							 }
							 
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

    
    /**
     *  分类-品牌-国籍-产地-价格(低)-价格(高)-排序属性-排序
     * 
     * 
     * */
    public Pager<SearchProPoJo> search(Integer id_category,Integer id_brand,Integer id_country,Integer id_province,Double price_low,Double price_high,Integer type_sort,Integer type_asc,Integer pageNo,Integer pageSize){
    	
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
			
			QueryParser type_queryParser = new QueryParser(Version.LUCENE_47, "type", analyzer);
			Query type_query = type_queryParser.parse("0");
			
			booleanQuery.add(type_query, BooleanClause.Occur.MUST);
			
			if(id_category != null && id_category != 0){
				
				BooleanQuery idCateBooleanQuery = new BooleanQuery();
				
				QueryParser id_cate_queryParser = new QueryParser(Version.LUCENE_47, "id_cate", analyzer);
				Query id_cate_query = id_cate_queryParser.parse(String.valueOf(id_category));
				
				idCateBooleanQuery.add(id_cate_query, BooleanClause.Occur.SHOULD);
				
				QueryParser ids_cate_queryParser = new QueryParser(Version.LUCENE_47, "ids_cate", analyzer);
				ids_cate_queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
				Query ids_cate_query = ids_cate_queryParser.parse(String.valueOf(id_category));
				
				idCateBooleanQuery.add(ids_cate_query, BooleanClause.Occur.SHOULD);
				
				booleanQuery.add(idCateBooleanQuery, BooleanClause.Occur.MUST);
			}
			
			if(id_brand != null && id_brand != 0){
				
				BooleanQuery idBrandBooleanQuery = new BooleanQuery();
				
				QueryParser id_brand_queryParser = new QueryParser(Version.LUCENE_47, "id_brand", analyzer);
				Query id_brand_query = id_brand_queryParser.parse(String.valueOf(id_brand));
				
				idBrandBooleanQuery.add(id_brand_query, BooleanClause.Occur.SHOULD);
				
				QueryParser ids_brand_queryParser = new QueryParser(Version.LUCENE_47, "ids_brand", analyzer);
				ids_brand_queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
				Query ids_brand_query = ids_brand_queryParser.parse(String.valueOf(id_brand));
				
				idBrandBooleanQuery.add(ids_brand_query, BooleanClause.Occur.SHOULD);
				
				booleanQuery.add(idBrandBooleanQuery, BooleanClause.Occur.MUST);
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
			
			if( (price_low != null && price_low > 0) || (price_high != null && price_high > 0) ){
				
				boolean boolean_price_low = (price_low == null || price_low == 0)?false:true;
				boolean boolean_price_high = (price_high==null || price_high == 0)?false:true;
				
				Query shopPrice_query = NumericRangeQuery.newDoubleRange("shopPrice", ( price_low != null && price_low > 0)?Double.valueOf(price_low):null, (price_high != null && price_high > 0)?Double.valueOf(price_high):null, boolean_price_low, boolean_price_high);
				
				booleanQuery.add(shopPrice_query, BooleanClause.Occur.MUST);
			}
			
			Sort sort = null;
			SortField[] sortField = new SortField[2];
    		
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
			}else{
				sortField[0]=new SortField("shopPrice", SortField.FIELD_SCORE.getType(), true);
			}
			
			sortField[1]=new SortField("onSaleTime", SortField.FIELD_SCORE.getType() , true);
			
			sort = new Sort(sortField);
			
			System.out.println(booleanQuery.toString());
			
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
	
			TopFieldCollector c = TopFieldCollector.create(sort, pageNo*pageSize, true, true, true, false);
			isearcher.search(booleanQuery, c);
	        ScoreDoc[] hits = c.topDocs((pageNo-1)*pageSize, pageSize).scoreDocs;
	        if (hits != null && hits.length > 0){
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
	        	     searchProPoJo.setMarkPrice(StringUtils.isNotBlank(document.get("markPrice"))?Double.valueOf(document.get("markPrice")):0);
	        	     searchProPoJo.setShopPrice(StringUtils.isNotBlank(document.get("shopPrice"))?Double.valueOf(document.get("shopPrice")):0);
	        	     
//	        	     System.out.println(searchProPoJo.getType()+" "+searchProPoJo.getId()+" "+searchProPoJo.getName()+" "+searchProPoJo.getPath_pic()+" "+searchProPoJo.getMarkPrice()+" "+searchProPoJo.getShopPrice()+" "+searchProPoJo.getUrl());
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
			
			QueryParser attrName_queryParser = new QueryParser(Version.LUCENE_47, "attrName", analyzer);
			Query attrName_query = attrName_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(attrName_query, BooleanClause.Occur.SHOULD);
			
			QueryParser attrValue_queryParser = new QueryParser(Version.LUCENE_47, "attrValue", analyzer);
			Query attrValue_query = attrValue_queryParser.parse(String.valueOf(keys));
			booleanQuery.add(attrValue_query, BooleanClause.Occur.SHOULD);
    		
			TopDocs hits = isearcher.search(booleanQuery,100);
			ScoreDoc[] hit = hits.scoreDocs;
			if (hit != null && hit.length > 0){
	        	for (int i = 0; i < hit.length; i++){
	        		int num = hit[i].doc;
	        	    Document document = isearcher.doc(num);	
	        	    
	        	     SearchProPoJo searchProPoJo = new SearchProPoJo();
	        	     list.add(searchProPoJo);

	        	     if(Integer.valueOf(document.get("type")) == 0){
	        	    	 searchProPoJo.setUrl("/product/productAction!detail.dhtml?id="+document.get("id"));
	        	     }else if(Integer.valueOf(document.get("type")) == 1){
	        	    	 searchProPoJo.setUrl("/search/searchAction!list.dhtml?keys="+document.get("id")+"-"+0+"-"+0+"-"+0+"-"+0+"-"+0+"-"+0+"-"+0);
	        	     }else if(Integer.valueOf(document.get("type")) == 2){
	        	    	 searchProPoJo.setUrl("/search/searchAction!list.dhtml?keys="+document.get("id_cate")+"-"+document.get("id")+"-"+0+"-"+0+"-"+0+"-"+0+"-"+0+"-"+0);
	        	     }
	        	     searchProPoJo.setName(document.get("name"));
	        	}
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }

	@Override
	public Pager<SearchProPoJo> search(SearchPojo searchPojo,Integer pageNo,Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
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
			
			QueryParser type_queryParser = new QueryParser(Version.LUCENE_47, "type", analyzer);
			Query type_query = type_queryParser.parse("0");
			
			booleanQuery.add(type_query, BooleanClause.Occur.MUST);
			
			if(searchPojo.getCateId() != null){
				
				BooleanQuery idCateBooleanQuery = new BooleanQuery();
				
				QueryParser id_cate_queryParser = new QueryParser(Version.LUCENE_47, "id_cate", analyzer);
				Query id_cate_query = id_cate_queryParser.parse(String.valueOf(searchPojo.getCateId()));
				
				idCateBooleanQuery.add(id_cate_query, BooleanClause.Occur.SHOULD);

				QueryParser ids_cate_queryParser = new QueryParser(Version.LUCENE_47, "ids_cate", analyzer);
				ids_cate_queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
				Query ids_cate_query = ids_cate_queryParser.parse(String.valueOf(searchPojo.getCateId()));
				
				idCateBooleanQuery.add(ids_cate_query, BooleanClause.Occur.SHOULD);
				
				booleanQuery.add(idCateBooleanQuery, BooleanClause.Occur.MUST);
			}
			
			if(searchPojo.getBrandIds() != null && searchPojo.getBrandIds().size() > 0){
				
				BooleanQuery brandBooleanQuery = new BooleanQuery();
				
				for(Integer brandid:searchPojo.getBrandIds()){
					
					QueryParser id_brand_queryParser = new QueryParser(Version.LUCENE_47, "id_brand", analyzer);
					Query id_brand_query = id_brand_queryParser.parse(String.valueOf(brandid));
					
					brandBooleanQuery.add(id_brand_query, BooleanClause.Occur.SHOULD);
					
					QueryParser ids_brand_queryParser = new QueryParser(Version.LUCENE_47, "ids_brand", analyzer);
					ids_brand_queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
					Query ids_brand_query = ids_brand_queryParser.parse(String.valueOf(brandid));
					
					brandBooleanQuery.add(ids_brand_query, BooleanClause.Occur.SHOULD);
					
				}
				
				booleanQuery.add(brandBooleanQuery, BooleanClause.Occur.MUST);
			}
			
			if(searchPojo.getAttrValueIds() != null && searchPojo.getAttrValueIds().size() > 0){
				
				BooleanQuery attrValueBooleanQuery = new BooleanQuery();
				
				for(Integer attrValueId:searchPojo.getAttrValueIds()){
					QueryParser attrValueIdQueryParser = new QueryParser(Version.LUCENE_47, "attrValueId", analyzer);
					Query attrValuequery = attrValueIdQueryParser.parse(String.valueOf(attrValueId));
					
					attrValueBooleanQuery.add(attrValuequery, BooleanClause.Occur.SHOULD);
				}
				
				booleanQuery.add(attrValueBooleanQuery, BooleanClause.Occur.MUST);
			}
			
			if( (searchPojo.getLowPrice() != null && searchPojo.getLowPrice() > 0) || (searchPojo.getHighPrice() != null && searchPojo.getHighPrice() > 0) ){
				
				boolean boolean_price_low = (searchPojo.getLowPrice() == null || searchPojo.getLowPrice() == 0)?false:true;
				boolean boolean_price_high = (searchPojo.getHighPrice()==null || searchPojo.getHighPrice() == 0)?false:true;
				
				Query shopPrice_query = NumericRangeQuery.newDoubleRange("shopPrice", ( searchPojo.getLowPrice() != null && searchPojo.getLowPrice() > 0 )?searchPojo.getLowPrice():null, (searchPojo.getHighPrice() != null && searchPojo.getHighPrice() > 0)?searchPojo.getHighPrice():null, boolean_price_low, boolean_price_high);
				
				booleanQuery.add(shopPrice_query, BooleanClause.Occur.MUST);
			}
			
			Sort sort = null;
			SortField[] sortField = new SortField[1];
    		
			if(searchPojo.getOrderType() != null){
				String sortFieldName = null;
				SortField.Type type = null;
				if(searchPojo.getOrderType() == 0 || searchPojo.getOrderType() == 1){
					sortFieldName="count_sale";
					type = SortField.Type.INT;
				}else if(searchPojo.getOrderType() == 2){
					sortFieldName="shopPrice";
					type = SortField.Type.DOUBLE;
				}else if(searchPojo.getOrderType() == 3){
					sortFieldName="count_comment";
					type = SortField.Type.INT;
				}else if(searchPojo.getOrderType() == 4){
					sortFieldName="onSaleTime";
					type = SortField.Type.STRING;
				}
				sortField[0]=new SortField(sortFieldName, type, ( searchPojo.getAsc() ==null || searchPojo.getAsc() ==0 )?true:false);
			}else{
				sortField[0]=new SortField("count_sale", SortField.Type.INT, true);
			}
			
			sort = new Sort(sortField);
			
			System.out.println(booleanQuery.toString());
	
			TopFieldCollector c = TopFieldCollector.create(sort, pageNo*pageSize, true, true, true, false);
			isearcher.search(booleanQuery, c);
	        ScoreDoc[] hits = c.topDocs((pageNo-1)*pageSize, pageSize).scoreDocs;
	        if (hits != null && hits.length > 0){
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
	        	     searchProPoJo.setMarkPrice(StringUtils.isNotBlank(document.get("markPrice"))?Double.valueOf(document.get("markPrice")):0);
	        	     searchProPoJo.setShopPrice(StringUtils.isNotBlank(document.get("shopPrice"))?Double.valueOf(document.get("shopPrice")):0);
	        	     
//	        	     System.out.println(searchProPoJo.getType()+" "+searchProPoJo.getId()+" "+searchProPoJo.getName()+" "+searchProPoJo.getPath_pic()+" "+searchProPoJo.getMarkPrice()+" "+searchProPoJo.getShopPrice()+" "+searchProPoJo.getUrl());
	        	}
	        }
	        
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    
    	return pager;
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
