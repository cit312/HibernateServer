import java.util.HashMap;


public class ApplicationController {
	private HashMap<String, Handler> commMap;
	
	public ApplicationController(){
		commMap = new HashMap<String, Handler>();
	//	commMap.put("getPivots",new getPivots());
		commMap.put("createUser",new createUser());
	}
	
	public void handleRequest(String command, HashMap data){
		Handler hand = commMap.get(command);
		hand.handleIt(data);
	}
}
