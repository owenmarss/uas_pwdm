package com.example.uas_pwdm;

public class CustomItem {
    private int id;
    private String name;

    public CustomItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // Display the name in the Spinner
    }
}
