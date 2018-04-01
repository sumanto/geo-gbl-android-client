package edu.gatech.edutech.gblclient.objects;

public class ThiefAttributes {
    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void resetAttributes() {
        this.eyes = null;
        this.hair = null;
        this.hobby = null;
        this.food = null;
        this.feature = null;
        this.vehicle = null;
        this.sex = null;
    }

    public String toString() {
        return "sex: " + sex +
                ", eye: " + eyes +
                ", hair: " + hair +
                ", hobby: " + hobby +
                ", food: " + food +
                ", feature: " + feature +
                ", vehicle: " + vehicle;
    }


    private String eyes;
    private String hair;
    private String hobby;
    private String food;
    private String feature;
    private String vehicle;
    private String sex;
}
