/*
 * The Many-To-Many relationship between User and PhoneNumber objects requires two tables
 * app_user and phone_number with a transition table user_number that maps phone numbers to users.
 * The @ManyToMany annotation shows how to make the User object aware of its phone numbers.
 */
package com.example.hibernate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_users")
public class User{
    
    @Id
    @GeneratedValue
    private Integer user_id;
    private String uname;
    private String uphone;
    
    /*
     * one User can have many phone numbers.  CascadeType.ALL causes associated
     * phone numbers to be delted when a User is deleted.
     */
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="map", 
    		   joinColumns = { @JoinColumn( name="user_id") },
               inverseJoinColumns = { @JoinColumn( name="pivot_id")}
               )
    
    private Set<Pivots> pivots;
    public User() {
        // TODO Auto-generated constructor stub
    }
    
//    public String toString() {
//        return "User [id=" + id + ", pword=" + pword + ", uname=" + uname + ", phoneNumbers]";
//    }
    
    public Integer getId() {
        return user_id;
    }
    public void setId(Integer user_id) {
        this.user_id = user_id;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPhone() {
        return uphone;
    }
    public void setPhone(String uphone) {
        this.uphone = uphone;
    }
    public Set<Pivots> getPivots() {
        return pivots;
    }

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", uname=" + uname + ", uphone="
				+ uphone + ", pivots=" + pivots + "]";
	}
    
}