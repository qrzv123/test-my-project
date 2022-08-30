package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductDetailController {
	private final String command = "detail.prd";
	private String getPage="/productDetail";
	
	@Autowired
	ProductDao pdao;
	
	@RequestMapping(value=command)
	public ModelAndView detail(@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber) {
		ProductBean pb = pdao.getProduct(num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pb",pb);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
}
