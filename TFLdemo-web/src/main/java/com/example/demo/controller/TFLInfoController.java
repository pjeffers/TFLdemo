package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ArrivalSchedule;
import com.example.demo.service.ArrivalsService;

@Controller
public class TFLInfoController {
	
	@Autowired
	ArrivalsService arrivalsService;
	
@RequestMapping("home")
 public String home(Model model){
	
	ArrivalSchedule arrivalSchedule = arrivalsService.getTubeArrivals("Great Portland Street");
	model.addAttribute("arrivalSchedule",arrivalSchedule);
	 
	 return "home";
 }


}
