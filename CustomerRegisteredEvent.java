package com.auto.insurance;

import java.io.Serializable;

/**
 * Created by uengine on 2018. 10. 25..
 */
public class CustomerRegisteredEvent{


    private String customerName;
    private String couponNumber;
    private String emailAddress;
    private String ssn;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }
}
