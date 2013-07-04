import java.io.Serializable;
import java.util.HashMap;


public class CommBean implements Serializable{
    public HashMap data = null;
    public String error = null;
    
    public void setError(String error){
    	this.error = error;
    }
    
    public void setData(HashMap data){
    	this.data = data;
    }
}
