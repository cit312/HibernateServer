import java.util.HashMap;


public class GetPivots implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		//Collect Vars
		String userID = (String) data.get("userID");
		
		//Get the correct bean crap
		HashMap<String,HashMap> pivots = new HashMap<String,HashMap>();
		HashMap<String,String> attr = new HashMap<String,String>();
		attr.put("Attr1", "It is 60 seconds late");
		attr.put("Attr2", "Last changed on that day");
		attr.put("Attr3", "60 degrees to the left");
		pivots.put("Pivot 1", attr);
		attr = new HashMap<String,String>();
		attr.put("Attr1", "It is 160 seconds late");
		attr.put("Attr2", "changed on that day");
		attr.put("Attr3", "160 degrees to the left");
		pivots.put("Pivot 2", attr);
		
		//Put bean into hashmap
		
		
		//put data into CommBean (serializable)
		CommBean pivotsCommBean = new CommBean();
		pivotsCommBean.setData(pivots);
		
		return pivotsCommBean;
	}

}
