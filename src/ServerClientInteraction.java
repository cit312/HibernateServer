
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
	private HashMap inString;
	
	private byte[] b;
	
	public void setSocket(Socket s){
		this.fromClientSocket = s;
	}
	
	public void run(){

		try {
			jsonIn = new JSONInputStream(fromClientSocket.getInputStream());
			inString = (HashMap) jsonIn.readObject();
			String command = (String) inString.get("command");
			
			ApplicationController apCntrl = new ApplicationController();
			apCntrl.handleRequest(command, inString);
			
			System.out.println(inString);
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