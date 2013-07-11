package com.example.hibernate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
//import org.apache.log4j.Logger;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONUtilities;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class DbInteraction {
   // final static Logger logger = Logger.getLogger(ExampleApplication.class);
    private List<User> users;
    
    public DbInteraction() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DbInteraction aSillyHibernateUseExample = new DbInteraction();
        //aSillyHibernateUseExample.addNewUsers();
        //System.out.println(aSillyHibernateUseExample.showAllUsers());
        System.out.println(aSillyHibernateUseExample.addLog("HERHINGasdfasdf", 1));
        //System.out.println("----------------------------------------");
        //System.out.println(aSillyHibernateUseExample.showAllUsers());
        //System.out.println(aSillyHibernateUseExample.addPivots());
        //aSillyHibernateUseExample.deletePivots();
        //aSillyHibernateUseExample.modifyUser();
        //aSillyHibernateUseExample.addSharedPhoneNumber();
        //aSillyHibernateUseExample.deleteAddedUsers();
    }
    /*
     * show how to add records to the database
     */
    private void addNewUsers() {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        /*
         * all database interactions in Hibernate are required to be inside a transaction.
         */
        Transaction transaction = session.beginTransaction();
        /*
         * create some User instances.
         */
        User aNameUser = new User();
        aNameUser.setUname("Jason");
        aNameUser.setPhone("2083906666");
        
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
    } 
    
    
    /*
     * show how to get a collection of type List containing all of the records in the app_user table
     */
    public HashMap showAllUsers() {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        /*
         * execute a HQL query against the database.  HQL is NOT SQL.  It is object based.
         */
        Query allUsersQuery = session.createQuery("select u from User as u where uphone=2083905008");
        /*
         * get a list of User instances based on what was found in the database tables.
         */
        //users = allUsersQuery.list();
        User user = (User) allUsersQuery.uniqueResult();
        
        //System.out.println("num users: "+users.size());
        /*
         * iterate over each User instance returned by the query and found in the list.
         */
        //HashMap mapUsers = new HashMap();
        //Iterator<User> iter = users.iterator();
        
        //while(iter.hasNext()) {
         //   User element = iter.next();
            
            //put stuff into hashmap
            HashMap temp = new HashMap();
            temp.put("id", ((User) user).getId());
            temp.put("uName", ((User) user).getUname());
            temp.put("uPhone", ((User) user).getPhone());
            
            
//            System.out.println("num of pivots: "+element.getPivots().size());
//
            Iterator<Pivots> iter2 = ((User) user).getPivots().iterator();
            HashMap allTempPivots = new HashMap();
            while(iter2.hasNext()){
            	Pivots element2 = iter2.next();
            	
            	HashMap tempPivot = new HashMap();
            	tempPivot.put("pivot_id", element2.getId());
            	
            	
            	//Get all them note logs
            	Iterator<PivotLog> iter3 = element2.getNotes().iterator();
            	HashMap allTempLog = new HashMap();
                while(iter3.hasNext()){
                	PivotLog element3 = iter3.next();
                	HashMap tempLog = new HashMap();
                	
                	tempLog.put("note_id", element3.getNote_id());
                	tempLog.put("note", element3.getNote());
                	//System.out.println("HURR: " + element3.getNote());
                	allTempLog.put(element3.getNote_id(), tempLog);
                }
                tempPivot.put("Notes", allTempLog);
                
                allTempPivots.put(element2.getName(), tempPivot);
            }
            
            temp.put("Pivots", allTempPivots);
           // mapUsers.put("Data", temp);
        //}
        transaction.commit();
        
        
        return temp;
    }
    
//    /*
//     * show how to modify a database record
//     */
//    private void modifyUser() {
//        
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        /*
//         * get a single User instance from the database.
//         */
//        Query singleUserQuery = session.createQuery("select u from User as u where u.uname='Brayden'");
//        User leeUser = (User)singleUserQuery.uniqueResult();
//        /*
//         * change the user name for the Java instance
//         */
//        leeUser.setUname("Joshua");
//        /*
//         * call the session merge method for the User instance in question.  This tells the database that the instance is ready to be permanently stored.
//         */
//        session.merge(leeUser);
//        
//        /*
//         * call the transaction commit method.  This tells the database that the changes are ready to be permanently stored.
//         */
//        transaction.commit();
//        /*
//         * permanently store the changes into the database tables.
//         */
//        showAllUsers();
//    }
//    
	  public String addLog(String log, long id) {
		  Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		  Transaction transaction = session.beginTransaction();
	  /*
	   * get two User instances from the database using HQL.  This is NOT SQL.  It is object based.
	   */
		  Query joshuaQuery = session.createQuery("select u from Pivots as u where pivot_id=" + id);
		  Pivots joshuaUser = (Pivots) joshuaQuery.uniqueResult();
	  /*
	   * create a PhoneNumber instance
	   */
		  PivotLog pivotLog = new PivotLog();
		  pivotLog.setNote(log);
//	  	Pivots newPivot = new Pivots();
//	  	newPivot.setName(log);
	                             
		  /*
		   * 	add the shared phone number to the joshuaUser 
		   */
	 
		  Set<PivotLog> userNotes = joshuaUser.getNote(); 
		  userNotes.add(pivotLog);
	
		  session.save(joshuaUser);
		  /*
	  * 	inform the database that the modified User instances should be ready for permanent storage.
	  	*/
		  session.merge(joshuaUser);
		  /*
		   * permanently store the changes into the database tables.
		   */
		  transaction.commit();
		  /*
		   * show that the database was updated by printing out all of the User instances created by a HQL query
		   */
		  //showAllUsers();
	 
		  return "null";
	}    
    
    
    
    
    
	  public String addPivots(String newPivotName) {
	  Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
	  Transaction transaction = session.beginTransaction();
	  /*
	   * get two User instances from the database using HQL.  This is NOT SQL.  It is object based.
	   */
	  Query joshuaQuery = session.createQuery("select u from User as u where uphone=2083905008");
	  User joshuaUser = (User)joshuaQuery.uniqueResult();
	  
	  /*
	   * create a PhoneNumber instance
	   */
	  Pivots newPivot = new Pivots();
	  newPivot.setName(newPivotName);
	                             
	 /*
	  * add the shared phone number to the joshuaUser 
	  */
	 
	 Set<Pivots> joshuaPivots = joshuaUser.getPivots(); 
	 joshuaPivots.add(newPivot);
	 
	 System.out.println("HURR: " + joshuaPivots.toString());
	
	 session.save(newPivot);
	 /*
	  * inform the database that the modified User instances should be ready for permanent storage.
	  */
	 session.merge(joshuaUser);
	 /*
	  * permanently store the changes into the database tables.
	  */
	 transaction.commit();
	 /*
	  * show that the database was updated by printing out all of the User instances created by a HQL query
	  */
	 //showAllUsers();
	 
	 return "null";
	}    
    
    
    private void deletePivots() {
    	
    	 Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
         Transaction transaction = session.beginTransaction();
         /*
          * execute a HQL query against the database.  HQL is NOT SQL.  It is object based.
          */
         Query allPivotsQuery = session.createQuery("select u from Pivots as u where u.pivot_id = 3");
         /*
          * get a list of User instances based on what was found in the database tables.
          */
         Pivots pivot = (Pivots) allPivotsQuery.uniqueResult();
         
         session.delete(pivot);
         transaction.commit();
        }
    
    
//    private void deleteAddedUsers() {
//        // TODO Auto-generated method stub
//        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//        
//        int numPivots = pivots.size();
//        for(int i = 0; i < numUsers; i++){
//            System.out.println("deleting user "+users.get(i).getUname());
//            User aUser = users.get(i);
//            session.delete(users.get(i));
//        }
//        transaction.commit();
//        /*
//         * at this point the records have been removed from the database but still exist in our class list attribute.
//         * Do not store lists retrieved from the database since they will be out of synch with the database table from which they come.
//         * This example shows that you should not store retrieved lists.
//         */
//        System.out.println(users);
//        users.remove(2);
//        users.remove(2);
//        /*
//         * now the Java instances are also gone and the database is back to its original state so the example application can be run again.
//         */
//        System.out.println(users);
//    }
                                   
                                   }