package com.achila.sendemailalert.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertList {

    private List<AlertMessage> alertMessageList;

    public AlertList(){

    }
    public AlertList(@JsonProperty("alert_list")List<AlertMessage> alertMessageList) {
        this.alertMessageList = alertMessageList;
    }

    public List<AlertMessage> getAlertMessageList() {
        return alertMessageList;
    }

    public void setAlertMessageList(List<AlertMessage> alertMessageList) {
        this.alertMessageList = alertMessageList;
    }
}
