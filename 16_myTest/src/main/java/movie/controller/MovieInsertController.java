package movie.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieBean;
import movie.model.MovieDao;

@Controller
public class MovieInsertController {
	private final String command="/insert.mv";
	private String getPage="/movieInsertForm";
	private String gotoPage="redirect:/list.mv";
	
	@Autowired
	private MovieDao movieDao;
	
	@RequestMapping(value=command,method = RequestMethod.GET)
	public ModelAndView insert() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command,method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("mb") @Valid MovieBean bean, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;			
		}
		movieDao.insertMovie(bean);
		
		mav.setViewName(gotoPage);
		return mav;
	}
}
