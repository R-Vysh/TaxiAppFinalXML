package ua.ros.taxiapp.web.controller;

public class StatusMessage {
    public static final String OK = "Successfull";
    public static final String FAIL = "An error occured";


    private String message;

    public StatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
