package com.soumya.Social_Media_Project.response;

public class ApiResponse {
    private String massage;
    private boolean status;

    public ApiResponse() {
    }

    public ApiResponse(String massage, boolean status) {
        this.massage = massage;
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
