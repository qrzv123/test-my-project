package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberRegisterController {
	private final String command = "registerForm.mem";
	private String getPage = "/memberRegisterForm";
	private String gotoPage = "redirect:/list.mem";
	
	@Autowired
	MemberDao memberDao;
	
	//get:memberLoginForm.jsp 회원가입 클릭시
	@RequestMapping(value=command,method = RequestMethod.GET) 
	public String register() {
		return getPage;
	}
	
	//post: memberRegisterForm.jsp 추가하기 클릭시
	@RequestMapping(value=command,method = RequestMethod.POST) 
	public String register(@ModelAttribute("member") @Valid MemberBean member, BindingResult result,
							Model model) {
		
		if(result.hasErrors()) {
			return getPage;
		}
		memberDao.insertMember(member);
		return gotoPage;
	}
}
