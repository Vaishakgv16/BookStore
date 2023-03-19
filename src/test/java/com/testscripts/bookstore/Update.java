package com.testscripts.bookstore;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Update {
	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req = RestAssured.given();
		Response res = req.header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").patch("api/users/2");
		System.out.println(res.asPrettyString());
		Assert.assertTrue(res.statusCode()==200);
		String update = JsonPath.from(res.asString()).get("updatedAt").toString();
		System.out.println(update);
	}

}
