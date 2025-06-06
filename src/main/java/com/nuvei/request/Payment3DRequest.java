/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request;

import com.nuvei.request.builder.NuveiCCBuilder;
import com.nuvei.util.Constants;
import com.nuvei.util.ValidChecksum;
import com.nuvei.util.ValidationUtils;

/**
 * <p>
 * Request to complete a payment using 3D Secure authentication. It must be preceded by {@link Authorization3DRequest}.
 * <p>
 * 3D Secure is a credit card authentication program offered by Visa® and MasterCard® meant to reduce fraudulent purchases
 * by verifying the purchaser’s identity during online transactions. The goal of the 3D Secure program is to authenticate
 * the cardholders’ identity and verify that they are authorized to complete a transaction.
 * <p>
 * When 3DSecure is implemented into the payment process, disputed transactions and chargebacks are reduced by shifting
 * the liability of the charge from the merchant to the cardholder.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @see Authorization3DRequest
 * @see PaymentAPMRequest
 * @see PaymentCCRequest
 * @since 2/15/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.API_GENERIC_CHECKSUM_MAPPING)
public class Payment3DRequest
        extends NuveiCCRequest {

    /**
     * The payment authorization response returned by the card issuer/bank.
     */
    private String paResponse;

    public static Builder builder() {
        return new Builder();
    }

    public String getPaResponse() {
        return paResponse;
    }

    public void setPaResponse(String paResponse) {
        this.paResponse = paResponse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment3DRequest{");
        sb.append("paResponse=")
                .append(getPaResponse());
        sb.append(", ")
                .append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static class Builder extends NuveiCCBuilder<Builder> {

        private String paResponse;

        /**
         * Adds the paResponse param to the request.
         *
         * @param paResponse The payment authorization response returned by the card issuer/bank.
         * @return this object
         */
        public Builder addPaResponse(String paResponse) {
            this.paResponse = paResponse;
            return this;
        }

        /**
         * Builds the request.
         *
         * @return {@link NuveiRequest} object build from the params set by this builder
         */
        @Override
        public NuveiBaseRequest build() {
            Payment3DRequest request = new Payment3DRequest();
            request.setPaResponse(paResponse);
            return ValidationUtils.validate(super.build(request));
        }

    }
}
