package com.testscripts.bookstore;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateOrder {
	
	    static File SubmitOrder=new File("src/test/resources/SubmitOrder.json");
		static File UpdateOrder=new File("src/test/resources/UpdateOrder.json");
		public static String getToken()
		{
			RestAssured.baseURI="https://simple-books-api.glitch.me";
			RequestSpecification req = RestAssured.given();
			Response res = req.header("Content-Type","application/json").body("{\r\n"
					+ "   \"clientName\": \"Vaishak\",\r\n"
					+ "   \"clientEmail\": \"vaishakgv"+System.currentTimeMillis()+"@gmail.com\"\r\n"
					+ "}").post("/api-clients/");
			System.out.println(res.asPrettyString());
			Assert.assertTrue(res.statusCode()==201);
			String token = JsonPath.from(res.asString()).get("accessToken");
			return token;
		}
		public static void main(String[] args) {
			String accessToken = getToken();
			RestAssured.baseURI="https://simple-books-api.glitch.me";
			RequestSpecification req = RestAssured.given();
			Response res = req.header("Content-Type","application/json").header("Authorization","Bearer "+accessToken).body(SubmitOrder).post("/orders");
			System.out.println(res.asPrettyString());
			Object orderId = JsonPath.from(res.asString()).get("orderId");
			System.out.println(orderId);
			Response orderIdres = req.header("Content-Type","application/json").header("Authorization","Bearer "+accessToken).body(UpdateOrder).patch("/orders/orderId");
			System.out.println(orderIdres.asPrettyString());
			//Assert.assertTrue(res.statusCode()==201);
			//Assert.assertTrue(JsonPath.from(res.asString()).get("created").toString().equals("true"));

		}
	
	}


