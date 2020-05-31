package io.corona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.corona.models.Location;
import io.corona.services.Coronadata;

@Controller
public class HomeC{
	
	@Autowired
	Coronadata coronaData;
	
	@GetMapping("/")
	public String Home(Model model)
	
	{
		
		List<Location> stats=coronaData.getStat();
		int totalCases=stats.stream().mapToInt(stat-> stat.getLastTotal()).sum();
		int newCases=stats.stream().mapToInt(stat-> stat.getDiff()).sum();

		model.addAttribute("Location",stats);
		model.addAttribute("totalCases",totalCases);
		model.addAttribute("newCases",newCases);


		return "home";
	}
}