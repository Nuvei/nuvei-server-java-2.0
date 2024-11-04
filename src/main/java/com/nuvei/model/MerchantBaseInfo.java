/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.model;

import javax.validation.constraints.NotNull;

import com.nuvei.util.Constants;

/**
 * <p>
 * Holder for the required for a payment Checkout Page's Merchant's data such as
 * secret key, the merchant id, the merchant site id, etc.
 * </p>
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/17/2017
 */
public class MerchantBaseInfo {

    @NotNull(message = "merchantKey may not be null!")
    private String merchantKey;

    @NotNull(message = "serverHost may not be null")
    private String serverHost;

    private Constants.HashAlgorithm hashAlgorithm;

    /**
     * Constructs a {@link MerchantInfo} object with data from the params.
     *
     * @param merchantKey    The secret merchant key obtained by the Merchant during integration process with nuvei
     * @param serverHost     The Nuvei's server address to send the request to
     * @param hashAlgorithm  The hashing algorithm used to generate the checksum
     */
    public MerchantBaseInfo(String merchantKey, String serverHost, Constants.HashAlgorithm hashAlgorithm) {
        this.merchantKey = merchantKey;
        this.serverHost = serverHost;
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public String getServerHost() {
        return serverHost;
    }

    public Constants.HashAlgorithm getHashAlgorithm() {
        return hashAlgorithm;
    }
}