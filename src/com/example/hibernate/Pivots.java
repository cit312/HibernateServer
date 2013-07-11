package com.example.hibernate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pivots")
public class Pivots {
    
    @Id
    @GeneratedValue
    private Integer pivot_id;
    private String  pivot_name;
    private String  pivot_notes;
    
    
    public Integer getId() {
        return pivot_id;
    }
    public void setId(Integer pivot_id) {
        this.pivot_id = pivot_id;
    }
    
    public String getName() {
        return pivot_name;
    }
    public void setName(String pivot_name) {
        this.pivot_name = pivot_name;
    } 
 
	public String getPivot_notes() {
		return pivot_notes;
	}
	public void setPivot_notes(String pivot_notes) {
		this.pivot_notes = pivot_notes;
	}
	
	@Override
	public String toString() {
		return "Pivots [pivot_id=" + pivot_id + ", pivot_name=" + pivot_name
				+ ", pivot_notes=" + pivot_notes + "]";
	}
    
}