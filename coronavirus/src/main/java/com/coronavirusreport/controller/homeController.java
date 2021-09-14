package com.coronavirusreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coronavirusreport.models.locationStats;
import com.coronavirusreport.services.coronaVirusServices;

@Controller
public class homeController {
	
	@Autowired
	coronaVirusServices cvservices;
	
	@GetMapping("/")
	public String home(Model model) {
		
		System.out.println("*******************************************************************************************");
		List<locationStats> allStats = cvservices.getAllstats();
		long totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotal_Cases()).sum();
		long totalNewCases = allStats.stream().mapToInt(stat -> stat.getNew_Cases()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}

}
