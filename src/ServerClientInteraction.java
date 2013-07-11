
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
	private Socket client;
	private Socket returnToClient;
	
	private byte[] b;
	
	public void setSocket(Socket s){
		this.fromClientSocket = s;
	}
	
	@Override
	public void run(){

		try {
			//*** 1. Read In Info***
			jsonIn = new JSONInputStream(fromClientSocket.getInputStream());
			inString = (HashMap) jsonIn.readObject();
			
			//Syntax to get values from hashmap. may have changed
			String command = (String) inString.get("command");
			
			//*** 2. Handle the request based on command***
			ApplicationController apCntrl = new ApplicationController();
			CommBean returnedData = apCntrl.handleRequest(command, inString);
			
			//Debugging. See incoming hashmap
			System.out.println("INCOMING:" + inString);
			System.out.println("OUTGOING:" + returnedData.toString());
			
			//*** 3. Return Data ***
			
			//jsonOut = new JSONOutputStream(fromClientSocket.getInputStream());
			//JSONOutputStream.writeObject(data);
			
			//PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			//out.println("Returned Stuff");
			//returnToClient = new Socket(fromClientSocket.getRemoteSocketAddress().toString(), 9995);
			//jsonOut = new JSONOutputStream(returnToClient.getOutputStream());
			
			//Close the socket stuff or w/e it is
			
			//jsonOut.writeObject(data);
			//returnToClient.close();
			
			//Send data
			jsonOut = new JSONOutputStream(fromClientSocket.getOutputStream());
			jsonOut.writeObject(returnedData);
			
			fromClientSocket.close(); //Clean-up
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return;
	}
}