package com.example.android_6day;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class socketServer extends Thread {
	String ipString;
	Handler activity;

	public socketServer(Handler ac) {
		// TODO Auto-generated constructor stub
		ipString = SocketHelper.ipString;
		activity = ac;
	}

	connect_json_server json_server = new connect_json_server();

	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Socket socket;
		Message message = new Message();
		message.obj = "start";
		activity.sendMessage(message);
		new receivehandle(SocketHelper.clientsocket, activity).start();

		try {

			if (SocketHelper.clientsocket == null) {
				socket = new Socket(ipString, 10000);
				SocketHelper.clientsocket = socket;
			}
			Message messages = new Message();
			message.obj = "start";
			activity.sendMessage(message);
			new receivehandle(SocketHelper.clientsocket, activity).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void cmdwrite(Socket socket, String value) {
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.write(value.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class receivehandle extends Thread {
		Socket socket;
		Message message;
		Handler activity;

		public receivehandle(Socket socket, Handler ac) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
			activity = ac;
		}

		@Override
		public void run() {
			super.run();
			String content="";
			InputStream is;

			Message m = new Message();
			m.obj = content;
			m.what = 1;
			Log.e("who have",activity.toString());
			activity.sendMessage(m);
			/*
			while(true){
				try {
					is = SocketHelper.clientsocket.getInputStream();
					byte[] buffer = new byte[1024];
					is.read(buffer);
					content = new String(buffer).trim();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(content.length()>0){
//					Log.e("Main", "content:"+content);

				}
			}
			*/
			
			/*
			try {
			int count=0;
			Message message;
			String data="";
			BufferedReader bufferedReader;
			// TODO Auto-generated method stub
				while (true) {
//					char[] buffer=new char[256];
					count=0;
					message=new Message();
					bufferedReader = new BufferedReader(
							new InputStreamReader(SocketHelper.clientsocket.getInputStream()));
					StringBuilder builder=new StringBuilder();
					if((data=bufferedReader.readLine())!=null)
					{
						builder.append(data.trim());
					}
					else {
						bufferedReader.close();
					}
					message.obj=builder.toString();
					Log.e("builder",builder.toString());
					activity.sendMessage(message);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			*/
		}

		String toUtf8(String str) {
			String result = null;
			try {
				result = new String(str.getBytes("UTF-8"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	}
}
