import java.util.HashMap;


public class ApplicationController {
	private HashMap<String, Handler> commMap;
	
	public ApplicationController(){
		commMap = new HashMap<String, Handler>();
		commMap.put("getPivots",new GetPivots());
		commMap.put("createPivot",new CreatePivot());
		commMap.put("createUser",new createUser());
		commMap.put("test",new Test());
	}
	
	public CommBean handleRequest(String command, HashMap data){
		Handler hand = commMap.get(command);
		return hand.handleIt(data);
	}
}
