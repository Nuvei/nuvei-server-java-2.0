/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.model;

import javax.validation.constraints.Size;

import com.nuvei.util.APIConstants;

/**
 * <p>
 * Holder for an User's billing or shipping address.
 *</p>
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/14/2017
 */
public class UserAddress {

    /**
     * The first name of the recipient
     */
    @Size(max = APIConstants.FIRST_NAME_MAX_LENGTH, message = "firstName size must be up to 30 characters long!")
    private String firstName;

    /**
     * The last name of the recipient
     */
    @Size(max = APIConstants.LAST_NAME_MAX_LENGTH, message = "lastName size must be up to 40 characters long!")
    private String lastName;

    /**
     * The email of the recipient
     */
    @Size(max = APIConstants.EMAIL_MAX_LENGTH, message = "email size must be up to 100 characters long!")
    private String email;

    /**
     * The phone number of the recipient
     */
    @Size(max = APIConstants.PHONE_MAX_LENGTH, message = "phone size must be up to 18 characters long!")
    private String phone;

    /**
     * The address of the recipient
     */
    @Size(max = APIConstants.ADDRESS_MAX_LENGTH, message = "address size must be up to 120 characters long!")
    private String address;

    /**
     * The city of the recipient
     */
    @Size(max = APIConstants.CITY_MAX_LENGTH, message = "city size must be up to 30 characters long!")
    private String city;

    /**
     * The country of the recipient(two-letter ISO country code)
     */
    @Size(max = 2, message = "country size must be up to 2 characters long!")
    private String country;

    /**
     * The state of the recipient(two-letter ISO state code)
     */
    @Size(max = 5, message = "state size must be up to 5 characters long!")
    private String state;

    /**
     * The postal code of the recipient
     */
    @Size(max = APIConstants.ZIP_MAX_LENGTH, message = "zip size must be up to 10 characters long!")
    private String zip;

    /**
     * The cell number of the recipient
     */
    @Size(max = APIConstants.PHONE_MAX_LENGTH, message = "cell size must be up to 18 characters long!")
    private String cell;

    /**
     * The county of the recipient
     */
    @Size(max = APIConstants.COUNTY_MAX_LENGTH, message = "county size must be up to 255 characters long!")
    private String county;

    @Size(max = APIConstants.ADDRESS_LINE_MAX_LENGTH, message = "shipAddressLine2 size must be up to 60 characters long!")
    private String shipAddressLine2;

    @Size(max = APIConstants.ADDRESS_LINE_MAX_LENGTH, message = "shipAddressLine3 size must be up to 60 characters long!")
    private String shipAddressLine3;

    @Size(max = APIConstants.ADDRESS_LINE_MAX_LENGTH, message = "addressLine2 size must be up to 60 characters long!")
    private String addressLine2;

    @Size(max = APIConstants.ADDRESS_LINE_MAX_LENGTH, message = "addressLine3 size must be up to 60 characters long!")
    private String addressLine3;

    private String careOf;

    private String salutation;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getShipAddressLine2() {
        return shipAddressLine2;
    }

    public void setShipAddressLine2(String shipAddressLine2) {
        this.shipAddressLine2 = shipAddressLine2;
    }

    public String getShipAddressLine3() {
        return shipAddressLine3;
    }

    public void setShipAddressLine3(String shipAddressLine3) {
        this.shipAddressLine3 = shipAddressLine3;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
