package com.trust.ebikeapp.tool;

import com.trust.ebikeapp.Config;

/**
 * Created by Trust on 2017/7/5.
 */

public class TextUtlis {

    public static String getMsg(int id){
       return  Config.context.getResources().getString(id);
    }
}
