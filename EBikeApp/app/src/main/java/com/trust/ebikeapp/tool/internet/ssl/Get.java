package com.trust.ebikeapp.tool.internet.ssl;

import android.content.Context;
import android.os.Message;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Trust on 2017/7/3.
 */

public class Get {
    private OkHttpClient okHttpClient ;
    private Request.Builder builder;
    private GetResult getResult;


    public Get(ResultCallBack callBack) {
        this.okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(TrustAllCerts.createSSLSocketFactory(),new TrustAllCerts())
                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        builder = new Request.Builder();
        getResult = new GetResult(callBack);
    }



    public void Request(String url , int type) {
        Request request    = builder.get().url(url).build();
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
                map.put("err","与服务器连接超时!");
                JSONObject json = new JSONObject(map);
                sendMessage(json.toString(),Config.ERROR,type);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Object json;
                if(type == Config.updateApp){
                    json = response.body().byteStream();
                }else{
                    json = response.body().string();
                }


                L.i("json:"+json);

                if(response.code() == 200)
                {
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



    public void sendMessage(Object result,int status,int type){
        Message message = Message.obtain();
        message.what = type;
        message.arg1 = status;
        message.obj = result;
        getResult.sendMessage(message);
    }

}
