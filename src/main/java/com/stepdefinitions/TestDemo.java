package com.stepdefinitions;

import java.io.FileInputStream;

public class TestDemo {
	public static void main(String[] args) {
		 String filepath=System.getProperty("user.dir");
		 //FileInputStream fis=new FileInputStream(filepath+"\\com\\testdata\\Globals.properties");
		System.out.println(filepath+"\\com\\testdata\\Globals.properties");
	}
}
/*Scenario Outline: Verify if place is being successfully added using addPlaceAPI
	Given AddPlace payload <Accuracy>,"<Address>","<Language>","<Name>","<Phone_number>" and "<Website>"
	When user calls "addPlaceAPI" with POST http request
	Then API call got is success with status code is 200
	And "status" in response body is "OK"
	
	Examples: 
|Accuracy|    Address    |Language| Name  |Phone_number|Website|
|51|Line 23, UAS|English|XXX ZZZ|9874563210|abc@gyn.org|
|81|road bay, Seashore, HK|CHinese|Feng Lang|9874563210|axc@gym.org|

	*/
