/*
 * 
 * THIS IS TO TEST THE APP CONTROLER. JUST TAKE COMMAND
 * AND RETURN FAKE DATA
 * 
 */

import java.util.HashMap;

public class Test implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		// create fake data
		HashMap<String,String> numbers = new HashMap<String,String>();
		numbers.put("one", "more");
		numbers.put("two", "another");
		
		//put data into CommBean (serializable)
		CommBean fakeData = new CommBean();
		fakeData.setData(numbers);
		
		return fakeData;

	}

}
