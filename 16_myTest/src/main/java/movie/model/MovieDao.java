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
		System.out.println("Dao서확인하는 getMovieList:"+lists.size());
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
		//일반 데이터 하나 넘어갔을때는 movie.xml에 #{title}처럼 아무거나 입력해도 되는데
		//MovieBena mb처럼 insertMovie하는 것이 넘어가면 getter메서드 등의 사용 위해 movie.xml에 name 그대로써줘야 한다 
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
