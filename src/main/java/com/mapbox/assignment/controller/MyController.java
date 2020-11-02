package com.mapbox.assignment.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapbox.assignment.model.Itinerary;
import com.mapbox.assignment.service.ItineraryService;

@RestController
public class MyController {
	
	@Autowired
	ItineraryService service;
	
	@GetMapping("/itinerary")
	public ArrayList<Itinerary> getItinerary() {
		ArrayList<Itinerary> response = service.getItinerary();
		return response;
	}

}
