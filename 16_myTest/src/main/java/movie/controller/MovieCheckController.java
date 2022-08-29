package movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import movie.model.MovieDao;

@Controller
public class MovieCheckController {

	@Autowired 
	MovieDao movieDao;

	private final String command = "title_check_proc.mv";
	//private String getPage = "insert.mv";

	@RequestMapping(command)
	@ResponseBody
	public String check(
			@RequestParam("inputtitle") String inputtitle) {

		int cnt = -1;
		cnt = movieDao.checkDupl(inputtitle); //yes:사용가능, no:사용불가
		if(cnt == 0) {
			return "YES";
			//YES.jsp인가? view의 이름으로 삼고 싶지 않다면 
			//어노테이션인@ResponseBody써주면 view이름이아니라 글자 자체를 넘긴다
		}
		else {
			return "NO";
		}
	}
}
