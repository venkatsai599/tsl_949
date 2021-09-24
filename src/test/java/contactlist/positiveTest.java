package contactlist;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class positiveTest {
	String id;
	@Test(enabled=false,description="getting All contact information")
	public void gettingAllContact() {
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts")
			.then()
			.log()    //print
			.body()
			.statusCode(200);	//assertion
	}
	@Test(enabled=true,description="Add Specific Contact")
	public void addContact() {
		
		JSONObject data=new JSONObject();

		JSONObject emp=new JSONObject();

		JSONObject loc=new JSONObject(); emp.put("jobTitle", "QA");

		emp.put("company", "LTI");

		loc.put("city", "Mumbai"); loc.put("country", "India");

		data.put("firstName", "Amy");

		data.put("lastName", "Sharma"); data.put("email", "mpremchand99@gmail.com");

		data.put("location", loc); data.put("employer", emp);

		String id= given()

				.header("Content-Type", "application/json") .body (data.toJSONString())

		.when()

				.post("http://3.13.86.142:3000/contacts")

		.then()

		.log()

		.body()

		.statusCode (200) 

				.extract().path("_id"); System.out.println("Id is "+id);
	}
	@Test(enabled=false,description="Get Specific Contact")
	public void getSpecificContact() {
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts/6149774ff2967f0ec8939016")
		.then()
			.log()
			.body()
			.statusCode(200);
	}
	@Test(enabled=true,dependsOnMethods="getSpecificContact",description="Updating Contact info")
	public void updateContact() {
		System.out.println("Updating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Premchand");
		data.put("lastName", "Vishwakarma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
				 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.put("http://3.13.86.142:3000/contacts/"+id)
					.then()
						.log()			//Print
						.body()
						.statusCode(204);   // Assertion
	}
	@Test(enabled=true,dependsOnMethods="updateContact",description="Get Specific Contact")
	public void deleteContact() {
		System.out.println("Deleting Contact");
					given()
					.when()
						.delete("http://3.13.86.142:3000/contacts/"+id)
					.then()
						.log()			//Print
						.body()
						.statusCode(204);   // Assertion
	}	

	
}
	


