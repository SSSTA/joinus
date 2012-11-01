package com.sssta.joinus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sssta.model.ConstValue;


public class SocketClient extends Thread implements Serializable{
	
	static private String host="192.168.1.135";
	static private int port=3001;
	static private Socket socket;
	private InputStream inputStream=null;
	private OutputStream outputStream=null;
	private byte[] outbuffer;
	private byte[] inbuffer=new byte[1024];
	private String inbufferString="";
	private String cmdString;
	private String returnString;
	
	static public String Execute(String cmd)
	{
		SocketClient client=new SocketClient();
		client.cmdString=cmd;
		client.start();
		while(client.isAlive());
		return client.returnString;
	}
	
	public void run()
	{
		if(socket==null)
		{
			try {
				socket=new Socket(host, port);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try{
			cmdString+=";";
			inputStream=socket.getInputStream();
			outputStream=socket.getOutputStream();
			outbuffer=cmdString.getBytes("utf-8");
			outputStream.write(outbuffer);
			if (ConstValue.IsQuery(cmdString)) {
				while (true) {
					inputStream.read(inbuffer);
					String tempString = new String(inbuffer, "utf-8");
					inbufferString += tempString;
					if (tempString.contains(ConstValue.EndString)) {
						int index = inbufferString.lastIndexOf(ConstValue.EndString);
						inbufferString=inbufferString.substring(0, index);
						break;
					}
				}
			}
			else inbufferString=null;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			this.returnString=inbufferString;
		}
	}
		
}
