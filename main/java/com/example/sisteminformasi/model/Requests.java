package com.example.sisteminformasi.model;

public class Requests {
    String name;
    String contact;
    String address;
    String operational;
    String equipment;
    String picture;
    String key;

    public Requests() {

    }

    public Requests(String name, String contact, String address, String operational, String equipment, String picture) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.operational = operational;
        this.equipment = equipment;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperational() {
        return operational;
    }

    public void setOperational(String operational) {
        this.operational = operational;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "" + name + "\n" +
                "" + contact + "\n" +
                "" + address + "\n" +
                "" + operational + "\n" +
                "" + equipment + "\n" +
                "" + picture + "\n";
    }
}
