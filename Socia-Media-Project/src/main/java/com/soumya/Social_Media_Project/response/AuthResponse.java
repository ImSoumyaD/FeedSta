package com.soumya.Social_Media_Project.response;

public class AuthResponse {
    private String token;
    private String massage;

    public AuthResponse() {
    }

    public AuthResponse(String token, String massage) {
        this.token = token;
        this.massage = massage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
