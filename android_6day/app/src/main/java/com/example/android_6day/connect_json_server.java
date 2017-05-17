package com.example.android_6day;

import java.util.ArrayList;
import org.json.JSONArray;

import org.json.JSONObject;
import android.R.integer;


public class connect_json_server {

	JSONObject json;
	JSONObject js1;
	JSONObject js2;

	/*
	 * 发送点桌号给服务器
	 * 
	 */
	String return_ruzuo(int uid, int deskid, String uname) {
		json = new JSONObject();
		js1 = new JSONObject();
		try {

			js1.put("uid", uid);
			js1.put("deskid", deskid);
			js1.put("uname", uname);
		
		} catch (Exception e) {
			return "";
		}
	
	
		return "t"+js1.toString();
	}

	/*
	 * 
	 * 提交订单
	 */
	String return_sub(integer deskid, ArrayList<carte> list) {
		json = new JSONObject();
		js1 = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			for (carte carte : list) {
				js2 = new JSONObject();

				js2.put("id", carte.getId());
				js2.put("number", carte.getNumber());

				array.put(js2);
			}
			js1.put("carte", array);
			js1.put("total", list.size());
			js1.put("deskid", deskid);
			js1.put("orderid", 0);
			json.put("o", js1);
			return json.toString();
		}

		catch (Exception e) {
			// TODO: handle exception
		}
		return "o{}";
	}

	int return_result_string(String resultvalue) {
		try {
			json = new JSONObject(resultvalue);
			return Integer.parseInt((String) json.get("result"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	/*
	 * 从服务器接收菜单信息到本地
	 */
	ArrayList<carte> return_menu_(String serverstring) {
		ArrayList<carte> list = new ArrayList<carte>();
		try {
			json = new JSONObject(serverstring);
			String ct = json.getString("carte");
			JSONArray array = new JSONArray(ct);
			for (int i = 0; i < array.length(); i++) {
				js1 = (JSONObject) array.get(i);
				list.add(new carte(js1.getString("dishid"), js1.getString("dishname"), js1.getInt("price"),
						js1.getString("storageManager"), js1.getInt("type"), js1.getString("recomend"),
						Integer.parseInt(js1.getString("dishid")), 0));
			}

		} catch (Exception e) {
			{

			} // TODO: handle exception
		}

		return list;
	}

}
