package com.hujing.youmedai;

import cn.bmob.v3.BmobObject;

public class UserInfo extends BmobObject{
    private String username;
    private String phoneNo;
    private String idcardNo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }
}
