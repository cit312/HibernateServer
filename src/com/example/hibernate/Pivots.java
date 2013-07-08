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
    private String  pivot_degrees;
    private String  pivot_direction;
    private String  pivot_status;
    
    
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
    
    public String getDeg() {
        return pivot_degrees;
    }
    public void setDeg(String pivot_degrees) {
        this.pivot_degrees = pivot_degrees;
    }
    
    public String getDirection() {
        return pivot_direction;
    }
    public void setDirection(String pivot_direction) {
        this.pivot_direction = pivot_direction;
    }
    
    public String getStatus() {
        return pivot_status;
    }
    public void setStatus(String pivot_status) {
        this.pivot_status = pivot_status;
    }
    
    
	@Override
	public String toString() {
		return "Pivots [pivot_id=" + pivot_id + ", pivot_name=" + pivot_name
				+ ", pivot_degrees=" + pivot_degrees + ", pivot_direction="
				+ pivot_direction + ", pivot_status=" + pivot_status + "]";
	}
    
    
}