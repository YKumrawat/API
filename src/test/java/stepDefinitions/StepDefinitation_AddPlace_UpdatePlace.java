package stepDefinitions;
import org.junit.Assert;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitation_AddPlace_UpdatePlace extends Utils {

		RequestSpecification reqSpec_addplace;
		RequestSpecification reqSpec_updateplace;
		ResponseSpecification resp;
		Response response;
		static String  placeId;
    	TestDataBuild data = new TestDataBuild();
  

       	@Given("Add place payload {string} {string}")
    	public void add_place_payload(String name, String language) throws IOException {
         reqSpec_addplace = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language));
	    	}



       	@When("User calls {string} with {string} http request")
       	public void user_calls_with_http_request(String resource, String method) {
       		
       		//Construct will call the value of resource which you pass
            APIResources aPIResource = APIResources.valueOf(resource);
	    	if(resource.equalsIgnoreCase("addPlaceAPI")&&method.equalsIgnoreCase("POST")) 
	    	response = reqSpec_addplace.when().post(aPIResource.getResource());
	    	else if (resource.equalsIgnoreCase("updatePlaceAPI")&&method.equalsIgnoreCase("put")) 
	    		response = reqSpec_updateplace.when().put(aPIResource.getResource());

	    }

	    @Then("API call success with status code {string}")
		public void api_call_success_with_status_code(String statusCode) {
	    	Assert.assertEquals(Integer.parseInt(statusCode), response.getStatusCode());
	    	}
	    

	    @Then("{string} in response body is {string}")
		public void in_response_body_is(String key, String expecctedvalue) { 		
	    	
	    	String resp_status =getJsonPath(response, key);
	    	Assert.assertEquals(expecctedvalue, resp_status);
	 
}
	    
	    
	    @Then("verify the placeId is the map with {string} using {string}")
	    public void verify_the_place_id_is_the_map_with_using(String name, String resource) throws IOException {
	        placeId = getJsonPath(response,"place_id");
	    	RequestSpecification reqSpec_getplace = given().spec(requestSpecification()).
	    			queryParam("place_id", placeId);
	    	APIResources res = APIResources.valueOf(resource);
	    	response = reqSpec_getplace.when().get(res.getResource());
	    	String name_response = getJsonPath(response,"name");
	    	Assert.assertEquals(name, name_response);
	    	
	    	
	    }
	    
	


	    @Given("Add update place payload {string} {string}")
	    public void add_update_place_payload(String address, String key) throws IOException {
	    	System.out.println(placeId);
	    	reqSpec_updateplace = given().spec(requestSpecification()).body(data.updatePlacePayLoad(placeId,address,key));
	    }


	    @Then("verify the response message {string}")
	    public void verify_the_response_message(String msg) {
	       String msg_response = getJsonPath(response, msg);
	       Assert.assertEquals("Address successfully updated", msg_response);
	    }







	}
