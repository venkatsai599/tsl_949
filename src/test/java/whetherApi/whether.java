package whetherApi;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class whether {
	@Test(enabled=true,description="getting Whether information of specific city")
	public void gettingwhtherinfo() {
					given()
					.when()
					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=981d0095475c811603828a5cfd5f9f2b")
					.then()
						.log()			//Print
						.body()
						.statusCode(200);   // Assertion
	}
	@Test(enabled=true,description="getting Whether information of specific city")
	public void gettingwhtherinfo2() {
	
		Response res = RestAssured.given()
					.when()
					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=981d0095475c811603828a5cfd5f9f2b");
					System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
	
		
	}
	@Test(enabled=true,description="getting Whether information of specific city")
	public void gettingwhtherinfo3() {
	
						RestAssured.given()
						.queryParam("q","pune")
						.queryParam("appid","981d0095475c811603828a5cfd5f9f2b")
					.when()
					.get("http://api.openweathermap.org/data/2.5/weather")
						.then()
						.log()			//Print
						.body()
						.statusCode(200); 
	
	}
	@Test(enabled=true,description="getting Whether information of specific city")
	public void gettingwhtherinfo4() {
		Map<String,String> Param = new HashMap<String,String>();
		Param.put("q","pune");
		Param.put("appid","981d0095475c811603828a5cfd5f9f2b");

		RestAssured.given()
		.queryParams(Param)
		
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
		.then()
		.log()			//Print
		.body()
		.statusCode(200); 

}
}
