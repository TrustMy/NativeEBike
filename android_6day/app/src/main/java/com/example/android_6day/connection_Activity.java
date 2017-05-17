package com.example.android_6day;

import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;






public class connection_Activity extends Activity implements OnClickListener {

	
	socketServer socketServer;
	String ismenu="{ \"carte\":";
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {

			Log.d("lhh", "connection_Activity success");
			Intent intent=new Intent();
			intent.setClass(connection_Activity.this, buttonMenu_Activity.class);
			startActivity(intent);

			/*
			if(msg!=null)
			{
				String value=(String)msg.obj;
				Log.e("handler....",value);
				if(value.equals("The self-made successful!"))
				{
					Log.e("the self","jinru");

				}
				if(value.equals("start"))
				{
					socketServer.cmdwrite(SocketHelper.clientsocket, json_server.return_ruzuo(1, 1, "usbabc"));
				}
				
			}
			*/
		};
	};
	connect_json_server json_server = new connect_json_server();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect_layout);
		Button button = (Button) findViewById(R.id.connect_btn_start);

		button.setOnClickListener(this);
		
				
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText text = (EditText) findViewById(R.id.connect_editview_ip);
		SocketHelper.ipString=text.getText().toString();
		socketServer =new socketServer(handler);
		socketServer.start();
		//
	}

}