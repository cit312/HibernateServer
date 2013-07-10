import java.util.HashMap;


public class CreateLog implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		//Collect Vars
		
		//Get the correct bean crap
		HashMap<String,HashMap> pivots = new HashMap<String,HashMap>();
		HashMap<String,String> attr = new HashMap<String,String>();
		attr.put("ID", "1");
		attr.put("Attr2", "Added Log");
		attr.put("Attr3", "160 degrees to the left");
		pivots.put("Pivot 1", attr);
		
		
		//put data into CommBean (serializable)
		CommBean pivotsCommBean = new CommBean();
		pivotsCommBean.setData(pivots);
				
		return pivotsCommBean;
	}

}
