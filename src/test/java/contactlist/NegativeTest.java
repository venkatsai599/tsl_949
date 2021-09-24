package contactlist;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class NegativeTest {
	@Test(enabled=true,description="Gettting contact whichdoes not exist")
	public void getSpecificContact() {
		System.out.println("Getting Contact");
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts/5")
			.then()
				.log()
				.body()
				.statusCode(404);
	}
	
	@Test(enabled=false,description="Add Specific Contact")
	public void addContact() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Amy");
		data.put("lastName", "Sharma");
		//data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err =given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals("Contact validation failed:email:Email is required",err);
	}
	@Test(enabled=true,description="Add Specific Contact")
	public void MaxLength() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "AmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmyAmy");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err =given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
						Assert.assertEquals(true,err.contains("is longer than the maximum allowed length"));
	}
	@Test(enabled=true,description="Wrong Parameters")
	public void wrongparameters() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		data.put("firstName", "778");
		data.put("lastName", "999");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err =given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
						Assert.assertEquals(false,err.contains("wrong parameters passed"));
	}
	@Test(enabled=true,description="Wrong Format")
	public void wrongFormat() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		data.put("firstName", "amy");
		data.put("lastName","jakson");
		data.put("email", "mpremchand99.gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err =given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
						Assert.assertEquals(err.contains("Contacts validation failed"),true);
	}
}
