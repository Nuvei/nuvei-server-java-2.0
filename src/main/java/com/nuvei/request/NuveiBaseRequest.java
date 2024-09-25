/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.request;

import javax.validation.constraints.NotNull;

import com.nuvei.util.APIConstants;

/**
 * <p>
 * Abstract class to be used as a base for all of the requests to Nuvei's servers.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/14/2017
 */
public abstract class NuveiBaseRequest {

    /**
     * Merchant's unique request ID
     */
    private String internalRequestId;

    /**
     * ID of the API request in merchant system.
     */
    private String clientRequestId;

    /**
     * The local time when the method call is performed in the format: YYYYMMDDHHmmss.
     */
    @NotNull(message = "timeStamp parameter is mandatory!")
    private String timeStamp;

    /**
     * Hash of the values of the request parameters UTF-8 encoded and concatenated in this order HASH(merchantId + merchantSiteId + clientRequestId + amount + currency + timestamp + merchantSecretKey)
     * Where HASH is the type of used hash algorithm, MD5 or SHA256, depends on merchantSite setting.
     */
    @NotNull(message = "checksum parameter is mandatory!")
    private String checksum;

    /**
     * The session identifier returned, to be used as input parameter in all methods. UUID = Universal unique ID.
     */
    private String sessionToken;

    private String serverHost;

    private final String webMasterId = NuveiBaseRequest.class.getPackage().getImplementationVersion() != null
            ? APIConstants.SDK_JAVA_VERSION.concat(NuveiBaseRequest.class.getPackage().getImplementationVersion()) : null;

    private final String sourceApplication = "JAVA_SDK";

    public String getInternalRequestId() {
        return internalRequestId;
    }

    public void setInternalRequestId(String internalRequestId) {
        this.internalRequestId = internalRequestId;
    }

    public String getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(String clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getWebMasterId() {
        return webMasterId;
    }

    public String getSourceApplication() {
        return sourceApplication;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("serverHost='")
                .append(serverHost)
                .append('\'');
        sb.append("internalRequestId='")
                .append(internalRequestId)
                .append('\'');
        sb.append(", clientRequestId='")
                .append(clientRequestId)
                .append('\'');
        sb.append(", timeStamp='")
                .append(timeStamp)
                .append('\'');
        sb.append(", checksum='")
                .append(checksum)
                .append('\'');
        sb.append(", sessionToken='")
                .append(sessionToken)
                .append('\'');
        return sb.toString();
    }
}