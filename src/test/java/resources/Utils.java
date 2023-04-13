package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.io.FileInputStream;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class Utils {

	// RequestSpecification req making static it will share in all test cases
	public static RequestSpecification req;
	public static ResponseSpecification resp;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		
		//Pass via CLI mvn -Duri=https://rahulshettyacademy.com test
	    // String URI =System.getProperty("uri");
	    //req = new RequestSpecBuilder().setBaseUri(URI).
	    		
		//Passing baseUri value from properties file.
    	 req = new RequestSpecBuilder().setBaseUri(getGobalValue("baseURI")).
		
    	addQueryParam("key", "qaclick123").
    	addFilter(RequestLoggingFilter.logRequestTo(stream)).
    	addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).build();
	return req;}
		
		return req;
		
	}
	
	public ResponseSpecification responseSpecification() {
		resp = new ResponseSpecBuilder().expectStatusCode(200).
    	    	expectContentType(ContentType.JSON).build();
		return resp;
	}
	
	public String getGobalValue(String value) throws IOException {
		Properties prop = new Properties();
		String basepath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(basepath+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		
		String key =prop.getProperty(value);
		return key;
	}
	
	public String getJsonPath(Response response, String key) {
	 	JsonPath js = new JsonPath(response.asString());
	 	return js.get(key).toString();
	}
	
}
