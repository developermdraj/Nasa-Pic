package com.nasa.pictures.Model;

public class User_Model {

    String Name;
    String Image;
    String descriptions;
    String Date;
    String Copyright;


    public User_Model() {
    }

    public User_Model(String name, String image, String descriptions, String data, String copyright) {
        Name = name;
        Image = image;
        this.descriptions = descriptions;
        Date = data;
        Copyright = copyright;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String data) {
        Date = data;
    }

    public String getCopyright() {
        return Copyright;
    }

    public void setCopyright(String copyright) {
        Copyright = copyright;
    }
}
