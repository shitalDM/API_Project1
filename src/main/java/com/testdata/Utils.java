package com.testdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	public ResponseSpecification res;
	PrintStream log;
	public RequestSpecification requestSpecifications() {
		if (req==null) {
			try {
				log=new PrintStream(new FileOutputStream("output.txt"));
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			req=new RequestSpecBuilder()
				.setBaseUri(getPropertiesValue("baseUrl"))
				.setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
			return req;
		}
		return req;
	}
	public ResponseSpecification responseSpecifications() {
		res=new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();
		return res;
	}
	 public static String getPropertiesValue(String key) {
		 String filepath=System.getProperty("user.dir");
		 Properties prop=new Properties();
		 try {
			FileInputStream fis=new FileInputStream(filepath+"\\src\\main\\java\\com\\testdata\\Globals.properties");
			//System.out.println(filepath+"\\com\\testdata\\Globals.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return prop.getProperty(key);
	 }

	 //Created method which accepts response & key to get json value
	 public String getJsonPathValue(Response response, String key) {
		 String resp=response.asString();
		 JsonPath js=new JsonPath(resp);
		 String ky=js.get(key);
		 return ky;
	 }
}
