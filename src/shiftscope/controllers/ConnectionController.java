package shiftscope.controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONArray;
import org.json.JSONException;
import shiftscope.views.Main;
import android.os.Bundle;
import android.os.Message;

public class ConnectionController {
	
	private static String REMOTE_HOST = "192.168.1.67";
	private static final int PORT = 8000;
	private static Socket socket;
	private static DataInputStream inputStream;
	private static PrintWriter printwriter;
	
	public static void automaticConnection() {
		Thread multicastReceiver = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					while (true) {
						InetAddress group = InetAddress.getByName("225.4.5.6");
						MulticastSocket multicastSocket = new MulticastSocket(3456);
						multicastSocket.joinGroup(group);
						byte[] buffer = new byte[1000];
						DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
						multicastSocket.receive(packet);
						String receivedMessage = new String(packet.getData()).trim();
						if (receivedMessage.equals("REQUEST_CONNECTION")) {
							REMOTE_HOST = packet.getAddress().getHostAddress();
							Message messageObject = new Message();
							Bundle b = new Bundle();
					        b.putString("message",REMOTE_HOST);
					        messageObject.setData(b);
					        Main.handler.sendMessage(messageObject);
					        multicastSocket.close();
					        startConnection();
					        break;
						}
					}
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
		multicastReceiver.start();
		
	}

	public static void request(String message){
			try {
				if (socket != null) {
					printwriter = new PrintWriter(socket.getOutputStream(),true);
					printwriter.println(message);
					waitForResponse();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void requestTrackList(){
		try {
			if (socket != null){
				printwriter = new PrintWriter(socket.getOutputStream(),true);
				printwriter.println("TRACKLIST");
				SongController.parseJSON(waitForJSONResponse());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
	public static void startConnection(){
		try {
			socket = new Socket(REMOTE_HOST, PORT);
			socket.setSoTimeout(3000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void closeConnection() {
		try {
			if(socket != null){
				socket.close();
				socket = null;
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void waitForResponse(){
		String message;
		Message messageObject;
		Bundle b;
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			message = inputStream.readUTF();
			messageObject = new Message();
			b = new Bundle();
			if (message.contains("CURRENT_SONG")){
				b.putString("currentsong", message.substring(message.lastIndexOf(":")+1, message.length()));
		        messageObject.setData(b);
			} else {
				b.putString("message", message);
		        messageObject.setData(b);
			}

	        Main.handler.sendMessage(messageObject);
		} catch (SocketTimeoutException e) {
			b = new Bundle();
			messageObject = new Message();
			b.putString("message", "Servidor no responde");
	        messageObject.setData(b);
	        Main.handler.sendMessage(messageObject);
	        closeConnection();
	        automaticConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static JSONArray waitForJSONResponse(){
		String message;
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			message = inputStream.readUTF();
			try {
				return new JSONArray(message);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}
}
