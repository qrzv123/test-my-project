package product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("ProductDao")
public class ProductDao {

	private final String namespace="product.model.Product";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;


	public int getTotalCount(Map<String, String> map) {
		int result = sqlSessionTemplate.selectOne(namespace+".GetTotalCount", map);
		return result;

	}


	public List<ProductBean> getProductList(Paging pageInfo, Map<String, String> map) {
		List<ProductBean> lists = new ArrayList<ProductBean>();

		System.out.println("Prdoffset:" + pageInfo.getOffset());
		System.out.println("Prdlimit:" + pageInfo.getLimit());

		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());

		lists = sqlSessionTemplate.selectList(namespace+".GetProductList",map,rowBounds);

		return lists;

	}


	public int productInsert(ProductBean product) {
		int cnt = sqlSessionTemplate.insert(namespace+".productInsert",product);
		System.out.println("insert cnt:"+cnt);
		return cnt;

	}


	public ProductBean getProduct(String num) {
		ProductBean pb = sqlSessionTemplate.selectOne(namespace+".GetProduct",num);
		return pb;
	}

}
