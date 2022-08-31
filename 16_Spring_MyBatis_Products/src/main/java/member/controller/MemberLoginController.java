package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {
	private final String command = "/loginForm.mem";
	private String getPage = "/memberLoginForm";

	@Autowired
	MemberDao memberDao;

	//get: productList.jsp에서 상품추가하기=>ProductInsertController
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String login() {
		return getPage;

	}

	//post: memberLoginForm.jsp 로그인
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String login(MemberBean member,
			HttpServletResponse response,
			HttpSession session) throws IOException {
		//커맨드객체 만들어질때 setId, setPassword만 호출됨
		//예외처리 호출한쪽에서 하도록 throws
		
		//상단MemberBean은 로그인시 입력한 내용
		System.out.println("로그인시입력한id :"+member.getId());
		System.out.println("로그인시입력한password :"+member.getPassword());
		response.setContentType("text/html; charset=utf-8");
		//한글처리

		PrintWriter write = response.getWriter();
		//PrintWriter는 웹브라우저와의 연결을 담당
		//연결하고있는 다리이름은 write

		//하단MemberBean은 회원이 존재할시 회원내용
		MemberBean login = memberDao.getMember(member.getId());
		if(login ==null) {
			System.out.println("가입하지 않은 회원");
			write.println("<script>");
			write.println("alert('가입하지 않은 회원');");
			write.println("</script>");
			write.flush();//브라우저로 내보내기
			return getPage;
		}
		else {//일치하는 아이디 존재
			if(member.getPassword().equals(login.getPassword())) {
				System.out.println("가입한 회원");
				session.setAttribute("loginInfo", login);//logInfo이름이 관리하는 login에 회원의 정보가 다 들어있다
				
				String destination = (String)session.getAttribute("destination");// redirect:/insert.prd을 가져온다
				System.out.println(destination);
				return destination; // return "redirect:/insert.prd";한것이다
			}
			else {//비번이 일치하지 않음
				write.println("<script>");
				write.println("alert('비번이 일치하지 않습니다.');");
				write.println("</script>");
				write.flush();//브라우저로 내보내기
				return getPage;
			}
		}//일치하는아이디존재시 비번일치y/n

	}

}
