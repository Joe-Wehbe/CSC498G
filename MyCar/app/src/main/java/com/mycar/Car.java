package com.mycar;

import android.provider.ContactsContract;

public class Car {

    String brand;
    String model;

    public Car(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
