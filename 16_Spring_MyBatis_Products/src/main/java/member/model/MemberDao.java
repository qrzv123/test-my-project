package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import product.model.ProductBean;
import utility.Paging;

@Component("MyMemberDao")
public class MemberDao {
	
	private String namespace="member.model.Member";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public void insertMember(MemberBean mb) {
		int cnt = sqlSessionTemplate.insert(namespace+".InsertMember",mb);
		System.out.println("dao서확인하는 insert의 cnt:"+cnt);
	}

	public int getTotalCount(Map<String, String> map) {	
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		System.out.println("dao서확인하는 totalCount:"+totalCount);
		return totalCount;
	}

	public List<MemberBean> getMemberList(Paging pageInfo, Map<String, String> map) {
		List<MemberBean> lists = new ArrayList<MemberBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());

		lists = sqlSessionTemplate.selectList(namespace+".GetMemberList",map,rowBounds);
		return lists;
	}

	public MemberBean getMember(String id) {
		MemberBean login = null;//이렇게해줘야 못찾으면 널값이 리턴이 된다
		login = sqlSessionTemplate.selectOne(namespace+".GetMember",id);
		return login;
	}
	
	
}
