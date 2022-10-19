package com.testdata;

import java.util.ArrayList;
import java.util.List;

import com.pojo.googlemapapi.AddPlace;
import com.pojo.googlemapapi.Location;

public class TestDataBuild {
	//int accuracy, String address, String language, String name, String phone, String website
		public AddPlace addPlacePayload(int accuracy, String address, String language, String name, String phone, String website) {
			AddPlace all=new AddPlace();
			//Set- Serialization
			all.setAccuracy(accuracy);
			all.setAddress(address);
			all.setLanguage(language);
			all.setName(name);
			all.setPhone_number(phone);
			all.setWebsite(website);
			
			List<String> al=new ArrayList<String>();
			al.add("my1");
			al.add("my1");
			all.setTypes(al);
			
			Location ln=new Location();
			ln.setLat(-38.383494);
			ln.setLng(33.427362);
			all.setLocation(ln);
			return all;
		}
		
		public String deletePlacePayload(String placeId) {
			return "{\r\n"
					+ "    \"place_id\":\""+placeId+"\"\r\n"
					+ "}";
		}
}
