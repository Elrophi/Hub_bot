package com.example.maigoje.myapplication.models;

public class chatModel {

    public String message;
    public boolean isSend;


    public chatModel(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }
}
