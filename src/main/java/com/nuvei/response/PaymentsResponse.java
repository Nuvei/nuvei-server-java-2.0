/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.response;

import com.nuvei.model.ApiExternalToken;

/**
 * <p>
 * Abstract class to be used as a base for payment responses.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 3/10/2017
 */
public abstract class PaymentsResponse extends NuveiResponse {

    /**
     * Тhe Merchant Order ID to be used as input parameter in update method and payment methods.
     * The parameter passed to define which merchant order to update.
     */
    protected String orderId;

    /**
     * Тhe gateway/APM gateway transaction status.
     */
    protected String transactionStatus;

    /**
     * Тhe error code if error occurred at the Checkout Page side.
     */
    protected String paymentMethodErrorCode;

    /**
     * Тhe error reason if error occurred at the Checkout Page side.
     */
    protected String paymentMethodErrorReason;

    /**
     * The error code if error occurred at the gateway/APM gateway side.
     */
    protected Integer gwErrorCode;

    /**
     * The error reason if error occurred at the gateway/APM gateway side.
     */
    protected String gwErrorReason;

    /**
     * The error code if error occurred at the bank side.
     */
    protected Integer gwExtendedErrorCode;

    /**
     * The Id of newly generated userPaymentOption, in case of new userPaymentOption is generated, or userPaymentOptionId
     * that has been used for transaction processing and it had been sent into the request.
     */
    protected String userPaymentOptionId;

    /**
     * Тhe Bank transaction ID.
     */
    protected String externalTransactionId;

    /**
     * Gateway Transaction ID.
     */
    protected String transactionId;

    /**
     * The Auth code.
     */
    protected String authCode;

    /**
     * The ID of the user in merchant system.
     */
    protected String userTokenId;

    /**
     * This block contain external token parameters arriving from a 3rd party payment provider which is not Nuvei (such as CreditGuard ect.)
     */
    protected ApiExternalToken externalToken;

    /**
     * APM reference ID
     */
    protected String additionalTransactionBankId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaymentMethodErrorCode() {
        return paymentMethodErrorCode;
    }

    public void setPaymentMethodErrorCode(String paymentMethodErrorCode) {
        this.paymentMethodErrorCode = paymentMethodErrorCode;
    }

    public String getPaymentMethodErrorReason() {
        return paymentMethodErrorReason;
    }

    public void setPaymentMethodErrorReason(String paymentMethodErrorReason) {
        this.paymentMethodErrorReason = paymentMethodErrorReason;
    }

    public String getGwErrorReason() {
        return gwErrorReason;
    }

    public void setGwErrorReason(String gwErrorReason) {
        this.gwErrorReason = gwErrorReason;
    }

    public String getUserPaymentOptionId() {
        return userPaymentOptionId;
    }

    public void setUserPaymentOptionId(String userPaymentOptionId) {
        this.userPaymentOptionId = userPaymentOptionId;
    }

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getGwErrorCode() {
        return gwErrorCode;
    }

    public void setGwErrorCode(Integer gwErrorCode) {
        this.gwErrorCode = gwErrorCode;
    }

    public Integer getGwExtendedErrorCode() {
        return gwExtendedErrorCode;
    }

    public void setGwExtendedErrorCode(Integer gwExtendedErrorCode) {
        this.gwExtendedErrorCode = gwExtendedErrorCode;
    }

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public ApiExternalToken getExternalToken() {
        return externalToken;
    }

    public void setExternalToken(ApiExternalToken externalToken) {
        this.externalToken = externalToken;
    }

    public String getAdditionalTransactionBankId() {
        return additionalTransactionBankId;
    }

    public void setAdditionalTransactionBankId(String additionalTransactionBankId) {
        this.additionalTransactionBankId = additionalTransactionBankId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("orderId='")
                .append(orderId)
                .append('\'');
        sb.append(", transactionStatus='")
                .append(transactionStatus)
                .append('\'');
        sb.append(", paymentMethodErrorCode='")
                .append(paymentMethodErrorCode)
                .append('\'');
        sb.append(", paymentMethodErrorReason='")
                .append(paymentMethodErrorReason)
                .append('\'');
        sb.append(", gwErrorCode=")
                .append(gwErrorCode);
        sb.append(", gwErrorReason='")
                .append(gwErrorReason)
                .append('\'');
        sb.append(", gwExtendedErrorCode=")
                .append(gwExtendedErrorCode);
        sb.append(", userPaymentOptionId=")
                .append(userPaymentOptionId);
        sb.append(", externalTransactionId='")
                .append(externalTransactionId)
                .append('\'');
        sb.append(", transactionId='")
                .append(transactionId)
                .append('\'');
        sb.append(", authCode='")
                .append(authCode)
                .append('\'');
        sb.append(", userTokenId='")
                .append(userTokenId)
                .append('\'');
        sb.append(", ")
                .append(externalToken);
        sb.append(", additionalTransactionBankId='")
                .append(additionalTransactionBankId)
                .append('\'');
        sb.append(", ")
                .append(super.toString());
        return sb.toString();
    }
}
