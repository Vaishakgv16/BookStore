package com.testscripts.bookstore;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetBook {

	public static void main(String[] args) {
		RestAssured.baseURI="https://simple-books-api.glitch.me";
		RequestSpecification req=RestAssured.given();
		
		
		Response res=req.get("/books/1");
		System.out.println(res.asPrettyString());
		Assert.assertTrue(res.statusCode()==200);
		String name = JsonPath.from(res.asString()).get("name").toString();
		System.out.println(name);
		Assert.assertTrue(name.equals("The Russian"));
		
		
		Response statusres=req.get("/status");
		System.out.println(statusres.asPrettyString());
		Assert.assertTrue(statusres.statusCode()==200);
		String status = JsonPath.from(statusres.asString()).get("status").toString();
		System.out.println(status);
		Assert.assertTrue(status.equals("OK"));
		
		
	    Response listOfBooksres=req.queryParam("type","fiction").get("/books");
		System.out.println(listOfBooksres.asPrettyString());
		Assert.assertTrue(listOfBooksres.statusCode()==200);
		String type = JsonPath.from(listOfBooksres.asString()).get("type").toString();
		System.out.println(type);
		Assert.assertTrue(type.contains("fiction"));
		
		
		Response ordres = req.get("/orders");
		System.out.println(ordres.asPrettyString());
		Assert.assertTrue(ordres.statusCode()==401);
		
		
		Response ordidres = req.get("/orders/1");
		System.out.println(ordidres.asPrettyString());
		Assert.assertTrue(ordidres.statusCode()==401);
		
		}

}
