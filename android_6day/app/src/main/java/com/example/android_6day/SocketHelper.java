package com.example.android_6day;

import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SocketHelper extends Thread {

	public static Socket clientsocket = null;
	public static ArrayList<carte> list = new ArrayList<carte>();
	public static String ipString="";
}

class MyActiVity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg != null) {
				String value = (String) msg.obj;
				Log.e("handle", value);
				setMsg(value);
				
			}

		};
	};



}
