package com.phonegap.ebike.tool;

import com.phonegap.ebike.Config;

/**
 * Created by Trust on 2017/7/5.
 */

public class TextUtlis {

    public static String getMsg(int id){
       return  Config.context.getResources().getString(id);
    }
}
