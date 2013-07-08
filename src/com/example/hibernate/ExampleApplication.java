package com.example.hibernate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
//import org.apache.log4j.Logger;

public class ExampleApplication {
   // final static Logger logger = Logger.getLogger(ExampleApplication.class);
    private List<User> users;
    
    public ExampleApplication() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ExampleApplication aSillyHibernateUseExample = new ExampleApplication();
        //aSillyHibernateUseExample.addNewUsers();
        aSillyHibernateUseExample.showAllUsers();
        //aSillyHibernateUseExample.addPivots();
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
    private void showAllUsers() {
        Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        /*
         * execute a HQL query against the database.  HQL is NOT SQL.  It is object based.
         */
        Query allUsersQuery = session.createQuery("select u from User as u order by u.user_id");
        /*
         * get a list of User instances based on what was found in the database tables.
         */
        users = allUsersQuery.list();
        System.out.println("num users: "+users.size());
        /*
         * iterate over each User instance returned by the query and found in the list.
         */
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()) {
            User element = iter.next();
            System.out.println("--------NEW ITERATION THING-------");
            System.out.println(element.toString());
            System.out.println("num of pivots: "+element.getPivots().size());

            Iterator<Pivots> iter2 = element.getPivots().iterator();
            while(iter2.hasNext()){
            	Pivots element2 = iter2.next();
            	System.out.println(element2.getName());
            }
        }
        transaction.commit();
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
    
    
    
  private void addPivots() {
  Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
  Transaction transaction = session.beginTransaction();
  /*
   * get two User instances from the database using HQL.  This is NOT SQL.  It is object based.
   */
  Query joshuaQuery = session.createQuery("select u from User as u where u.user_id=3");
  User joshuaUser = (User)joshuaQuery.uniqueResult();
  
  /*
   * create a PhoneNumber instance
   */
  Pivots newPivot = new Pivots();
  newPivot.setName("JoshAwesome");
                             
 /*
  * add the shared phone number to the joshuaUser 
  */
 
 Set<Pivots> joshuaPivots = joshuaUser.getPivots(); 
 joshuaPivots.add(newPivot);

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
 showAllUsers();
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