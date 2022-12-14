package member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;
import utility.Paging;

@Controller
public class MemberListController {
	private final String command = "list.mem";
	private String getPage = "/memberList";

	@Autowired
	MemberDao memberDao;

	@RequestMapping(value=command) 
	public String list(
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber,
			HttpServletRequest request,Model model) {
		System.out.println("keyword:"+keyword);
		System.out.println("whatColumn:"+whatColumn);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		//한글검색이 안된다? =>내부브라우저에서 하고있어서그렇다 크롬에서 확인해봐라

		int totalCount = memberDao.getTotalCount(map);
		String url=request.getContextPath()+"/"+command;

		Paging pageInfo = new Paging(pageNumber,null, totalCount, url, whatColumn, keyword, null);

		List<MemberBean> lists = new ArrayList<MemberBean>();
		lists = memberDao.getMemberList(pageInfo,map);

		model.addAttribute("lists",lists);
		model.addAttribute("pageInfo",pageInfo);
		return getPage;
	}
}
