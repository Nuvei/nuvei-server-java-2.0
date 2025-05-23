/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request.builder;

import java.util.ArrayList;
import java.util.List;

import com.nuvei.model.Addendums;
import com.nuvei.model.AmountDetails;
import com.nuvei.model.RestApiUserDetails;
import com.nuvei.model.DeviceDetails;
import com.nuvei.model.DynamicDescriptor;
import com.nuvei.model.Item;
import com.nuvei.model.MerchantDetails;
import com.nuvei.model.ShippingTrackingDetails;
import com.nuvei.model.UrlDetails;
import com.nuvei.model.UserAddress;
import com.nuvei.request.NuveiOrderDetailsRequest;
import com.nuvei.util.AddressUtils;
import com.nuvei.util.ChecksumUtils;
import com.nuvei.util.Constants;
import com.nuvei.util.DeviceUtils;
import com.nuvei.util.MerchantUtils;
import com.nuvei.util.UrlUtils;

/**
 * <p>
 * A base builder for an order related requests.
 * </p>
 * @author Nikola Dichev
 * @see NuveiBuilder
 * @see NuveiCCBuilder
 * @see NuveiTransactionBuilder
 * @since 2/20/2017
 */
public abstract class NuveiOrderBuilder<T extends NuveiOrderBuilder<T>> extends NuveiBuilder<T> {

    private String currency;
    private String amount;
    private List<Item> items = new ArrayList<>();
    private DeviceDetails deviceDetails;
    private RestApiUserDetails userDetails;
    private UserAddress shippingAddress;
    private UserAddress billingAddress;
    private DynamicDescriptor dynamicDescriptor;
    private MerchantDetails merchantDetails;
    private UrlDetails urlDetails;
    private Addendums addendums;
    private String userTokenId;
    private String clientUniqueId;
    private AmountDetails amountDetails;
    private ShippingTrackingDetails shippingTrackingDetails;
    private String digitalAssetType;

    /**
     * Adds amount to the request.
     *
     * @param amount amount value as {@link String} E.g. "10", "10.11", "10.1101"
     * @return this object
     */
    public T addAmount(String amount) {
        this.amount = amount;
        return (T) this;
    }

    /**
     * Adds currency to the request.
     *
     * @param currency the three character ISO currency code
     * @return this object
     */
    public T addCurrency(String currency) {
        this.currency = currency;
        return (T) this;
    }

    /**
     * Adds item to the request. The item is added to a {@link List} of items and is not replaced.
     *
     * @param item the {@link Item} to add to the request
     * @return this object
     */
    public T addItem(Item item) {

        items.add(item);
        return (T) this;
    }

    /**
     * Adds item to the request. The item is added to a {@link List} of items and is not replaced.
     *
     * @param name     the name of the item to add
     * @param price    the price of the item to add
     * @param quantity the quantity of the item to add
     * @return this object
     */
    public T addItem(String name, String price, String quantity) {

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);

        return addItem(item);
    }

    public T addItems(List<Item> items) {
        if (items != null) {
            this.items.addAll(items);
        }

        return (T)this;
    }

    /**
     * Adds device details to the request.
     *
     * @param deviceDetails the {@link DeviceDetails} to add to the request
     * @return this object
     */
    public T addDeviceDetails(DeviceDetails deviceDetails) {

        this.deviceDetails = deviceDetails;
        return (T) this;
    }

    /**
     * Adds device details to the request.
     *
     * @param deviceType the type of the device making the request
     * @param deviceName the type of the device making the request
     * @param deviceOS   the device operating system
     * @param browser    the browser that the device used making the request
     * @param ipAddress  the IP address of the device making the request
     * @return this object
     */
    public T addDeviceDetails(String deviceType, String deviceName, String deviceOS, String browser, String ipAddress) {

        return addDeviceDetails(DeviceUtils.createDeviceDetailsFromParams(deviceType, deviceName, deviceOS, browser, ipAddress));
    }

    /**
     * Adds user details to the request.
     *
     * @param userDetails the {@link RestApiUserDetails} to add to the request
     * @return this object
     */
    public T addUserDetails(RestApiUserDetails userDetails) {

        this.userDetails = userDetails;
        return (T) this;
    }

    /**
     * Adds user details to the request.
     *
     * @param address     The address of the user
     * @param city        The city of the user
     * @param country     The city of the user(two-letter ISO country code)
     * @param email       The email of the user
     * @param firstName   The first name of the user
     * @param lastName    The last name of the user
     * @param phone       The phone number of the user
     * @param state       The state of the user(two-letter ISO state code)
     * @param zip         The postal code of the user
     * @param dateOfBirth The date of birth of the user
     * @return this object
     */
    public T addUserDetails(String address, String city, String country, String email, String firstName, String lastName, String phone, String state,
                            String zip, String dateOfBirth, String county) {

        RestApiUserDetails userDetails = AddressUtils.createRestApiUserDetailsFromParams(address, city, country, email,
                firstName, lastName, phone, state, zip, dateOfBirth, county);
        return addUserDetails(userDetails);
    }

    /**
     * Adds shipping info to the request.
     *
     * @param userAddress {@link UserAddress} object to add to the request as shipping details
     * @return this object
     */
    public T addShippingDetails(UserAddress userAddress) {

        this.shippingAddress = userAddress;
        return (T) this;
    }

    /**
     * Adds shipping info to the request.
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
    public T addShippingDetails(String firstName, String lastName, String email, String phone, String address, String city, String country,
                                String state, String zip, String cell, String county) {

        UserAddress userAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                city, country, state, zip, cell, county);

        return addShippingDetails(userAddress);
    }

    /**
     * Adds shipping info to the request.
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
    public T addShippingDetails(String firstName, String lastName, String email, String phone, String address, String city, String country,
                                String state, String zip, String cell, String county, String addressLine2, String addressLine3, String shipAddressLine2,
                                String shipAddressLine3) {

        UserAddress userAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                city, country, state, zip, cell, county, addressLine2, addressLine3, shipAddressLine2, shipAddressLine3);

        return addShippingDetails(userAddress);
    }

    /**
     * Adds billing info to the request.
     *
     * @param userAddress {@link UserAddress} object to add to the request as billing details
     * @return this object
     */
    public T addBillingDetails(UserAddress userAddress) {

        this.billingAddress = userAddress;
        return (T) this;
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
    public T addBillingDetails(String firstName, String lastName, String email, String phone, String address, String city, String country,
                               String state, String zip, String cell, String county) {

        UserAddress userAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                city, country, state, zip, cell, county);

        return addBillingDetails(userAddress);
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
    public T addBillingDetails(String firstName, String lastName, String email, String phone, String address, String city, String country,
                               String state, String zip, String cell, String county, String addressLine2, String addressLine3, String shipAddressLine2,
                               String shipAddressLine3) {

        UserAddress userAddress = AddressUtils.createUserAddressFromParams(firstName, lastName, email, phone, address,
                city, country, state, zip, cell, county, addressLine2, addressLine3, shipAddressLine2, shipAddressLine3);

        return addBillingDetails(userAddress);
    }

    /**
     * Adds Merchant's dynamic data to the request.
     *
     * @param dynamicDescriptor {@link DynamicDescriptor} object to add to the request as billing details
     * @return this object
     */
    public T addDynamicDescriptor(DynamicDescriptor dynamicDescriptor) {

        this.dynamicDescriptor = dynamicDescriptor;
        return (T) this;
    }

    /**
     * Adds Merchant's dynamic data to the request.
     *
     * @param merchantName  Merchant's name
     * @param merchantPhone Merchant's phone number
     * @return this object
     */
    public T addDynamicDescriptor(String merchantName, String merchantPhone) {

        DynamicDescriptor dynamicDescriptor = new DynamicDescriptor();
        dynamicDescriptor.setMerchantName(merchantName);
        dynamicDescriptor.setMerchantPhone(merchantPhone);

        return addDynamicDescriptor(dynamicDescriptor);
    }

    /**
     * Adds Merchant's specific custom data to the request.
     *
     * @param merchantDetails {@link MerchantDetails} object to add to the request
     * @return this object
     */
    public T addMerchantDetails(MerchantDetails merchantDetails) {

        this.merchantDetails = merchantDetails;
        return (T) this;
    }

    /**
     * Adds Merchant's specific custom data to the request.
     *
     * @param customField1  {@link String} to store in {@code customField1}
     * @param customField2  {@link String} to store in {@code customField2}
     * @param customField3  {@link String} to store in {@code customField3}
     * @param customField4  {@link String} to store in {@code customField4}
     * @param customField5  {@link String} to store in {@code customField5}
     * @param customField6  {@link String} to store in {@code customField6}
     * @param customField7  {@link String} to store in {@code customField7}
     * @param customField8  {@link String} to store in {@code customField8}
     * @param customField9  {@link String} to store in {@code customField9}
     * @param customField10 {@link String} to store in {@code customField10}
     * @param customField11 {@link String} to store in {@code customField11}
     * @param customField12 {@link String} to store in {@code customField12}
     * @param customField13 {@link String} to store in {@code customField13}
     * @param customField14 {@link String} to store in {@code customField14}
     * @param customField15 {@link String} to store in {@code customField15}
     * @return this object
     */
    public T addMerchantDetails(String customField1, String customField2, String customField3, String customField4, String customField5,
                                String customField6, String customField7, String customField8, String customField9, String customField10,
                                String customField11, String customField12, String customField13, String customField14, String customField15) {

        MerchantDetails merchantDetails = MerchantUtils.createMerchantDetailsFromParams(customField1, customField2, customField3,
                customField4, customField5, customField6, customField7, customField8, customField9, customField10, customField11, 
                customField12, customField13, customField14, customField15);

        return addMerchantDetails(merchantDetails);
    }

    /**
     * Adds URLs to redirect to in case of success/failure and URL to send notification(DMN) to.
     *
     * @param urlDetails {@link UrlDetails} object to add to the request
     * @return this object
     */
    public T addURLDetails(UrlDetails urlDetails) {

        this.urlDetails = urlDetails;
        return (T) this;
    }

    /**
     * Adds URLs to redirect to in case of success/failure and URL to send notification(DMN) to.
     *
     * @param failureUrl      URL to redirect to in case of failed transaction
     * @param pendingUrl      URL to redirect to in case of pending transaction
     * @param successUrl      URL to redirect to in case of successful transaction
     * @param notificationUrl URL to send notification(DMN) to
     * @return this object
     */
    public T addURLDetails(String failureUrl, String pendingUrl, String successUrl, String notificationUrl) {

        UrlDetails urlDetails = UrlUtils.createUrlDetails(failureUrl, pendingUrl, successUrl, notificationUrl);
        return addURLDetails(urlDetails);
    }

    /**
     * Adds additional domain specific payment info(addendum) to the request.
     *
     * @param addendums {@code Addendums} object to add to the request
     * @return this object
     * @see Addendums
     */
    public T addAddendums(Addendums addendums) {

        this.addendums = addendums;
        return (T) this;
    }

    /**
     * Adds empty additional domain specific payment info(addendum) to the request.
     *
     * @return this object
     */
    public T addAddendums() {

        Addendums addendums = new Addendums();
        return addAddendums(addendums);
    }

    /**
     * Adds user's token id to the request.
     *
     * @param userTokenId the user's token id to add to the request
     * @return this object
     */
    public T addUserTokenId(String userTokenId) {

        this.userTokenId = userTokenId;
        return (T) this;
    }

    /**
     * Adds client's unique id to the request.
     *
     * @param clientUniqueId the client's unique id to add to the request
     * @return this object
     */
    public T addClientUniqueId(String clientUniqueId) {

        this.clientUniqueId = clientUniqueId;
        return (T) this;
    }

    /**
     * Adds Amount details to the request.
     *
     * @return this object
     */
    public T addAmountDetails(AmountDetails amountDetails) {

        this.amountDetails = amountDetails;
        return (T) this;
    }
    
    public T addAmountDetails(String totalHandling, String totalShipping, String totalTax, String totalDiscount) {

        AmountDetails amountDetails = new AmountDetails();
        amountDetails.setTotalDiscount(totalDiscount);
        amountDetails.setTotalHandling(totalHandling);
        amountDetails.setTotalShipping(totalShipping);
        amountDetails.setTotalTax(totalTax);
        return addAmountDetails(amountDetails);
    }

    /**
     * Adds shippingTrackingDetails info to the request.
     *
     * @param shippingTrackingDetails {@link ShippingTrackingDetails} object to add to the request as shipping details
     * @return this object
     */
    public T addShippingTrackingDetails(ShippingTrackingDetails shippingTrackingDetails) {

        this.shippingTrackingDetails = shippingTrackingDetails;
        return (T) this;
    }


    /**
     * Adds digitalAssetType to the request.
     *
     * @param digitalAssetType       Digital currency transaction identifier.
     * @return this object
    */
    public T addDigitalAssetType(String digitalAssetType) {
        this.digitalAssetType = digitalAssetType;
        return (T) this;
    }

    /**
     * Adds the order details data, collected by this builder.
     *
     * @param nuveiOrderDetailsRequest an already created request of type T
     * @param <S>                           type parameter
     * @return this object
     */
    protected <S extends NuveiOrderDetailsRequest> S build(S nuveiOrderDetailsRequest) {

        super.build(nuveiOrderDetailsRequest);

        nuveiOrderDetailsRequest.setCurrency(currency);
        nuveiOrderDetailsRequest.setAmount(amount);
        for (Item item : items) {
            nuveiOrderDetailsRequest.addItem(item);
        }
        nuveiOrderDetailsRequest.setDeviceDetails(deviceDetails);
        nuveiOrderDetailsRequest.setUserDetails(userDetails);
        nuveiOrderDetailsRequest.setShippingAddress(shippingAddress);
        nuveiOrderDetailsRequest.setBillingAddress(billingAddress);
        nuveiOrderDetailsRequest.setDynamicDescriptor(dynamicDescriptor);
        nuveiOrderDetailsRequest.setMerchantDetails(merchantDetails);
        nuveiOrderDetailsRequest.setUrlDetails(urlDetails);
        nuveiOrderDetailsRequest.setAddendums(addendums);
        nuveiOrderDetailsRequest.setUserTokenId(userTokenId);
        nuveiOrderDetailsRequest.setClientUniqueId(clientUniqueId);
        nuveiOrderDetailsRequest.setAmountDetails(amountDetails);
        nuveiOrderDetailsRequest.setShippingTrackingDetails(shippingTrackingDetails);
        nuveiOrderDetailsRequest.setDigitalAssetType(digitalAssetType);

        nuveiOrderDetailsRequest.setChecksum(
                ChecksumUtils.calculateChecksum(nuveiOrderDetailsRequest, merchantInfo != null ? merchantInfo.getMerchantKey() : "",
                        Constants.CHARSET_UTF8, merchantInfo != null ? merchantInfo.getHashAlgorithm() : null));

        return nuveiOrderDetailsRequest;
    }
}
