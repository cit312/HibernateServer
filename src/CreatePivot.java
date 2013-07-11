import java.util.HashMap;

import com.example.hibernate.DbInteraction;
import com.sun.org.apache.xpath.internal.operations.Bool;


public class CreatePivot implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		//Collect Vars
		data = (HashMap) data.get("data");
		String phone = (String) data.get("number");
		String newPivotName = (String) data.get("newPivot");

		//Get Data from db
		DbInteraction dbInteraction = new DbInteraction();
		
		//Put data into bean
		String error = dbInteraction.addPivots(newPivotName);
		
		//put data into CommBean (serializable)
		CommBean pivotsCommBean = new CommBean();
		pivotsCommBean.setError(error);
		
		return pivotsCommBean;
	}

}
