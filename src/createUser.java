import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate.HibernateUtilSingleton;
import com.example.hibernate.User;


public class createUser implements Handler {

	@Override
	public CommBean handleIt(HashMap data) {
		// Collect VARs
		String name = (String) data.get("userName");
		String pass = (String) data.get("password");
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        /*
         * all database interactions in Hibernate are required to be inside a transaction.
         */
        Transaction transaction = session.beginTransaction();
        /*
         * create some User instances.
         */
        User aNameUser = new User();
        aNameUser.setUname(name);
        aNameUser.setPword(pass);
        
       /*
         * save each instance as a record in the database
         */
        session.save(aNameUser);
        transaction.commit();
        /*
         * prove that the User instances were added to the database and that
         * the instances were each updated with a database generated id.
         */
        System.out.println("aUser generated ID is: " + aNameUser.getId());
        
        return null;
	}

}
