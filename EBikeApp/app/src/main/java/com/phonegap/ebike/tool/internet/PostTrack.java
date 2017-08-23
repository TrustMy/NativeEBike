package com.phonegap.ebike.tool.internet;

import android.os.Message;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.TrustException;
import com.phonegap.ebike.tool.internet.ssl.TrustAllCerts;
import com.phonegap.ebike.tool.trustinterface.ResultCallBack;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Trust on 2017/5/11.
 */
public class PostTrack {
    private OkHttpClient okHttpClient ;
    private Request.Builder builder;
    private PostResult postResult;
    public PostTrack(ResultCallBack callBack) {
        this.okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(TrustAllCerts.createSSLSocketFactory(),new TrustAllCerts())
                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        postResult = new PostResult(callBack);
    }


    public void Request(String url ,Map<String,Object> map,int type,boolean needHeader)
    {
        JSONObject jsonObject = new JSONObject(map);
        L.d("jsonObject:"+jsonObject);
        needHeader(Config.Server+url,type,jsonObject.toString(),needHeader);
    }


    private void needHeader(String url, int type, String json ,boolean needHeader) {
        MediaType JSON = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, json);

        builder = new Request.Builder();
        Request request;

        if(needHeader){
            request    = builder.url(url).addHeader("Token", Config.token).post(body)
                    .build();
        }else{
            request   = builder.url(url).post(body)
                    .build();
        }

        executeResponse(request, type);
    }

    public void executeResponse(final Request request , final int type) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:"+e.toString());
                Map<String,Object> map = new WeakHashMap<>();
                map.put("status",false);
                map.put("err", TrustException.getException(e));
                JSONObject json = new JSONObject(map);
                sendMessage(json.toString(),Config.ERROR,type);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();

                L.i("json:"+json);

                if(response.code() == 200)
                {
                    if(type == Config.login){
                        Config.token = response.header("Token");
                        L.d("token:"+Config.token);
                    }
                    sendMessage(json,Config.SUCCESS,type);
                }else
                {
                    L.e("error code :"+response.code());
                    Map<String,Object> map = new WeakHashMap<>();
                    map.put("status",false);
                    map.put("err",response.code());
                    JSONObject jsons = new JSONObject(map);
                    sendMessage(jsons.toString(),Config.ERROR,type);
                }


            }
        });
    }



    public void sendMessage(String result,int status,int type){
        Message message = Message.obtain();
        message.what = type;
        message.arg1 = status;
        message.obj = result;
        postResult.sendMessage(message);
    }
}
