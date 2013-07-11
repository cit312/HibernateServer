import java.util.HashMap;

import com.example.hibernate.DbInteraction;


public class CreateLog implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		//Collect Vars
		data = (HashMap) data.get("data");
		String phone = (String) data.get("number");
		String newLog = (String) data.get("newLog");
		Long pivotID = (long) data.get("pivotID");

		//Get Data from db
		DbInteraction dbInteraction = new DbInteraction();
		
		//Put data into bean
		String error = dbInteraction.addLog( newLog, pivotID);
		
		//put data into CommBean (serializable)
		CommBean pivotsCommBean = new CommBean();
		pivotsCommBean.setError(error);
		
		return pivotsCommBean;
	}

}
