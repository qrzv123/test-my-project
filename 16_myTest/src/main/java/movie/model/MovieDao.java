package movie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("MovieDao")
public class MovieDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private String namespace = "movie.MovieBean";
	
	public List<MovieBean> getMovieList(Paging pageInfo, Map<String,String> map) {
		List<MovieBean> lists = new ArrayList<MovieBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		
		lists = sqlSessionTemplate.selectList(namespace+".getMovieList", map,rowBounds);
		System.out.println("Dao��Ȯ���ϴ� getMovieList:"+lists.size());
		return lists;
	}
	
	public int getTotalCount(Map<String,String> map) { 
		int count = sqlSessionTemplate.selectOne(namespace+".CountMovie", map);
		return count;
	}
	
	public void insertMovie(MovieBean mb) {
		sqlSessionTemplate.insert(namespace+".InsertMovie", mb);		
	}

	public int checkDupl(String inputtitle) {
		int result=-1;
		result=sqlSessionTemplate.selectOne(namespace+".TitleCheck", inputtitle);
		//�Ϲ� ������ �ϳ� �Ѿ������ movie.xml�� #{title}ó�� �ƹ��ų� �Է��ص� �Ǵµ�
		//MovieBena mbó�� insertMovie�ϴ� ���� �Ѿ�� getter�޼��� ���� ��� ���� movie.xml�� name �״�ν���� �Ѵ� 
		return result;
	}

	public MovieBean getMovie(String num) {
		MovieBean mb = new MovieBean();
		mb = sqlSessionTemplate.selectOne(namespace+".GetMovie",num);
		return mb;
	}

	public void updateMovie(MovieBean mb) {
		sqlSessionTemplate.update(namespace+".UpdateMovie",mb);
	}

	
}
