package movie.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieBean;
import movie.model.MovieDao;

@Controller
public class MovieUpdateController {
	private final String command = "/update.mv";// update.mv∏∏ Ω·¡‡µµ µ 
	private String getPage="/movieUpdateForm";
	private String gotoPage="redirect:/list.mv";
	
	@Autowired
	MovieDao mdao;
	
	@RequestMapping(value=command,method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("num") String num,
								@RequestParam("pageNumber") String pageNumber) {
		MovieBean mb = mdao.getMovie(num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("mb",mb);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command,method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("pageNumber") String pageNumber,
			@ModelAttribute("mb") @Valid MovieBean mb, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.addObject("pageNumber",pageNumber);
			mav.setViewName(getPage);
			return mav;
		}
		mdao.updateMovie(mb);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(gotoPage);
		//mav.setViewName(gotoPage+"?pageNumber="+pageNumber);¿ßø°2¡Ÿ∏ª∞Ì ¿Ã 1¡Ÿµµ µ 
		return mav;
	}
}
