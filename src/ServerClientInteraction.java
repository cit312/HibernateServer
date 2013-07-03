
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

public class ServerClientInteraction implements Runnable{
	private Socket fromClientSocket;
	
	private InputStream inStream;
	private JSONInputStream jsonIn;
	private JSONOutputStream jsonOut;
	private String inString;
	
	private byte[] b;
	
	public void setSocket(Socket s){
		this.fromClientSocket = s;
	}
	
	public void run(){

		try {
			jsonIn = new JSONInputStream(fromClientSocket.getInputStream());
			HashMap data = (HashMap) jsonIn.readObject();
			//inString = (String)jsonIn.readObject();
			
			
			System.out.println(data.get("data"));
			fromClientSocket.close(); //Clean-up
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return;
	}
}
