package org.example.backend.entity;


public class Owner {
    private String fullName;
    private String phone;

    public Owner() {
    }

    public Owner(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

}
