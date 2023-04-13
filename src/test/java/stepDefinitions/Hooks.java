package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@UpdatePlace")
	public void beforeScenario() throws IOException {
		
		//execute only when place id null
		
			StepDefinitation_AddPlace_UpdatePlace sd = new StepDefinitation_AddPlace_UpdatePlace();
			sd.add_place_payload("Sudha","Hindi");
			sd.user_calls_with_http_request("addPlaceAPI", "Post");
			sd.api_call_success_with_status_code("200");
			sd.verify_the_place_id_is_the_map_with_using("Sudha", "getPlaceAPI");
			
	}
	

	
}
