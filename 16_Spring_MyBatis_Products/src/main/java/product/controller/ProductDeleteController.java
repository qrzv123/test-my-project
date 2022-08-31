package product.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductDeleteController {
	private final String command="delete.prd";
	private String gotoPage="redirect:/list.prd";
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command)
	public ModelAndView delete(@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber) {
		ModelAndView mav = new ModelAndView();
		
		ProductBean pb = pdao.getProduct(num);	//파일이름을 가져오기위해
		String deletePath = servletContext.getRealPath("/resources");//deletePath는 그냥 경로 문자열(String임)
		
		File delFile = new File(deletePath+"/"+pb.getImage());	//문자열이었던 경로 문자열과 파일이름을가져와서 진짜 파일로 의미하게 만들어줌(파일로 만들어줌)
		delFile.delete();//지정한 파일 삭제
		
		pdao.deleteProduct(num);//DB에서만 파일이름이 삭제됨
		
		mav.setViewName(gotoPage+"?pageNumber="+pageNumber);
		return mav;
	}
}
