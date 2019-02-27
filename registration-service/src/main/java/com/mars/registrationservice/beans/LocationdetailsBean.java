package com.mars.registrationservice.beans;

public class LocationdetailsBean {

    private String ipAddress;
    private String city;
    private String country;
    private String latitude;
    private String longitude;

    public LocationdetailsBean() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    @Override
    public String toString() {
        return "LocationdetailsBean{" +
                "ipAddress='" + ipAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
