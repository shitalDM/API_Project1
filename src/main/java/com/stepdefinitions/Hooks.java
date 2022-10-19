package com.stepdefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() {
		//execute this code only when place id is null
		//write code to generate new place id
		StepDefinition step=new StepDefinition();
		if(step.placeId==null) {
			step.add_place_payload(10, "India", "Sanskrit", "Ram", "1231231231", "ram@gok.com");
			step.user_calls_with_http_request_and("AddPlaceAPI", "POST");
			step.verify_in_the_input_using_place_id_of_request("Ram", "GetPlaceAPI");
		}
	}
}
