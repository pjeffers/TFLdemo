package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.ArrivalSchedule;
import com.example.demo.service.ArrivalsService;

@Controller
public class ArrivalsController {

	@Autowired
	ArrivalsService arrivalsService;

	@RequestMapping("home")
	public String home(Model model) {

		// get the arrival schedule. No need to pass a search parameter as the Arrivals
		// service  does not currently search for the station details and is hard-coded for
		// Great Portland Street
		ArrivalSchedule arrivalSchedule;
		try {
			arrivalSchedule = arrivalsService.getTubeArrivals(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		
		}
		model.addAttribute("arrivalSchedule", arrivalSchedule);

		return "arrivals";
	}

	@ExceptionHandler(value = Exception.class)
	public String handleException() {

		return "error";
	}
}
