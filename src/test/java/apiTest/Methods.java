package apiTest;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Methods {

	static RequestSpecification request = RestAssured.given().baseUri("http://localhost:8088/employees");

	public static Response GetAllEmployees() {

		Response response = request.get();
		return response;

	}

	public static Response CreateEmployee(String firstName, String lastName, int salary, String email) {

		JSONObject jObj = new JSONObject();
		jObj.put("firstName", firstName);
		jObj.put("lastName", lastName);
		jObj.put("salary", salary);
		jObj.put("email", email);

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jObj.toString()).post();
		return response;

	}

	public static Response UpdateEmployee(int id, String firstName, String lastName, int salary, String email) {

		JSONObject jObj = new JSONObject();
		jObj.put("firstName", firstName);
		jObj.put("lastName", lastName);
		jObj.put("salary", salary);
		jObj.put("email", email);

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jObj.toString())
				.put("/" + id);
		return response;
	}

	public static Response GetSingleEmployee(int id) {

		Response response = request.get("/" + id);
		return response;

	}
	
	public static Response DeleteEmployee(int id) {

		Response response = request.delete("/" + id);
		return response;

	}
	
}
