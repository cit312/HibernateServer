package com.example.hibernate;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "pivots")
public class Pivots{
    
    @Id
    @GeneratedValue
    private Integer pivot_id;
    private String  pivot_name;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="pivotsMap", 
    		   joinColumns = { @JoinColumn( name="pivot_id") },
               inverseJoinColumns = { @JoinColumn( name="note_id")}
               )    
    private Set<PivotLog> note;
    
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
	
	public Set<PivotLog> getNotes() {
		return note;
	}
	public void setNote(Set<PivotLog> note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Pivots [pivot_id=" + pivot_id + ", pivot_name=" + pivot_name
				+ ", note=" + note + "]";
	}

	
    
}