package com.phonegap.ebike.tool.bean;

import java.util.List;

/**
 * Created by Trust on 2017/5/22.
 */

public class CarStrokeAndAddress {
    private static List<CarStrokeBean.ContentBean.TripsBean>  tripsBeenList;
    private static List<LocationAddressBean> addressList;


    public CarStrokeAndAddress(List<CarStrokeBean.ContentBean.TripsBean> tripsBeenList, List<LocationAddressBean> addressList) {
        this.tripsBeenList = tripsBeenList;
        this.addressList = addressList;
    }


    public List<CarStrokeBean.ContentBean.TripsBean> getTripsBeenList() {
        return tripsBeenList;
    }

    public void setTripsBeenList(List<CarStrokeBean.ContentBean.TripsBean> tripsBeenList) {
        this.tripsBeenList = tripsBeenList;
    }

    public List<LocationAddressBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<LocationAddressBean> addressList) {
        this.addressList = addressList;
    }
}
