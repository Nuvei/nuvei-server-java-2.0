/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request.builder;

import javax.validation.ConstraintViolationException;

import com.nuvei.model.MerchantInfo;
import com.nuvei.request.NuveiBaseRequest;
import com.nuvei.request.NuveiRequest;
import com.nuvei.util.ChecksumUtils;
import com.nuvei.util.Constants;
import com.nuvei.util.RequestUtils;

/**
 * <p>
 * A base builder class for a request 2007 - 2024 Nuvei Corporation.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @see NuveiCCBuilder
 * @see NuveiOrderBuilder
 * @see NuveiTransactionBuilder
 * @since 2/23/2017
 */
public abstract class NuveiBuilder<T extends NuveiBuilder<T>> {

    protected MerchantInfo merchantInfo;
    private String clientRequestId;
    private String internalRequestId;
    private String sessionToken;

    /**
     * Adds previously obtained {@code sessionToken} to the request. All requests(except the one to obtain session token)
     * 2007 - 2024 Nuvei Corporation's API require a valid session token. Some requests (such as Payment*) consume it.
     *
     * @param sessionToken Previously obtained session token as {@link String}
     * @return this object
     */
    public T addSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return (T) this;
    }

    /**
     * Adds a client request id to the request. It is used to track the different client requests.
     *
     * @param clientRequestId id used to track the request
     * @return this object
     */
    public T addClientRequestId(String clientRequestId) {
        this.clientRequestId = clientRequestId;
        return (T) this;
    }

    /**
     * Adds an internal id to the request. It is used to track the different client requests internally.
     *
     * @param internalRequestId id used to track the request
     * @return this object
     */
    public T addInternalRequestId(String internalRequestId) {
        this.internalRequestId = internalRequestId;
        return (T) this;
    }

    /**
     * Adds required merchant info to the request.
     *
     * @param merchantInfo A {@link MerchantInfo} object to get the merchant info from, when building the request
     * @return this object
     * @see MerchantInfo
     * @see NuveiBuilder#addMerchantInfo(String, String, String, String, Constants.HashAlgorithm)
     */
    public T addMerchantInfo(MerchantInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
        return (T) this;
    }

    /**
     * Adds required merchant info to the request.
     *
     * @param merchantKey    The secret merchant key obtained by the Merchant during integration process with Nuvei
     * @param merchantId     Merchant id in the Nuvei's system
     * @param merchantSiteId Merchant site id in the Nuvei's system
     * @param serverHost     The Nuvei's server address to send the request to
     * @param hashAlgorithm  The hashing algorithm used to generate the checksum
     * @return this object
     * @see MerchantInfo
     * @see NuveiBuilder#addMerchantInfo(MerchantInfo)
     */
    public T addMerchantInfo(String merchantKey, String merchantId, String merchantSiteId, String serverHost, Constants.HashAlgorithm hashAlgorithm) {
        this.merchantInfo = new MerchantInfo(merchantKey, merchantId, merchantSiteId, serverHost, hashAlgorithm);
        return (T) this;
    }

    /**
     * Adds the common data, collected by this builder. The intent of this method is to be used from the derived classes.
     *
     * @param nuveiRequest an already created request of type <{@code T} extends {@link NuveiRequest}>
     * @param <S>               type parameter
     * @return the passed {@code nuveiRequest} filled with the data from this builder
     */
    protected <S extends NuveiRequest> S build(S nuveiRequest) {

        String timestamp = RequestUtils.calculateTimestamp();
        nuveiRequest.setMerchantId(merchantInfo != null ? merchantInfo.getMerchantId() : null);
        nuveiRequest.setMerchantSiteId(merchantInfo != null ? merchantInfo.getMerchantSiteId() : null);
        nuveiRequest.setServerHost(merchantInfo != null ? merchantInfo.getServerHost() : null);
        nuveiRequest.setSessionToken(sessionToken);
        nuveiRequest.setTimeStamp(timestamp);
        nuveiRequest.setClientRequestId(clientRequestId);
        nuveiRequest.setInternalRequestId(internalRequestId);
        nuveiRequest.setChecksum(
                ChecksumUtils.calculateChecksum(nuveiRequest, merchantInfo != null ? merchantInfo.getMerchantKey() : "", Constants.CHARSET_UTF8,
                        merchantInfo != null ? merchantInfo.getHashAlgorithm() : null));

        return nuveiRequest;
    }

    /**
     * Builds the request.
     *
     * @return the created NuveiRequest
     * @throws ConstraintViolationException if any part of the request is invalid
     */
    public abstract NuveiBaseRequest build() throws ConstraintViolationException;
}
