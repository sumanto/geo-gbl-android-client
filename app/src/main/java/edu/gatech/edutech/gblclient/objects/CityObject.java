package edu.gatech.edutech.gblclient.objects;

public class CityObject {
    public CityObject(String cityName, String flightNumber) {
        this.cityName = cityName;
        this.flightNumber = flightNumber;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    private String cityName;
    private String flightNumber;
}
