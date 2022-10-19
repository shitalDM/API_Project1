package com.stepdefinitions;

import static io.restassured.RestAssured.given;
import com.pojo.googlemapapi.Location;
import com.testdata.APIResources;
import com.testdata.TestDataBuild;
import com.testdata.Utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.pojo.googlemapapi.AddPlace;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils{
	public RequestSpecification reqSpec,req;
	public ResponseSpecification resSpec;
	public Response response;
	TestDataBuild data=new TestDataBuild();
	public static String placeId;
	
	@Given("AddPlace payload {int},{string},{string},{string},{string}  and {string}")
	public void add_place_payload(int accuracy, String address, String language, String name, String phone, String website) {
		req=given().spec(requestSpecifications())
				.body(data.addPlacePayload(accuracy,address,language,name,phone,website)); //accuracy,address,language,name,phone,website
	}
	
	@When("user calls {string} with {string} http request and")
	public void user_calls_with_http_request_and(String reqType, String methodName) {
		resSpec=responseSpecifications();
		//reqType=AddPlaceAPI
		//methodName=POST
		APIResources resApi=APIResources.valueOf(reqType);
		System.out.println(APIResources.valueOf(reqType));
		if(methodName.equalsIgnoreCase("POST")) {
		response=req.when().post(resApi.getResource())
				.then().spec(resSpec).extract().response(); 
		}else if(methodName.equalsIgnoreCase("GET")) {
			response=req.when().get(resApi.getResource())
					.then().spec(resSpec).extract().response(); 
		}
	}
	
	@Then("API call got is success with status code is {int}")
	public void api_call_got_is_success_with_status_code_is(int statusCode) {
		Assert.assertEquals(response.getStatusCode(), statusCode);
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		String actualValue=getJsonPathValue(response,key);
		Assert.assertEquals(actualValue,expectedValue);
	}
	
	@And("verify {string} in the input using placeId of {string} request")
	public void verify_in_the_input_using_place_id_of_request(String expectedName, String requestType) {
		placeId=getJsonPathValue(response, "place_id");
		req=given().spec(requestSpecifications()).queryParam("place_id", placeId);
		user_calls_with_http_request_and(requestType,"GET");
		String actualName=getJsonPathValue(response,"name");
		System.out.println("actual name: "+actualName);
		Assert.assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlaceAPI payload")
	public void delete_place_api_payload() {
		req=given().spec(requestSpecifications())
					.body(data.deletePlacePayload(placeId));
	}
	
}
