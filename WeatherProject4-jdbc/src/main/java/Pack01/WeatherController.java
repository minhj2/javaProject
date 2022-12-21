package Pack01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
	private static WeatherDao wdao; 
	@Autowired
	public WeatherController(WeatherDao weatherDao) {
		this.wdao = weatherDao;
	}	

	@RequestMapping("/list")
	public String weatherlist(Model model, 
			@RequestParam(value="sint") int sint,
			@RequestParam(value="eint") int eint
			) {
			List<WeatherDto> list = new ArrayList<WeatherDto>();
			list =wdao.selectAll(sint, eint);
		model.addAttribute("result", list);
		return "weatherlist";
	}
	@RequestMapping("/selectEachpageList")	
	public String eachpageList(Model model,
			@RequestParam(value="totcnt") int totcnt,
			@RequestParam(value="curpage") int curpage
			) {

		List list = new ArrayList();
		list =wdao.selectEachpageList(totcnt, curpage);
			
		model.addAttribute("result", list.get(0));
		model.addAttribute("pv",list.get(1));
		return "selectEachpageList";
	}
	@RequestMapping("/detail")
	public String Weatherlist(Model model, 
			@RequestParam(value = "id") int id,
			@RequestParam(value = "curpage") int curpage
			) {	
		WeatherDto wdto = wdao.selectWd(id);	
		List<CodeDto> placelist = wdao.placelist();
		List<CodeDto> naturelist = wdao.naturelist();
		List<CodeDto> observelist = wdao.observelist();

		model.addAttribute("result",wdto);
		model.addAttribute("placelist",placelist);
		model.addAttribute("naturelist",naturelist);
		model.addAttribute("observelist",observelist);
		model.addAttribute("curpage",curpage);
		return "weatherdetail";
	}
	@RequestMapping("/update")
	public String Weatherupate(Model model, 
			@RequestParam(value = "id") int id,	
			@RequestParam(value = "yyyy") int yyyy,
			@RequestParam(value = "mm") int mm,
			@RequestParam(value = "dd") int dd,
			@RequestParam(value = "placeno") int placeno, 
			@RequestParam(value = "naturecd") int naturecd,
			@RequestParam(value = "observecd") int observecd,
			@RequestParam(value = "curpage") int curpage
			) {	

			WeatherDto wdto = new WeatherDto(id,yyyy,mm,dd, placeno,naturecd, observecd);
			int rsint = wdao.updateWd(wdto);
			List list = new ArrayList();
			list =wdao.selectEachpageList(0, curpage);
				
			model.addAttribute("result", list.get(0));
			model.addAttribute("pv",list.get(1));
			return "selectEachpageList";
	}

	@RequestMapping("/delete")
	public String weatherdelete(Model model, 
			@RequestParam(value = "id") int id,
			@RequestParam(value = "curpage") int curpage
			) {
		int result = wdao.deleteWd(id);	
		List list = new ArrayList();
		list =wdao.selectEachpageList(0, curpage);
			
		model.addAttribute("result", list.get(0));
		model.addAttribute("pv",list.get(1));
		return "selectEachpageList";
	}		
}
