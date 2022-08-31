package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductUpdateController {
	private final String command = "update.prd";
	private String getPage="/productUpdateForm";
	private String gotoPage="redirect:/list.prd";
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command,method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("num") String num,
								@RequestParam("pageNumber") String pageNumber) {
		ModelAndView mav = new ModelAndView();
		ProductBean pb = pdao.getProduct(num);
		mav.addObject("product",pb);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String update(
			@RequestParam(value="pageNumber",required=false) String pageNumber,
			@RequestParam("originalImg") String originalImg,
			@ModelAttribute("product") @Valid ProductBean pb, BindingResult result,
			Model model) {

		System.out.println("PUD�� pb.getImage():"+pb.getImage());	 //���ο� �̹��� ���� �̸�
		System.out.println("PUD�� originalImg:"+originalImg); 		//���� �̹��� ���� �̸�

		if(result.hasErrors()) {
			model.addAttribute("pageNumber",pageNumber);
			return getPage;
		}

		//���� �̹��� ����
		String originalPath = servletContext.getRealPath("/resources/")+originalImg;
		File file1 = new File(originalPath);
		file1.delete();
		
		//���ο� �̹��� ���
		MultipartFile multi = pb.getUpload();
		String newPath = servletContext.getRealPath("/resources")+"/"+pb.getImage(); //���ε� ��ġ ����
		File file2 = new File(newPath); //���Ϸ� �����
		
		try {
			multi.transferTo(file2); //���� ���ε�
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pdao.updateProduct(pb);
		model.addAttribute("pageNumber",pageNumber);
		return gotoPage;
	}

}
