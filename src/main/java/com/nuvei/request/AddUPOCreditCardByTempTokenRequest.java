/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuvei.model.UserAddress;
import com.nuvei.request.builder.NuveiBuilder;
import com.nuvei.util.AddressUtils;
import com.nuvei.util.Constants;
import com.nuvei.util.ValidChecksum;
import com.nuvei.util.ValidationUtils;

/**
 * <p>
 * Request to add Credit/Debit card(via temporary token) User Payment Option to a User.
 * </p>
 * When submitting consumer’s full credit card number, Nuvei provides a temporary token in the response that
 * represents the consumer’s credit card.
 * The next time the consumer completes a transaction, the merchant sends Nuvei the temporary token instead
 * of the consumer’s credit card information. The temporary token value represents the consumer’s credit card number.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @see AddUPOAPMRequest
 * @see AddUPOCreditCardRequest
 * @see CardTokenizationRequest
 * @since 3/9/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.API_GENERIC_CHECKSUM_MAPPING)
public class AddUPOCreditCardByTempTokenRequest
        extends NuveiRequest {

    /**
     * The unique identifier generated for each customer.
     */
    @NotNull(message = "userTokenId parameter is mandatory!")
    @Size(min = 1, max = 45)
    private String userTokenId;

    /**
     * The temporary hash of the credit card.
     */
    @NotNull(message = "ccTempToken parameter is mandatory!")
    @Size(min = 1, max = 45)
    private String ccTempToken;

    /**
     * Billing address related to a user payment option. Since order can contain only one payment option billing address is part of the order parameters.
     */
    @Valid
    private UserAddress billingAddress;

    public static Builder builder() {
        return new Builder();
    }

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public String getCcTempToken() {
        return ccTempToken;
    }

    public void setCcTempToken(String ccTempToken) {
        this.ccTempToken = ccTempToken;
    }

    public UserAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(UserAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddUPOCreditCardByTempTokenRequest{");
        sb.append("userTokenId='")
                .append(userTokenId)
                .append('\'');
        sb.append(", ccTempToken='")
                .append(ccTempToken)
                .append('\'');
        sb.append(", billingAddress=")
                .append(billingAddress);
        sb.append(", ")
                .append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static class Builder extends NuveiBuilder<Builder> {

        private String userTokenId;
        private String ccTempToken;
        private UserAddress billingAddress;

        /**
         * Adds user token id to the request.
         *
         * @param userTokenId The user token as {@link String}
         * @return this object
         */
        public Builder addUserTokenId(String userTokenId) {
            this.userTokenId = userTokenId;
            return this;
        }

        /**
         * Adds temp token to the request. The temp token is a hash representation of the the {@link com.nuvei.model.CardData}
         *
         * @param ccTempToken hash of the credit card
         * @return this object
         */
        public Builder addCCTempToken(String ccTempToken) {
            this.ccTempToken = ccTempToken;
            return this;
        }

        /**
         * Adds billing info to the request.
         *
         * @param firstName The first name of the recipient
         * @param lastName  The last name of the recipient
         * @param email     The email of the recipient
         * @param phone     The phone number of the recipient
         * @param address   The address of the recipient
         * @param city      The city of the recipient
         * @param country   The country of the recipient(two-letter ISO country code)
         * @param state     The state of the recipient(two-letter ISO state code)
         * @param zip       The postal code of the recipient
         * @param cell      The cell number of the recipient
         * @return this object
         */
        public Builder addBillingAddress(String firstName, String lastName, String email, String phone, String address, String city, String country,
                                         String state, String zip, String cell, String county) {

            UserAddress billingAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                    city, country, state, zip, cell, county);

            return addBillingAddress(billingAddress);
        }

        /**
         * Adds billing info to the request.
         *
         * @param firstName The first name of the recipient
         * @param lastName  The last name of the recipient
         * @param email     The email of the recipient
         * @param phone     The phone number of the recipient
         * @param address   The address of the recipient
         * @param city      The city of the recipient
         * @param country   The country of the recipient(two-letter ISO country code)
         * @param state     The state of the recipient(two-letter ISO state code)
         * @param zip       The postal code of the recipient
         * @param cell      The cell number of the recipient
         * @param addressLine2 The address line 2 of the recipient's address
         * @param addressLine3 The address line 3 of the recipient's address
         * @param shipAddressLine2 The shipping address line 2 of the recipient's address
         * @param shipAddressLine3 The shipping address line 3 of the recipient's address
         * @return this object
         */
        public Builder addBillingAddress(String firstName, String lastName, String email, String phone, String address, String city, String country,
                                         String state, String zip, String cell, String county, String addressLine2, String addressLine3, String shipAddressLine2,
                                         String shipAddressLine3) {

            UserAddress billingAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                    city, country, state, zip, cell, county, addressLine2, addressLine3, shipAddressLine2, shipAddressLine3);

            return addBillingAddress(billingAddress);
        }

        /**
         * Adds billing address data to the request.
         *
         * @param billingAddress {@link UserAddress} object to get the billing details from
         * @return this object
         */
        public Builder addBillingAddress(UserAddress billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        /**
         * Builds the request.
         *
         * @return {@link NuveiRequest} object build from the params set by this builder
         * @throws ConstraintViolationException if the validation of the params fails
         */
        @Override
        public NuveiBaseRequest build() throws ConstraintViolationException {
            AddUPOCreditCardByTempTokenRequest addUPOCreditCardByTempTokenRequest = new AddUPOCreditCardByTempTokenRequest();
            addUPOCreditCardByTempTokenRequest.setUserTokenId(userTokenId);
            addUPOCreditCardByTempTokenRequest.setCcTempToken(ccTempToken);
            addUPOCreditCardByTempTokenRequest.setBillingAddress(billingAddress);
            return ValidationUtils.validate(super.build(addUPOCreditCardByTempTokenRequest));
        }
    }
}