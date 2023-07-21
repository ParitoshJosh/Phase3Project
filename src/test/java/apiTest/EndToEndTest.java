package apiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EndToEndTest {

	Response response1;

	@Test
	public void testCases() {

		System.out.println("**************TC 1 Get all employees*********");
		// Validate response code is 200
		response1 = Methods.GetAllEmployees();
		System.out.println(response1.body().asString());

		int StatusCode = response1.getStatusCode();
		System.out.println("***TC 1*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 200);

		// Validate that number of employees are 3
		int NumberOfEmployees = response1.jsonPath().getList("employees").size();
		System.out.println("***TC 1*** NumberOfEmployees" + NumberOfEmployees);
		Assert.assertEquals(NumberOfEmployees, 3);

		System.out.println("**************TC 2 Create employee*********");
		// Validate the response code is 201
		response1 = Methods.CreateEmployee("John", "Abraham", 100000, "john@xyz.com");
		StatusCode = response1.getStatusCode();
		System.out.println("***TC 2*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 201);

		// Validate that name is coming as John
		String employeeName = response1.jsonPath().getString("firstName");
		Assert.assertEquals(employeeName, "John");
		System.out.println("***TC 2*** employeeName:" + employeeName);

		// Validate that number of employees are 4
		NumberOfEmployees = Methods.GetAllEmployees().jsonPath().getList("employees").size();
		System.out.println("***TC 2*** NumberOfEmployees" + NumberOfEmployees);
		Assert.assertEquals(NumberOfEmployees, 4);

		// employeeID noted for the created member John
		int employeeID = response1.jsonPath().get("id");
		System.out.println("***TC 2*** id of new created member John is " + employeeID);

		System.out.println("**************TC 3 Update employee*********");
		// update employee name
		response1 = Methods.UpdateEmployee(employeeID, "Tom", "Hanks", 20000, "tom@xyz.com");

		// Validate that status codes is 200
		StatusCode = response1.getStatusCode();
		System.out.println("***TC 3*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 200);

		// Validate that name is coming as Tom
		employeeName = response1.jsonPath().getString("firstName");
		Assert.assertEquals(employeeName, "Tom");
		System.out.println("***TC 3*** employeeName:" + employeeName);

		// Validate that john is no longer present in response
		Assert.assertNotEquals(response1.jsonPath().getString("firstName"), "John");

		System.out.println("**************TC 4 Get single employee*********");
		// Validate that response code is 200
		response1 = Methods.GetSingleEmployee(employeeID);
		StatusCode = response1.getStatusCode();
		System.out.println("***TC 4*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 200);

		// Validate that name is coming as Tom
		employeeName = response1.jsonPath().getString("firstName");
		System.out.println("***TC 4*** employeeName:" + employeeName);
		Assert.assertEquals(employeeName, "Tom");

		System.out.println("**************TC 5 Delete employee*********");
		// Validate that response code is 200
		response1 = Methods.DeleteEmployee(employeeID);
		StatusCode = response1.getStatusCode();
		System.out.println("***TC 5*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 200);

		// Validate that Tom is no longer present in response
		response1 = Methods.GetSingleEmployee(employeeID);		
		employeeName = Methods.GetSingleEmployee(employeeID).asString();
		System.out.println("***TC 5*** employeeName:" + employeeName);
		Assert.assertNotEquals(response1.jsonPath().getString("firstName"), "Tom");
	
		System.out.println("**************TC 6 Get single employee*********");
		// Validate that response code is 400
		response1 = Methods.GetSingleEmployee(employeeID);
		StatusCode = response1.getStatusCode();
		System.out.println("***TC 6*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 400);
		
		System.out.println("**************TC 7 Get all employees*********");
		// Validate response code is 200
		response1 = Methods.GetAllEmployees();
		System.out.println(response1.body().asString());

		StatusCode = response1.getStatusCode();
		System.out.println("***TC 7*** statusCode :" + StatusCode);
		Assert.assertEquals(StatusCode, 200);

		// Validate that number of employees are 3
		 NumberOfEmployees = response1.jsonPath().getList("employees").size();
		System.out.println("***TC 1*** NumberOfEmployees" + NumberOfEmployees);
		Assert.assertEquals(NumberOfEmployees, 3);
	}

}
