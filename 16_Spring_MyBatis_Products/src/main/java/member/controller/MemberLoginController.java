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

	//get: productList.jsp���� ��ǰ�߰��ϱ�=>ProductInsertController
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String login() {
		return getPage;

	}

	//post: memberLoginForm.jsp �α���
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String login(MemberBean member,
			HttpServletResponse response,
			HttpSession session) throws IOException {
		//Ŀ�ǵ尴ü ��������� setId, setPassword�� ȣ���
		//����ó�� ȣ�����ʿ��� �ϵ��� throws
		
		//���MemberBean�� �α��ν� �Է��� ����
		System.out.println("�α��ν��Է���id :"+member.getId());
		System.out.println("�α��ν��Է���password :"+member.getPassword());
		response.setContentType("text/html; charset=utf-8");
		//�ѱ�ó��

		PrintWriter write = response.getWriter();
		//PrintWriter�� ������������ ������ ���
		//�����ϰ��ִ� �ٸ��̸��� write

		//�ϴ�MemberBean�� ȸ���� �����ҽ� ȸ������
		MemberBean login = memberDao.getMember(member.getId());
		if(login ==null) {
			System.out.println("�������� ���� ȸ��");
			write.println("<script>");
			write.println("alert('�������� ���� ȸ��');");
			write.println("</script>");
			write.flush();//�������� ��������
			return getPage;
		}
		else {//��ġ�ϴ� ���̵� ����
			if(member.getPassword().equals(login.getPassword())) {
				System.out.println("������ ȸ��");
				session.setAttribute("loginInfo", login);//logInfo�̸��� �����ϴ� login�� ȸ���� ������ �� ����ִ�
				
				String destination = (String)session.getAttribute("destination");// redirect:/insert.prd�� �����´�
				System.out.println(destination);
				return destination; // return "redirect:/insert.prd";�Ѱ��̴�
			}
			else {//����� ��ġ���� ����
				write.println("<script>");
				write.println("alert('����� ��ġ���� �ʽ��ϴ�.');");
				write.println("</script>");
				write.flush();//�������� ��������
				return getPage;
			}
		}//��ġ�ϴ¾��̵������ �����ġy/n

	}

}
