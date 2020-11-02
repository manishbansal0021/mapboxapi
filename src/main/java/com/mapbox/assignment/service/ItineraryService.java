package com.mapbox.assignment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mapbox.assignment.model.Itinerary;


@Service
public class ItineraryService {
	
	public final String URL = "https://api.mapbox.com/geocoding/v5/mapbox.places/Istanbul,winter,tourist,museum.json?limit=10&access_token=pk.eyJ1IjoibWJhbnMiLCJhIjoiY2tnejhnanAzMTFiNDJ6cjF0NjFrYzFtcyJ9.0qzk1OV51uLQFsJlBSH3fg";
	
	public ArrayList<Itinerary> getItinerary() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response
		  = restTemplate.getForEntity(URL, String.class);
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(response.getBody());
		JsonArray array = object.getAsJsonArray("features");
		ArrayList<Itinerary> arr = new ArrayList<>();
		for(int i=0;i<10;i++) {
			JsonObject obj = (JsonObject) array.get(i);
			Itinerary itinerary = new Itinerary();
			itinerary.setName(obj.get("text").getAsString());
			
			JsonObject properties = obj.getAsJsonObject("properties");
			String category = properties.get("category").getAsString();
			List<String> categoryarr = Arrays.asList(category.split(","));
			itinerary.setCategories(categoryarr);
			
			JsonArray jsonArray = obj.getAsJsonArray("context");
			JsonObject jsonObj = (JsonObject) obj.getAsJsonArray("context").get(0);
			String region = jsonObj.get("text").getAsString();
			itinerary.setRegion(region);
			
			arr.add(itinerary);
			
		}
		
		

		return arr;
	}

}
