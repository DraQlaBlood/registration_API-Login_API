package com.mars.registrationservice.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    private String token;

    private Date expiryDate;

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken() {
    }

    public VerificationToken(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }


    public static int getEXPIRATION() {
        return EXPIRATION;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
