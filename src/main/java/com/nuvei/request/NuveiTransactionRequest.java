/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuvei.model.CompanyDetails;
import com.nuvei.model.ShippingTrackingDetails;
import com.nuvei.model.SubMerchant;
import com.nuvei.model.UrlDetails;

/**
 * <p>
 * Abstract class to be used as a base for transaction related requests.
 *</p>
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 3/22/2017
 */
public abstract class NuveiTransactionRequest extends NuveiRequest {

    /**
     * The transaction amount. (E.g. 1, 101.10 - decimal representation of the amount as {@link String})
     */
    @NotNull(message = "amount parameter is mandatory!")
    protected String amount;

    /**
     * The three character ISO currency code of the transaction.
     */
    @NotNull(message = "currency parameter is mandatory!")
    protected String currency;

    /**
     * The authorization code of the related auth transaction, to be compared to the original one.
     */
    protected String authCode;

    /**
     * Enables the addition of a free text comment to the request.
     */
    @Size(max = 255, message = "comment size must be up to 255 characters long!")
    protected String comment;

    /**
     * ID of the transaction in the merchant’s system. This must be sent in order to perform future actions, such as:
     * reconciliation, identifying the transaction in the event of any issues, etc.
     */
    @Size(max = 45, message = "clientUniqueId size must be up to 45 characters long!")
    protected String clientUniqueId;

    /**
     * URLs to redirect to in case of success, failure, etc. Also URL to send the direct merchant notification(DMN) message to.
     */
    @Valid
    protected UrlDetails urlDetails;

    @Size(max = 50)
    private String customSiteName;

    @Size(max = 50)
    private String productId;

    @Size(max = 255)
    private String customData;

    @Valid
    private SubMerchant subMerchant;

    @Valid
    private CompanyDetails companyDetails;

    private ShippingTrackingDetails shippingTrackingDetails;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClientUniqueId() {
        return clientUniqueId;
    }

    public void setClientUniqueId(String clientUniqueId) {
        this.clientUniqueId = clientUniqueId;
    }

    public abstract String getRelatedTransactionId();

    public abstract void setRelatedTransactionId(String relatedTransactionId);

    public UrlDetails getUrlDetails() {
        return urlDetails;
    }

    public void setUrlDetails(UrlDetails urlDetails) {
        this.urlDetails = urlDetails;
    }

    public String getCustomSiteName() {
        return customSiteName;
    }

    public void setCustomSiteName(String customSiteName) {
        this.customSiteName = customSiteName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public SubMerchant getSubMerchant() {
        return subMerchant;
    }

    public void setSubMerchant(SubMerchant subMerchant) {
        this.subMerchant = subMerchant;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public ShippingTrackingDetails getShippingTrackingDetails() {
        return shippingTrackingDetails;
    }

    public void setShippingTrackingDetails(ShippingTrackingDetails shippingTrackingDetails) {
        this.shippingTrackingDetails = shippingTrackingDetails;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NuveiTransactionRequest{");
        sb.append("amount='").append(amount).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", authCode='").append(authCode).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", clientUniqueId='").append(clientUniqueId).append('\'');
        sb.append(", urlDetails=").append(urlDetails);
        sb.append(", customSiteName='").append(customSiteName).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", customData='").append(customData).append('\'');
        sb.append(", subMerchant='").append(subMerchant).append('\'');
        sb.append(", companyDetails='").append(companyDetails).append('\'');
        sb.append(", shippingTrackingDetails='").append(shippingTrackingDetails).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
