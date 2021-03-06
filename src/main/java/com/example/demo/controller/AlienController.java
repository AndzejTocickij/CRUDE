package com.example.demo.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// to create new object
	@RequestMapping("/create")
	public String create(Alien alien) {
		if (alien.getName().isEmpty() || alien.getId() == 0 || alien == null) {
			throw new IllegalArgumentException("Input can not be empty or it is invalid");
		} else {
			repo.save(alien);
		}

		return "home.jsp";
	}

	// to show all existing objects
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(id).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
	// show object by it's ID
	@RequestMapping(value="/getAlien/{id}")
	public ResponseEntity<Alien> getAlienById(@PathVariable("id") int userId)  {
		Alien alien = repo.findById(userId)
				.orElseThrow(() ->  new IllegalArgumentException() ) ;
		return ResponseEntity.ok().body(alien);
	}

	// to update existing object by id
	@PutMapping(value="/update/{id}")
	public ResponseEntity<Alien> update(@PathVariable("id") int id, @Valid @RequestBody Alien userDetails) {
		Alien alien = repo.findById(id).orElseThrow(() -> new IllegalArgumentException() );
		alien.setId(userDetails.getId());
		alien.setName(userDetails.getName());
		alien.setTech(userDetails.getTech());
		final Alien updatedAlien = repo.save(alien);
		return ResponseEntity.ok(updatedAlien);
	}
	
	// to delete existing object by id
	@DeleteMapping(value="/delete/{id}")
	public String deleteAlien(@PathVariable("id") int userId) {
		Alien alien = repo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not foind " + userId) );
		repo.delete(alien);
		return "User has been deleted.";
		
	}
	
}
