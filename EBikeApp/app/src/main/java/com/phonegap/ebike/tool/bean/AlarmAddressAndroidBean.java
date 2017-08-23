package com.phonegap.ebike.tool.bean;

import java.util.List;

/**
 * Created by Trust on 2017/6/26.
 */

public class AlarmAddressAndroidBean {
    private List<AlarmLocationAddressBean> alarmLocationAddressBeen;
    private List<AlarmBean.ContentBean.AlarmsBean> alarmsBeanList;

    public AlarmAddressAndroidBean(List<AlarmLocationAddressBean> alarmLocationAddressBeen, List<AlarmBean.ContentBean.AlarmsBean> alarmsBeanList) {
        this.alarmLocationAddressBeen = alarmLocationAddressBeen;
        this.alarmsBeanList = alarmsBeanList;
    }

    public List<AlarmLocationAddressBean> getAlarmLocationAddressBeen() {
        return alarmLocationAddressBeen;
    }

    public void setAlarmLocationAddressBeen(List<AlarmLocationAddressBean> alarmLocationAddressBeen) {
        this.alarmLocationAddressBeen = alarmLocationAddressBeen;
    }

    public List<AlarmBean.ContentBean.AlarmsBean> getAlarmsBeanList() {
        return alarmsBeanList;
    }

    public void setAlarmsBeanList(List<AlarmBean.ContentBean.AlarmsBean> alarmsBeanList) {
        this.alarmsBeanList = alarmsBeanList;
    }
}
