import java.util.HashMap;

import com.example.hibernate.DbInteraction;


public class GetAppUsers implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		//Collect Vars
		String userID = (String) data.get("userID");

		//Get Data from db
		DbInteraction dbInteraction = new DbInteraction();
		
		//Put data into bean
		HashMap<String,HashMap> app_users = new HashMap<String,HashMap>();
		app_users = dbInteraction.showAllUsers();
		
		//put data into CommBean (serializable)
		CommBean pivotsCommBean = new CommBean();
		pivotsCommBean.setData(app_users);
		
		return pivotsCommBean;
	}

}
