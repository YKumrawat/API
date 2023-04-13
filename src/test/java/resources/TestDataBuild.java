package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import pojo.UpdatePlace;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language) {
		AddPlace ap = new AddPlace();
    	
    	Location loc = new Location();
    	loc.setLat(-38.383494);
    	loc.setLng(33.427362);
    	
    	ap.setLocation(loc);
    	ap.setAccuracy(50);
    	ap.setName(name);
    	ap.setPhone_number("(+91) 983 893 3937");
    	ap.setAddress("29, side layout, cohen 09");
    	
    	List<String> mylist = new ArrayList<String>();
    	mylist.add("shoe park");
    	mylist.add("shop");
    	ap.setTypes(mylist);
    	ap.setWebsite("http://google.com");
    	ap.setLanguage(language);
    	
    	return ap;
	}
	
	public UpdatePlace updatePlacePayLoad(String place_id,String address, String key ) {
		UpdatePlace up = new UpdatePlace();
		up.setAddress(address);
		up.setPlace_id(place_id);
		up.setKey(key);
		
		return up;
	}
	
}
