/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.response;

import com.nuvei.util.Constants;

/**
 * <p>
 * Abstract class to be used as a base for all of the responses from Nuvei's servers.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/14/2017
 */
public abstract class NuveiResponse {

    private static final long serialVersionUID = 4104056768008786142L;

    /**
     * Nuvei Internal unique request id (used for reconciliation purpose etc.).
     */
    private Long internalRequestId;

    /**
     * The Checkout Page status of merchant request.
     */
    private Constants.APIResponseStatus status;

    /**
     * The error code of the error occurred at the Checkout Page site.
     */
    private Integer errCode = Constants.ERR_CODE_NO_ERROR;

    /**
     * The error reason if error occurred at the Checkout Page side.
     */
    private String reason = "";

    /**
     * The Merchant ID provided by Nuvei.
     */
    private String merchantId;

    /**
     * The Merchant Site ID provided by Nuvei.
     */
    private String merchantSiteId;

    /**
     * The current version of the API method
     */
    private String version;

    /**
     * ID of the API request in merchant system.
     */
    private String clientRequestId;

    /**
     * The session identifier returned, to be used as input parameter in all methods. UUID = Universal unique ID.
     */
    private String sessionToken;

    /**
     * ID of the transaction in merchant system.
     */
    private String clientUniqueId;

    /**
     * The type of the server's error
     */
    private Constants.ErrorType errorType = null;

    /**
     * The API workflow type
     */
    private Constants.APIType apiType;

    /**
     * If configured to use hints will returned URL pointing to more detailed error description when request fails
     */
    private String hint;

    /**
     * Full json representation of the response
     */
    private String json;

    public NuveiResponse() {
    }

    public Long getInternalRequestId() {
        return internalRequestId;
    }

    public void setInternalRequestId(Long internalRequestId) {
        this.internalRequestId = internalRequestId;
    }

    public Constants.APIResponseStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.APIResponseStatus status) {
        this.status = status;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantSiteId() {
        return merchantSiteId;
    }

    public void setMerchantSiteId(String merchantSiteId) {
        this.merchantSiteId = merchantSiteId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(String clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getClientUniqueId() {
        return clientUniqueId;
    }

    public void setClientUniqueId(String clientUniqueId) {
        this.clientUniqueId = clientUniqueId;
    }

    public Constants.ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(Constants.ErrorType errorType) {
        this.errorType = errorType;
    }

    public Constants.APIType getApiType() {
        return apiType;
    }

    public void setApiType(Constants.APIType apiType) {
        this.apiType = apiType;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("internalRequestId=")
                .append(internalRequestId);
        sb.append(", status=")
                .append(status);
        sb.append(", errCode=")
                .append(errCode);
        sb.append(", reason='")
                .append(reason)
                .append('\'');
        sb.append(", merchantId='")
                .append(merchantId)
                .append('\'');
        sb.append(", merchantSiteId='")
                .append(merchantSiteId)
                .append('\'');
        sb.append(", version='")
                .append(version)
                .append('\'');
        sb.append(", clientRequestId='")
                .append(clientRequestId)
                .append('\'');
        sb.append(", sessionToken='")
                .append(sessionToken)
                .append('\'');
        sb.append(", clientUniqueId='")
                .append(clientUniqueId)
                .append('\'');
        sb.append(", errorType=")
                .append(errorType);
        sb.append(", apiType=")
                .append(apiType);
        sb.append(", hint=").append(hint);
        return sb.toString();
    }
}
