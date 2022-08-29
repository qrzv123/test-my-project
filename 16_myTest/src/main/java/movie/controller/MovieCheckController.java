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
		cnt = movieDao.checkDupl(inputtitle); //yes:��밡��, no:���Ұ�
		if(cnt == 0) {
			return "YES";
			//YES.jsp�ΰ�? view�� �̸����� ��� ���� �ʴٸ� 
			//������̼���@ResponseBody���ָ� view�̸��̾ƴ϶� ���� ��ü�� �ѱ��
		}
		else {
			return "NO";
		}
	}
}
