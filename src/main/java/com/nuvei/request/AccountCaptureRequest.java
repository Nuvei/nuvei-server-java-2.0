

/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuvei.model.DeviceDetails;
import com.nuvei.model.UrlDetails;
import com.nuvei.model.UserDetails;
import com.nuvei.request.builder.NuveiBuilder;
import com.nuvei.util.Constants;
import com.nuvei.util.ValidChecksum;
import com.nuvei.util.ValidationUtils;

@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.API_GENERIC_CHECKSUM_MAPPING)
public class AccountCaptureRequest extends NuveiRequest {

    @NotNull
    @Size(max = 255)
    private String userTokenId;

    @NotNull
    @Size(max = 50)
    private String paymentMethod;

    @NotNull
    @Size(max = 3)
    private String currencyCode;

    @Size(max = 2)
    private String country;

    @NotNull
    @Size(max = 2)
    private String languageCode;

    @Size(max = 12)
    private String amount;

    @Size(max = 1000)
    private String notificationUrl;

    private DeviceDetails deviceDetails;

    private UserDetails userDetails;

    private UrlDetails urlDetails;

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     *  This method is no longer acceptable to get the country code.
     *  <p> Use {@link #getCountry()} instead.
     */
    @Deprecated
    public String getCountryCode() {
        return getCountry();
    }

    /**
     *  This method is no longer acceptable to set the country code.
     *  <p> Use {@link #setCountry(String)} instead.
     */
    @Deprecated
    public void setCountryCode(String countryCode) {
       setCountry(countryCode);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public DeviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public UrlDetails getUrlDetails() {
        return urlDetails;
    }

    public void setUrlDetails(UrlDetails urlDetails) {
        this.urlDetails = urlDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends NuveiBuilder<Builder> {

        private String userTokenId;
        private String paymentMethod;
        private String currencyCode;
        private String country;
        private String languageCode;
        private String amount;
        private String notificationUrl;
        private DeviceDetails deviceDetails;
        private UserDetails userDetails;
        private UrlDetails urlDetails;

        public Builder addUserTokenId(String userTokenId) {
            this.userTokenId = userTokenId;
            return this;
        }

        public Builder addPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder addCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

        /**
         *  This method is no longer acceptable to set the country code.
         *  <p> Use {@link #addCountry(String)} instead.
         */
        @Deprecated
        public Builder addCountryCode(String countryCode) {
            this.country = countryCode;
            return this;
        }

        public Builder addCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder addLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public Builder addAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder addDeviceDetails(DeviceDetails deviceDetails) {
            this.deviceDetails = deviceDetails;
            return this;
        }

        public Builder addUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
            return this;
        }

        public Builder addNotificationUrl(String notificationUrl) {
            this.notificationUrl = notificationUrl;
            return this;
        }

        public Builder addUrlDetails(UrlDetails urlDetails) {
            this.urlDetails = urlDetails;
            return this;
        }

        @Override
        public AccountCaptureRequest build() throws ConstraintViolationException {
            AccountCaptureRequest request = new AccountCaptureRequest();
            request.setUserTokenId(userTokenId);
            request.setPaymentMethod(paymentMethod);
            request.setCurrencyCode(currencyCode);
            request.setCountry(country);
            request.setLanguageCode(languageCode);
            request.setAmount(amount);
            request.setNotificationUrl(notificationUrl);
            request.setDeviceDetails(deviceDetails);
            request.setUserDetails(userDetails);
            request.setUrlDetails(urlDetails);

            return ValidationUtils.validate(super.build(request));
        }
    }
}
