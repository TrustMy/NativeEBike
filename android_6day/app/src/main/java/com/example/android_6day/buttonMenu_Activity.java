package com.example.android_6day;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class buttonMenu_Activity extends Activity implements OnClickListener {
	
	Handler handles=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String value=(String)msg.obj;
			Log.d("lhh", "buttonMenu_Activity success");
			Log.e("����","����");
			Log.e("value",value);
			if(value.contains(ismenu))
			{
				Log.e("aaa","bbb");
				SocketHelper.list=json_server.return_menu_(value);
				Intent intent=new Intent();
				intent.setClass(buttonMenu_Activity.this, Cartes_menu_Activity.class);
				startActivity(intent);
			}
			
			Log.e("handle111",value);
			
		};
	};
	socketServer socketServertt;
	String ismenu="{ \"carte\":";
	connect_json_server json_server=new connect_json_server();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttonmenu_layout);

		
		
		 Button diancai = (Button) findViewById(R.id.buttonmenu_diancai);
		 Button tijiao = (Button) findViewById(R.id.buttonmenu_tijiao);
		 Button cuidan = (Button) findViewById(R.id.buttonmenu_cuidan);
		 Button jiezhang = (Button) findViewById(R.id.buttonmenu_jiezhang);
		
		 diancai.setOnClickListener(this);
		 tijiao.setOnClickListener(this);
		 cuidan.setOnClickListener(this);
		 jiezhang.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
		 case R.id.buttonmenu_diancai:
			 socketServertt=new socketServer(handles);
			 socketServertt.start();
		
//			 socketServertt.cmdwrite(SocketHelper.clientsocket, "m");
			
		 break;
		
		 case R.id.buttonmenu_tijiao:
		
		 break;
		
		 case R.id.buttonmenu_cuidan:
		
		 break;
		
		 case R.id.buttonmenu_jiezhang:
		
		 break;
		
		 default:
		 break;
		 }

	}
}
