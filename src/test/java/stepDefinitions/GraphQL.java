package stepDefinitions;

import static io.restassured.RestAssured.*;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQL {
	
public static void main (String[]args) {
	String response = given().log().all().header("Content-Type", "application/json").
	body("{\"query\":\"query($name:String!,$id: String!){\\n  \\n  locations(filters:{name:$name}){\\n    info{\\n      count\\n    }\\n    result{\\n      id\\n      name\\n      }\\n  }\\n  location(locationId:2399){\\n      id\\n      name\\n    }\\n  \\n  episodes(filters:{ episode:$id}){\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      air_date\\n    }\\n  }\\n    \\n  }\\n\",\"variables\":{\"name\":\"Aus\",\"id\":\"1\"}}").
	when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

JsonPath js = new JsonPath(response);
int count =js.get("data.locations.info.count");
Assert.assertEquals(19, count);
System.out.println(response);
System.out.println("working on branch");
<<<<<<< HEAD
System.out.println("Develop");
System.out.println("new change in develop");
=======
System.out.println("working on branch-new");

>>>>>>> f6771fa0295ddc132a7d93746796cfbe31be2f18



}

}
