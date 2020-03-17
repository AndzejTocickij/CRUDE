package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Controller
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	// Method to create new object
	@RequestMapping("/create")
	public String create(Alien alien){
		if(alien.getName().isEmpty() || alien.getId() == 0 || alien == null ) {
			throw new IllegalArgumentException("Input can not be empty or it is invalid");
		} else {
			repo.save(alien);
		}
		
		return "home.jsp";
	}
	// Method to show all existing objects
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(id).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
}
