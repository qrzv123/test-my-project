package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductInsertController {
	private final String command = "/insert.prd";
	private String getPage = "/productInsertForm";
	private final String gotoPage = "redirect:/list.prd";

	//HttpServletRequest request;
	//ServletContext application;������Ʈ���ϳ���������� application��ü�� ���� ���ε��� ���� ����Ѵ�
	
	@Autowired
	ServletContext servletContext;
	//������Ʈ�� �ϳ� �ڵ����θ�������� ServletContext��ü������ �ʿ�� �ؼ� �����Ѵ�
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping(value=command, method = RequestMethod.GET)
	public String insert(HttpSession session) {
		//�α��� �ϸ� loginInfo session������ �Ұ��̴� null�� ���ٴ� ���� ���Ǽ��� �� ���� ���ٴ� ��
		if(session.getAttribute("loginInfo")==null) {
			
			session.setAttribute("destination", "redirect:/insert.prd");//redirect���� ��û�� ������ get����̴�
			//�α�����destination��� �̸����� ������ redirect:/insert.prd��û�� �ϱ����� ���Ǽ����� ���� insert.prd�Ϸ����ؼ�
			
			return "redirect:/loginForm.mem";//�α��� �������� MemberLoginController�� ���Եȴ�
		}
		else {//�α��� ������
			return getPage;			
		}
	}

	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView insert(
			@ModelAttribute("product") @Valid ProductBean product,
			BindingResult result) {
		
		//����ġ���� ���ϸ��� set
		//product.setName(�Է��� ��)
		//product.setImage();			//insertform���� �Է��� ����image�� ���� �ʴ´�
		//product.setUpload(������ ȭ��);	//insertform���� image���ϸ��� upload�� ��´�
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		MultipartFile multi = product.getUpload(); //������ �̸���(������)����ִ� ��ü�� multi�� ���´�
		System.out.println("===================================insertController======================================");
		System.out.println("multi.getName():"+multi.getName()); 						//������ (�̸���)������ upload�� ����ִ�
		System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());	//��¥ ������ �̸��� ����
		System.out.println("product.getImage():"+product.getImage());					//��¥ ������ �̸��� ����
		
		productDao.productInsert(product); //insert�۾��� DB���̺��� ���� �̸��� �����
		
		
		String uploadPath = servletContext.getRealPath("/resources");
		//application�� Ÿ�Ը��� servletContext
		//�̶��ο��� ������ ������ ��ġ ���� /resources/����������� resources���� ���ο� ���� �����ص� ��, js������ ����� �׾���
		
		System.out.println("uploadPath:"+uploadPath);
		//uploadPath:C:\Spring_jhs\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\16_Spring_MyBatis_Products\resources
		
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
		//file������ �����ϰ��ִ� uploadPath��ġ�� multi.getOriginalFilename()�� �ش��ϴ� ������ �ø��� ������transferTo���
		try {
			multi.transferTo(file);//transferTo�� �� ��ġ(����file)�� ������ �÷��ִ� �޼���
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName(gotoPage);
		return mav;
	}
	
}
