/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.request;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.nuvei.model.CurrencyConversion;
import com.nuvei.model.ExternalSchemeDetails;
import com.nuvei.model.OpenAmount;
import com.nuvei.model.UserPaymentOption;

/**
 * <p>
 * Abstract class with basic fields used with requests to create an order in the Nuvei's system.
 * </p>
 * <p>
 * This request represents the state of the order when it is created, it can be changed at later time.
 * Note that no payment request is send, it is used mainly to store the order details at the time of creation.
 * </p>
 *
 * @author <a mailto:vasiln@nuvei.com>Vassil Nikov</a>
 * @see GetOrderDetailsRequest
 * @see UpdateOrderRequest
 * @see OpenOrderRequest
 */
public abstract class OrderRequestWithDetails extends NuveiOrderDetailsRequest {

    @Size(max = 50)
    private String paymentMethod;

    @Valid
    private UserPaymentOption userPaymentOption;

    @Valid
    private ExternalSchemeDetails externalSchemeDetails;

    @Valid
    private CurrencyConversion currencyConversion;

    private OpenAmount openAmount;

    @Size(max = 1)
    private String aftOverride;

    @Size(max = 255)
    private String customData;

    private Boolean autoPayment3D;

    private String isMoto;

    @Size(max = 255)
    private String userId;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public UserPaymentOption getUserPaymentOption() {
        return userPaymentOption;
    }

    public void setUserPaymentOption(UserPaymentOption userPaymentOption) {
        this.userPaymentOption = userPaymentOption;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public boolean isAutoPayment3D() {
        return autoPayment3D;
    }

    public void setAutoPayment3D(Boolean autoPayment3D) {
        this.autoPayment3D = autoPayment3D;
    }

    public String getIsMoto() {
        return isMoto;
    }

    public void setIsMoto(String isMoto) {
        this.isMoto = isMoto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ExternalSchemeDetails getExternalSchemeDetails() {
        return externalSchemeDetails;
    }

    public void setExternalSchemeDetails(ExternalSchemeDetails externalSchemeDetails) {
        this.externalSchemeDetails = externalSchemeDetails;
    }

    public CurrencyConversion getCurrencyConversion() {
        return currencyConversion;
    }

    public void setCurrencyConversion(CurrencyConversion currencyConversion) {
        this.currencyConversion = currencyConversion;
    }

    public OpenAmount getOpenAmount() {
        return openAmount;
    }

    public void setOpenAmount(OpenAmount openAmount) {
        this.openAmount = openAmount;
    }

    public String getAftOverride() {
        return aftOverride;
    }

    public void setAftOverride(String aftOverride) {
        this.aftOverride = aftOverride;
    }

    public Boolean getAutoPayment3D() {
        return autoPayment3D;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderRequestWithDetails{");
        sb.append("paymentMethod='").append(paymentMethod)
                .append(", userPaymentOption=").append(userPaymentOption)
                .append(", customData='").append(customData)
                .append(", autoPayment3D=").append(autoPayment3D)
                .append(", isMoto='").append(isMoto)
                .append(", userId='").append(userId)
                .append(", externalSchemeDetails='").append(externalSchemeDetails)
                .append(", currencyConversion='").append(currencyConversion)
                .append(", openAmount='").append(openAmount)
                .append(", aftOverride='").append(aftOverride);
        sb.append(super.toString());

        return sb.toString();
    }
}
