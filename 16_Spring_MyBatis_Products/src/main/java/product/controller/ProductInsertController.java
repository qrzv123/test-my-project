package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
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
	//ServletContext application;프로젝트당하나만들어지는 application객체를 파일 업로드할 때에 사용한다
	
	@Autowired
	ServletContext servletContext;
	//프로젝트당 하나 자동으로만들어지는 ServletContext객체정보를 필요로 해서 주입한다
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping(value=command, method = RequestMethod.GET)
	public String insert() {
		return getPage;
	}

	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView insert(
			@ModelAttribute("product") @Valid ProductBean product,
			BindingResult result) {
		
		//이위치에서 파일명이 set
		//product.setName(입력한 값)
		//product.setImage();			//insertform에서 입력한 값이image에 들어가지 않는다
		//product.setUpload(선택한 화일);	//insertform에서 image파일명을 upload에 담는다
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		MultipartFile multi = product.getUpload(); //파일의 이름이(정보가)들어있던 객체가 multi로 들어온다
		System.out.println("===================================insertController======================================");
		System.out.println("multi.getName():"+multi.getName()); 						//파일의 (이름이)정보가 upload에 담겨있다
		System.out.println("multi.getOriginalFilename():"+multi.getOriginalFilename());	//진짜 파일의 이름을 담음
		System.out.println("product.getImage():"+product.getImage());					//진짜 파일의 이름을 담음
		
		productDao.productInsert(product); //insert작업시 DB테이블에는 파일 이름만 저장됨
		
		
		String uploadPath = servletContext.getRealPath("/resources");
		//application의 타입명인 servletContext
		//이라인에서 실제로 저장할 위치 설정 /resources/폴더명과같이 resources파일 내부에 따로 지정해도 됨, js폴더도 여기다 뒀었음
		
		System.out.println("uploadPath:"+uploadPath);
		//uploadPath:C:\Spring_jhs\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\16_Spring_MyBatis_Products\resources
		
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
		//file변수로 관리하고있는 uploadPath위치의 multi.getOriginalFilename()에 해당하는 파일을 올리고 싶을때transferTo사용
		try {
			multi.transferTo(file);//transferTo는 이 위치(변수file)에 파일을 올려주는 메서드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName(gotoPage);
		return mav;
	}
	
}
