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
		
		ProductBean pb = pdao.getProduct(num);	//�����̸��� ������������
		String deletePath = servletContext.getRealPath("/resources");//deletePath�� �׳� ��� ���ڿ�(String��)
		
		File delFile = new File(deletePath+"/"+pb.getImage());	//���ڿ��̾��� ��� ���ڿ��� �����̸��������ͼ� ��¥ ���Ϸ� �ǹ��ϰ� �������(���Ϸ� �������)
		delFile.delete();//������ ���� ����
		
		pdao.deleteProduct(num);//DB������ �����̸��� ������
		
		mav.setViewName(gotoPage+"?pageNumber="+pageNumber);
		return mav;
	}
}
