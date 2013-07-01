import java.util.HashMap;

public class TestMain {

	public static void main(String[] args) {
/*		//Incoming Data
		HashMap<String, String> fakeInfo = new HashMap<String, String>();
		fakeInfo.put("command", "createUser");
		fakeInfo.put("userName", "Johnny");
		fakeInfo.put("password", "Sally");
		//Collect Data and Handle it
		ApplicationController appcntrl = new ApplicationController();
		appcntrl.handleRequest(fakeInfo.get("command"), fakeInfo);   */
		
		Client c = new Client();
		c.transmit();
	}

}