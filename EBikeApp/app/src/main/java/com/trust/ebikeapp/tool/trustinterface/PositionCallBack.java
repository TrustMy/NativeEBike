package com.trust.ebikeapp.tool.trustinterface;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

/**
 * Created by Trust on 2017/5/16.
 */

public interface PositionCallBack {
    void positionCallBack(LatLonPoint data);
}
