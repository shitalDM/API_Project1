Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using addPlaceAPI
	Given AddPlace payload <Accuracy>,"<Address>","<Language>","<Name>","<Phone_number>"  and "<Website>"
	When user calls "AddPlaceAPI" with "POST" http request and
	Then API call got is success with status code is 200
	And "status" in response body is "OK"
	And verify "<Name>" in the input using placeId of "GetPlaceAPI" request
	

Examples: 
|Accuracy|    Address    |Language| Name  |Phone_number|Website|
|51|Line 23, UAS|English| Shital|874563210|abc@gyn.org|
|81|road bay, Seashore, HK|CHinese|Fang Lang|9874563210|axc@gym.org|

@DeletePlace @Regression
Scenario: Verify if place is being successfully deleted using DeletePlaceAPI
	Given DeletePlaceAPI payload 
	When user calls "DeletePlaceAPI" with "POST" http request and
	Then API call got is success with status code is 200
	And "status" in response body is "OK"
	

