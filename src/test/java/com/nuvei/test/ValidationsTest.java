/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.nuvei.model.CardData;
import com.nuvei.model.DynamicDescriptor;
import com.nuvei.model.Item;
import com.nuvei.model.MerchantBaseInfo;
import com.nuvei.model.MerchantDetails;
import com.nuvei.model.MerchantInfo;
import com.nuvei.model.RestApiUserDetails;
import com.nuvei.model.SubMerchant;
import com.nuvei.model.SubMethodDetails;
import com.nuvei.model.UrlDetails;
import com.nuvei.model.UserAddress;
import com.nuvei.request.AccountCaptureRequest;
import com.nuvei.request.AddUPOAPMRequest;
import com.nuvei.request.AddUPOCreditCardByTempTokenRequest;
import com.nuvei.request.AddUPOCreditCardByTokenRequest;
import com.nuvei.request.AddUPOCreditCardRequest;
import com.nuvei.request.Authorization3DRequest;
import com.nuvei.request.CancelSubscriptionRequest;
import com.nuvei.request.CardDetailsRequest;
import com.nuvei.request.CardTokenizationRequest;
import com.nuvei.request.CreateSubscriptionRequest;
import com.nuvei.request.DccDetailsRequest;
import com.nuvei.request.DeleteUPORequest;
import com.nuvei.request.EditUPOAPMRequest;
import com.nuvei.request.EditUPOCreditCardRequest;
import com.nuvei.request.EnableUPORequest;
import com.nuvei.request.GetMerchantPaymentMethodsRequest;
import com.nuvei.request.GetOrderDetailsRequest;
import com.nuvei.request.GetSessionTokenRequest;
import com.nuvei.request.GetSubscriptionPlansRequest;
import com.nuvei.request.GetSubscriptionsListRequest;
import com.nuvei.request.GetUserUPOsRequest;
import com.nuvei.request.McpRatesRequest;
import com.nuvei.request.OpenOrderRequest;
import com.nuvei.request.Payment3DRequest;
import com.nuvei.request.PaymentAPMRequest;
import com.nuvei.request.PaymentCCRequest;
import com.nuvei.request.PayoutRequest;
import com.nuvei.request.RefundTransactionRequest;
import com.nuvei.request.NuveiBaseRequest;
import com.nuvei.request.SettleTransactionRequest;
import com.nuvei.request.SuspendUPORequest;
import com.nuvei.request.UpdateOrderRequest;
import com.nuvei.request.Verify3dRequest;
import com.nuvei.request.VoidTransactionRequest;
import com.nuvei.request.basic.EditUPOBasicRequest;
import com.nuvei.util.AddressUtils;
import com.nuvei.util.Constants;
import com.nuvei.util.DynamicDescriptorUtils;
import com.nuvei.util.MerchantUtils;
import com.nuvei.util.UrlUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/24/2017
 */
public class ValidationsTest {

    private static final String CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY =
            "ConstraintViolationException expected, but object creation passed successfully";
    private static final MerchantInfo validMerchantInfo =
            new MerchantInfo("dummy", "1234", "1234", "http://dummy:1234/ppp/", Constants.HashAlgorithm.MD5);
    private static final MerchantInfo invalidMerchantInfo =
            new MerchantInfo("dummy", null, null, "http://dummy:1234/ppp/", Constants.HashAlgorithm.MD5);

    private static final MerchantBaseInfo validBaseMerchantInfo =
            new MerchantBaseInfo("dummy", "http://dummy:1234/ppp/", Constants.HashAlgorithm.MD5);
    private static final MerchantBaseInfo invalidBaseMerchantInfo =
            new MerchantBaseInfo(null, "http://dummy:1234/ppp/", Constants.HashAlgorithm.MD5);

    private static final DynamicDescriptor someDynamicDescriptor = DynamicDescriptorUtils.createDynamicDescriptor("merchantName", "merchantPhone");

    private static final MerchantDetails merchantDetails = MerchantUtils.createMerchantDetailsFromParams("customField1", "customField2", "customField3", "customField4",
            "customField5", "customField6", "customField7", "customField8", "customField9", "customField10",
            "customField11", "customField12", "customField13", "customField14", "customField15");

    private static final SubMethodDetails subMethodDetails = new SubMethodDetails("testSubmethod", "testField1", "testField2");
    private static final DynamicDescriptor dynamicDescriptor = new DynamicDescriptor("testName", "029999999");
    private static final CardData cardData = new CardData("testNumber", "testCardHolderName", "2", "2001", "testCCtempToken", "222");

    private static final String dummySessionToken = "dummySessionToken";

    private static final Item dummyValidItem = new Item();
    private static final Item dummyValidItem2 = new Item();
    private static final Item dummyInvalidItem = new Item();

    private static final String dummyOrderId = "1234";
    private static final String dummyUserId = "dummyUserId";
    private static final Map<String, String> dummyValidApmData = new HashMap<>();
    private static final String dummyPaymentMethodName = "dummyPaymentMethodName";
    private static final String dummyCcToken = "dummyCcToken";

    private static final String dummyComment = "comment";

    private static final String validAmount = "1.00";
    private static final String invalidAmount = "-1.00";

    private static final String validAmountTwoItems = "5.00";
    private static final String invalidAmountTwoItems = "-5.00";

    private static final String validCurrencyCode = "EUR";
    private static final String invalidCurrencyCode = "Euro";

    private static final String validCountryCode = "GB";
    private static final String invalidCountryCode = "Ukraine";

    private static final String validLanguageCode = "en";
    private static final String invalidLanguageCode = "Russian";

    private static final CardData dummyCardData = new CardData();
    private static final String dummySubscriptionPlanId = "123456";
    private static final String dummyInitialTransactionId = "123456";
    private static final String dummyRelatedTransactionId = "1234567";
    private static final String dummyAuthCode = "auth_code";

    private static final String dummyBrand = "Visa";
    private static final String dummyUniqueCC = "visa45563";
    private static final String dummyBin = "41111";
    private static final String dummyLast4Digits = "1111";
    private static final String dummyCCExpMonth = "03";
    private static final String dummyCCExpYear = "2020";
    private static final String dummyCCNameOnCard = "Nikola Dichev";
    private static final String dummyUserPaymentOptionId = "42342";

    private static final String validBrand = "Visa";

    private static final String dummyAddress = "address";
    private static final String dummyCity = "Sofia";
    private static final String dummyCountryCode = "1111";
    private static final String dummyEMail = "email@abv.bg";
    private static final String dummyFirstName = "Nikola";
    private static final String dummyLastName = "Dichev";
    private static final String dummyPhone = "029999999";
    private static final String dummyState = "New York";
    private static final String dummyZip = "1111";
    private static final String dummyBirthDate = "1990-01-01";
    private static final String dummyCountry = "USA";
    private static final String dummyLocale = "EN";
    private static final String dummyUserTokenId = "userTokenID";
    private static final String dummyClientUniqueId = "clientUniqueId";
    private static final String dummySubmethod = "submethod";

    private static final RestApiUserDetails dummyValidRestApiUserDetails =
            AddressUtils.createRestApiUserDetailsFromParams("Test street 1", "Sofia", "BG", "test@test.com",
                    "Test", "Testov", "0884123456", null, "1000", "1990-01-01", "county usr");
    private static final RestApiUserDetails dummyInvalidRestApiUserDetails = AddressUtils.createRestApiUserDetailsFromParams("Test street 1 ", "Sofia", "BG", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789-9",
            "Test Long Name that should fail the validation! Test Long Name that should fail the validation!",
            "Test Long Name that should fail the validation! Test Long Name that should fail the validation! Test Long Name that should fail the validation!",
            "0884123456", null, "1000", "1990-01-01", "county usr");

    private static final UserAddress dummyValidBillingDetails =
            AddressUtils.createUserAddressFromParams("Test", "Testov", "test@test.com", "0884123456", "Test street 1",
                    "Sofia", "BG", null, "1000", "0884123456", "county billing");
    private static final UserAddress dummyValidShippingDetails = dummyValidBillingDetails; //shipping address same as billing address

    private static final UserAddress dummyValidBillingDetailsWithAddressLines =
            AddressUtils.createUserAddressFromParams("Test", "Testov", "test@test.com", "0884123456", "Test street 1",
                    "Sofia", "BG", null, "1000", "0884123456", "county billing",
                    "address line 2", "address line 3", null, null);

    private static final UserAddress dummyValidShippingDetailsWithAddressLines =  AddressUtils.createUserAddressFromParams("Test", "Testov", "test@test.com", "0884123456", "Test street 1",
            "Sofia", "BG", null, "1000", "0884123456", "county billing",
            null, null, "shipping address line 2", "shipping address line 3");

    private static final UserAddress dummyInvalidBillingDetails =
            AddressUtils.createUserAddressFromParams("Test Long Name that should fail the validation! Test Long Name that should fail the validation!",
                    "Test Long Name that should fail the validation! Test Long Name that should fail the validation! Test Long Name that should fail the validation!",
                    "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789-9", "0884123456", "Test street 1",
                    "Sofia", "BG", null, "1000", "0884123456", "county bil 1");
    private static final UserAddress dummyInvalidShippingDetails = dummyInvalidBillingDetails; //shipping address same as billing address

    private static final UserAddress dummyInvalidBillingDetailsWithLongAddressLines =
            AddressUtils.createUserAddressFromParams("Test", "Testov", "test@test.com", "0884123456", "Test street 1",
                    "Sofia", "BG", null, "1000", "0884123456", "county billing",
                    "1234567890123456789012345678901234567890123456789012345678901", "1234567890123456789012345678901234567890123456789012345678901", null, null);

    private static final UserAddress dummyInvalidShippingDetailsWithLongAddressLines =
            AddressUtils.createUserAddressFromParams("Test", "Testov", "test@test.com", "0884123456", "Test street 1",
                    "Sofia", "BG", null, "1000", "0884123456", "county billing",
                    null , null, "1234567890123456789012345678901234567890123456789012345678901", "1234567890123456789012345678901234567890123456789012345678901");


    private static final UrlDetails dummyValidUrlDetails = UrlUtils.createUrlDetails("https://apmtest.gate2shop.com/nikolappp/cashier/cancel.do",
            "https://apmtest.gate2shop.com/nikolappp/defaultPending.do",
            "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do", null);
    private static final UrlDetails dummyInvalidUrlDetails = UrlUtils.createUrlDetails("https://apmtest.gate2shop.com/nikolappp/cashier/cancel.do",
            "https://apmtest.gate2shop.com/nikolappp/defaultPending.do",
            "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do" +
                    "https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do https://apmtest.gate2shop.com/nikolappp/defaultSuccess.do", null);

    private static final String validPaymentMethodName = "apmgw_expresscheckout";

    private static final String dummyCcCardNumber = "4111 1111 1111 1111";
    private static final String dummyCcExpMonth = "12";
    private static final String dummyCcExpYear = "22";
    private static final String dummyCcNameOnCard = "Ivan Ivanov";

    private static final SubMerchant subMerchant = new SubMerchant();

    @Before
    public void initialization() {
        dummyValidItem.setQuantity("1");
        dummyValidItem.setName("dummyValidItem");
        dummyValidItem.setPrice("1");
        dummyValidItem2.setQuantity("2");
        dummyValidItem2.setName("dummyValidItem2");
        dummyValidItem2.setPrice("2");

        dummyInvalidItem.setName("dummyInvalidItem");

        dummyValidApmData.put("dummyUserName", "dummyExample");

        dummyCardData.setCardHolderName("dummyName");
        dummyCardData.setCardNumber("4111 1111 1111 1111");
        dummyCardData.setCVV("123");
        dummyCardData.setExpirationMonth("01");
        dummyCardData.setExpirationYear("2020");

    }

    private void commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType builderType, int expectedConstraintViolationCount) {

        try {
            EditUPOBasicRequest.builder(builderType)
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(expectedConstraintViolationCount, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_AddUPOAPMRequest() {

        NuveiBaseRequest nuveiRequest = AddUPOAPMRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addApmData(dummyValidApmData)
                .addUserTokenId(dummyUserId)
                .addPaymentMethodName(dummyPaymentMethodName)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_AddUPOAPMRequest() {

        try {
            AddUPOAPMRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addBillingAddress(dummyAddress, dummyCity, dummyCountryCode, dummyEMail, dummyFirstName, dummyLastName, dummyPhone, dummyState,
                            dummyZip, dummyBirthDate, dummyCountry, dummyLocale)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_AddUPOCreditCardByTempTokenRequest() {

        NuveiBaseRequest nuveiRequest = AddUPOCreditCardByTempTokenRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addCCTempToken(dummyCcToken)
                .addUserTokenId(dummyUserId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_AddUPOCreditCardByTempTokenRequest() {

        try {
            NuveiBaseRequest nuveiRequest = AddUPOCreditCardByTempTokenRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
        } catch (ConstraintViolationException e) {
            assertEquals(4, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_AddUPOCreditCardRequest() {

        NuveiBaseRequest nuveiRequest = AddUPOCreditCardRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addCcCardNumber(dummyCcCardNumber)
                .addCcExpMonth(dummyCcExpMonth)
                .addCCExpYear(dummyCcExpYear)
                .addCcNameOnCard(dummyCcNameOnCard)
                .addBillingAddress(dummyFirstName, dummyLastName, dummyAddress, dummyPhone, dummyZip, dummyCity, dummyCountryCode, dummyState,
                        dummyEMail, dummyLocale, dummyBirthDate, dummyCountry)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_AddUPOCreditCardRequest() {

        try {
            AddUPOCreditCardRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(3, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_Authorization3DRequest() {

        NuveiBaseRequest nuveiRequest = Authorization3DRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addIsDynamic3D("0", "off")
                .addAmount(validAmount)
                .addCurrency(validCurrencyCode)
                .addItem(dummyValidItem)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_Authorization3DRequest() {

        try {
            Authorization3DRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addIsDynamic3D("2", null)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(6, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_CancelSubscriptionRequest() {

        NuveiBaseRequest nuveiRequest = CancelSubscriptionRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addSubscriptionId("1234")
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_CancelSubscriptionRequest() {

        try {
            CancelSubscriptionRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(4, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_CardTokenizationRequest() {

        NuveiBaseRequest nuveiRequest = CardTokenizationRequest.builder()
                .addMerchantInfo(validBaseMerchantInfo)
                .addCardData(dummyCardData)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_CardTokenizationRequest() {

        try {
            CardTokenizationRequest.builder()
                    .addMerchantInfo(invalidBaseMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_CreateSubscriptionRequest() {

        NuveiBaseRequest nuveiRequest = CreateSubscriptionRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addSubscriptionPlanId(dummySubscriptionPlanId)
                .addInitialTransactionId(dummyInitialTransactionId)
                .addCardData(cardData)
                .addDynamicDescriptor(dynamicDescriptor)
                .addDeviceDetails("type", "name", "windows", "firefox", "127.0.0.1")
                .addMerchantDetails("customField1", "customField2", "customField3", "customField4", "customField5", "customField6", "customField7",
                        "customField8", "customField9", "customField10", "customField11", "customField12", "customField13", "customField14",
                        "customField15")
                .addURLDetails("failureUrl", "pendingUrl", "successUrl", "notificationUrl")
                .addUserPaymentOption("cvv", "111111")
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_CreateSubscriptionRequest() {

        try {
            CreateSubscriptionRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(4, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testFailedValidationForInitialTransactionId_CreateSubscriptionRequest() {

        try {
            CreateSubscriptionRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .addUserTokenId(dummyUserId)
                    .addSubscriptionPlanId(dummySubscriptionPlanId)
                    .addInitialTransactionId("abcd")
                    .addCardData(cardData)
                    .addDynamicDescriptor(dynamicDescriptor)
                    .addDeviceDetails("type", "name", "windows", "firefox", "127.0.0.1")
                    .addMerchantDetails("customField1", "customField2", "customField3", "customField4", "customField5",
                            "customField6", "customField7",
                            "customField8", "customField9", "customField10", "customField11", "customField12",
                            "customField13", "customField14",
                            "customField15")
                    .addURLDetails("failureUrl", "pendingUrl", "successUrl", "notificationUrl")
                    .addUserPaymentOption("cvv", "111111")
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
            ConstraintViolation violation = e.getConstraintViolations().iterator().next();
            assertEquals("initialTransactionId", violation.getPropertyPath().toString());
            assertEquals("is not valid", violation.getMessage());
        }
    }

    @Test
    public void testSuccessfulValidation_GetMerchantPaymentMethodsRequest() {

        NuveiBaseRequest nuveiRequest = GetMerchantPaymentMethodsRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addCountryCode(validCountryCode)
                .addLanguageCode(validLanguageCode)
                .addCurrencyCode(validCurrencyCode)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_GetMerchantPaymentMethodsRequest() {

        try {
            GetMerchantPaymentMethodsRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addCountryCode(invalidCountryCode)
                    .addLanguageCode(invalidLanguageCode)
                    .addCurrencyCode(invalidCurrencyCode)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetOrderDetailsRequest() {

        NuveiBaseRequest nuveiRequest = GetOrderDetailsRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addOrderId(dummyOrderId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_GetOrderDetailsRequest() {

        try {
            GetOrderDetailsRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(4, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetSessionToken() {
        NuveiBaseRequest nuveiRequest = GetSessionTokenRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_GetSessionToken() {

        try {
            GetSessionTokenRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(2, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetSubscriptionPlansRequest() {

        NuveiBaseRequest nuveiRequest = GetSubscriptionPlansRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_GetSubscriptionPlansRequest() {

        try {
            GetSubscriptionPlansRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(2, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetSubscriptionsListRequest() {

        NuveiBaseRequest nuveiRequest = GetSubscriptionsListRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_GetSubscriptionsListRequest() {

        try {
            GetSubscriptionsListRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(2, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_OpenOrderRequest() {
        NuveiBaseRequest nuveiRequest = OpenOrderRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmount)
                .addItem(dummyValidItem)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_OpenOrderRequest() {

        try {
            NuveiBaseRequest nuveiRequest = OpenOrderRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addAmount(invalidAmount)
                    .addCurrency(invalidCurrencyCode)
                    .addItem(dummyInvalidItem)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations()
                    .size());
        }
    }

    @Test
    public void testSuccessfulValidation_Payment3DRequest() {

        NuveiBaseRequest nuveiRequest = Payment3DRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addAmount(validAmount)
                .addCurrency(validCurrencyCode)
                .addItem(dummyValidItem)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_Payment3DRequest() {

        try {
            Payment3DRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_PaymentAPMRequest() {
        NuveiBaseRequest nuveiRequest = PaymentAPMRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmountTwoItems)
                .addSessionToken(dummySessionToken)
                .addItem(dummyValidItem)
                .addItem(dummyValidItem2)
                .addUserDetails(dummyValidRestApiUserDetails)
                .addBillingDetails(dummyValidBillingDetails)
                .addShippingDetails(dummyValidShippingDetails)
                .addOrderId(dummyOrderId)
                .addURLDetails(dummyValidUrlDetails)
                .addPaymentMethod(validPaymentMethodName)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testSuccessfulValidation_withAddressLines_PaymentAPMRequest() {
        NuveiBaseRequest nuveiRequest = PaymentAPMRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmountTwoItems)
                .addSessionToken(dummySessionToken)
                .addItem(dummyValidItem)
                .addItem(dummyValidItem2)
                .addUserDetails(dummyValidRestApiUserDetails)
                .addBillingDetails(dummyValidBillingDetailsWithAddressLines)
                .addShippingDetails(dummyValidShippingDetailsWithAddressLines)
                .addOrderId(dummyOrderId)
                .addURLDetails(dummyValidUrlDetails)
                .addPaymentMethod(validPaymentMethodName)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void  testFailedValidation_withLongBillingAddressLines_PaymentAPMRequest() {

        try {
            PaymentAPMRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .addCurrency(validCurrencyCode)
                    .addAmount(validAmountTwoItems)
                    .addSessionToken(dummySessionToken)
                    .addItem(dummyValidItem)
                    .addItem(dummyValidItem2)
                    .addUserDetails(dummyValidRestApiUserDetails)
                    .addBillingDetails(dummyInvalidBillingDetailsWithLongAddressLines)
                    .addShippingDetails(dummyValidShippingDetailsWithAddressLines)
                    .addOrderId(dummyOrderId)
                    .addURLDetails(dummyValidUrlDetails)
                    .addPaymentMethod(validPaymentMethodName)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        }  catch (ConstraintViolationException e) {
            List<String> messages = extractConstraintViolationMessages(e);
            assertEquals(2, messages.size());
            assertTrue(messages.contains("addressLine2 size must be up to 60 characters long!"));
            assertTrue(messages.contains("addressLine3 size must be up to 60 characters long!"));
        }
    }

    @Test
    public void  testFailedValidation_withLongShippingAddressLines_PaymentAPMRequest() {

        try {
            PaymentAPMRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .addCurrency(validCurrencyCode)
                    .addAmount(validAmountTwoItems)
                    .addSessionToken(dummySessionToken)
                    .addItem(dummyValidItem)
                    .addItem(dummyValidItem2)
                    .addUserDetails(dummyValidRestApiUserDetails)
                    .addBillingDetails(dummyValidBillingDetailsWithAddressLines)
                    .addShippingDetails(dummyInvalidShippingDetailsWithLongAddressLines)
                    .addOrderId(dummyOrderId)
                    .addURLDetails(dummyValidUrlDetails)
                    .addPaymentMethod(validPaymentMethodName)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        }  catch (ConstraintViolationException e) {
            List<String> messages = extractConstraintViolationMessages(e);
            assertEquals(2, messages.size());
            assertTrue(messages.contains("shipAddressLine2 size must be up to 60 characters long!"));
            assertTrue(messages.contains("shipAddressLine3 size must be up to 60 characters long!"));
        }
    }

    private List<String> extractConstraintViolationMessages(ConstraintViolationException e) {
        List<String> messages = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (constraintViolations != null) {
            for (ConstraintViolation constraintViolation : constraintViolations) {
                messages.add(constraintViolation.getMessage());
            }
        }
        return messages;
    }

    @Test
    public void testFailedValidation_PaymentAPMRequest() {

        try {
            NuveiBaseRequest nuveiRequest = PaymentAPMRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addItem(dummyInvalidItem)
                    .addUserDetails(dummyInvalidRestApiUserDetails)
                    .addBillingDetails(dummyInvalidShippingDetails)
                    .addShippingDetails(dummyInvalidShippingDetails)
                    .addURLDetails(dummyInvalidUrlDetails)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(17, e.getConstraintViolations()
                    .size());
        }
    }

    @Test
    public void testSuccessfulValidation_PaymentCCRequest() {
        NuveiBaseRequest nuveiRequest = PaymentCCRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmountTwoItems)
                .addSessionToken(dummySessionToken)
                .addItem(dummyValidItem)
                .addItem(dummyValidItem2)
                .addUserDetails(dummyValidRestApiUserDetails)
                .addBillingDetails(dummyValidBillingDetails)
                .addShippingDetails(dummyValidShippingDetails)
                .addOrderId(dummyOrderId)
                .addURLDetails(dummyValidUrlDetails)
                .addTransactionType(Constants.TransactionType.Sale)
                .addCardData(dummyCardData)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_PaymentCCRequest() {

        try {
            NuveiBaseRequest nuveiRequest = PaymentCCRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addItem(dummyInvalidItem)
                    .addUserDetails(dummyInvalidRestApiUserDetails)
                    .addBillingDetails(dummyInvalidShippingDetails)
                    .addShippingDetails(dummyInvalidShippingDetails)
                    .addURLDetails(dummyInvalidUrlDetails)
                    .addCardData(dummyCardData)
                    .addUserPaymentOption(null, "12")
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(17, e.getConstraintViolations()
                    .size());
        }
    }

    @Test
    public void testSuccessfulValidation_RefundTransactionRequest() {

        NuveiBaseRequest nuveiRequest = RefundTransactionRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmount)
                .addRelatedTransactionId(dummyRelatedTransactionId)
                .addAuthCode(dummyAuthCode)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_RefundTransactionRequest() {

        try {
            RefundTransactionRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_SettleTransactionRequest() {

        NuveiBaseRequest nuveiRequest = SettleTransactionRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addAuthCode(dummyAuthCode)
                .addAmount(validAmount)
                .addCurrency(validCurrencyCode)
                .addRelatedTransactionId(dummyRelatedTransactionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_SettleTransactionRequest() {

        try {
            SettleTransactionRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_UpdateOrderRequest() {
        NuveiBaseRequest nuveiRequest = UpdateOrderRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addSessionToken(dummySessionToken)
                .addOrderId(dummyOrderId)
                .addCurrency(validCurrencyCode)
                .addAmount(validAmount)
                .addItem(dummyValidItem)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_UpdateOrderRequest() {

        try {
            NuveiBaseRequest nuveiRequest = UpdateOrderRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .addItem(dummyInvalidItem)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(7, e.getConstraintViolations()
                    .size());
        }
    }

    @Test
    public void testSuccessfulValidation_VoidTransactionRequest() {

        NuveiBaseRequest nuveiRequest = VoidTransactionRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addAuthCode(dummyAuthCode)
                .addAmount(validAmount)
                .addCurrency(validCurrencyCode)
                .addRelatedTransactionId(dummyRelatedTransactionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_VoidTransactionRequest() {

        try {
            VoidTransactionRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(5, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_PayoutRequest() {

        PayoutRequest nuveiRequest = PayoutRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addAmountAndCurrency(validAmount, validCurrencyCode)
                .addComment(dummyComment)
                .addDynamicDescriptor(someDynamicDescriptor)
                .addMerchantDetails(merchantDetails)
                .addSubMethodDetails(subMethodDetails)
                .addUrlDetails(dummyValidUrlDetails)
                .addUserTokenId(dummyUserTokenId)
                .addClientUniqueId(dummyClientUniqueId)
                .addSubMethodDetails(dummySubmethod)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_PayoutRequest() {

        try {
            PayoutRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();

            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(2, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_Verify3dRequest() {
        NuveiBaseRequest nuveiRequest = Verify3dRequest.builder()
                .addSessionToken(dummySessionToken)
                .addMerchantInfo(validMerchantInfo)
                .addClientUniqueId(dummyClientUniqueId)
                .addUserTokenId(dummyUserTokenId)
                .addUserId(dummyUserId)
                .addSubMerchant(subMerchant)
                .addAmount(validAmount)
                .addCurrency(validCurrencyCode)
                .addRelatedTransactionId(dummyRelatedTransactionId)
                .build();

        assertNotNull(nuveiRequest);
    }

    @Test
    public void testSuccessfulValidation_AddUPOCreditCardByTokenRequest() {

        NuveiBaseRequest nuveiRequest = AddUPOCreditCardByTokenRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addCcToken(dummyCcToken)
                .addBrand(dummyBrand)
                .addUniqueCC(dummyUniqueCC)
                .addBin(dummyBin)
                .addLast4Digits(dummyLast4Digits)
                .addCcExpMonth(dummyCCExpMonth)
                .addCCExpYear(dummyCCExpYear)
                .addCcNameOnCard(dummyCCNameOnCard)
                .addBillingAddress(dummyAddress, dummyCity, dummyCountryCode, dummyEMail, dummyFirstName, dummyLastName, dummyPhone, dummyState,
                        dummyZip, dummyBirthDate, dummyCountry, dummyLocale)
                .addLastDepositUse("lastDepositUse")
                .addLastDepositSuccess("lastDepositSuccess")
                .addLastWithdrawalUse("lastWithdrawalUse")
                .addLastWithdrawalSuccess("lastWithdrawalSuccess")
                .addRegistrationDate("registrationDate")
                .addExpiryDate("expiryDate")
                .build();
        assertTrue(nuveiRequest != null);
        assertTrue(nuveiRequest.toString().contains(dummyCcToken));
        assertTrue(nuveiRequest.toString().contains(dummyBrand));
        assertTrue(nuveiRequest.toString().contains(dummyUniqueCC));
        assertTrue(nuveiRequest.toString().contains(dummyLast4Digits));
        assertTrue(nuveiRequest.toString().contains(dummyCCNameOnCard));
    }

    @Test
    public void testFailedValidation_AddUPOCreditCardByTokenRequest() {

        try {
            NuveiBaseRequest nuveiRequest = AddUPOCreditCardByTokenRequest.builder()
                    .addMerchantInfo(invalidMerchantInfo)
                    .build();
        } catch (ConstraintViolationException e) {
            assertEquals(11, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetUserUPOsRequest() {

        NuveiBaseRequest getUserUPOsRequest = GetUserUPOsRequest.builder()
                .addUserTokenId(dummyUserId)
                .addMerchantInfo(validMerchantInfo)
                .build();
        assertTrue(getUserUPOsRequest != null);
    }

    @Test
    public void testFailedValidation_GetUserUPOsRequest() {

        try {
            NuveiBaseRequest getUserUPOsRequest = GetUserUPOsRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_EditUPOCreditCardRequest() {

        NuveiBaseRequest nuveiRequest = EditUPOCreditCardRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addCcExpMonth(dummyCcExpMonth)
                .addCCExpYear(dummyCcExpYear)
                .addUserPaymentOptionId(dummyUserPaymentOptionId)
                .addCcNameOnCard(dummyCcNameOnCard)
                .addBillingAddress(dummyFirstName, dummyLastName, dummyAddress, dummyPhone, dummyZip, dummyCity, dummyCountryCode, dummyState, dummyEMail,
                        dummyLocale, dummyBirthDate, dummyCountry)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_EditUPOCreditCardRequest() {

        commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType.CCARD, 7);
    }

    @Test
    public void testSuccessfulValidation_EditUPOAPMRequest() {

        NuveiBaseRequest nuveiRequest = EditUPOAPMRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addApmData(dummyValidApmData)
                .addUserTokenId(dummyUserId)
                .addUserPaymentOptionId(dummyUserPaymentOptionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_EditUPOAPMRequest() {

        commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType.APM, 5);
    }

    @Test
    public void testSuccessfulValidation_DeleteUPORequest() {

        NuveiBaseRequest nuveiRequest = DeleteUPORequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addUserPaymentOptionId(dummyUserPaymentOptionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_DeleteUPORequest() {

        commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType.DELETE, 4);
    }

    @Test
    public void testSuccessfulValidation_SuspendUPORequest() {

        NuveiBaseRequest nuveiRequest = SuspendUPORequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addUserPaymentOptionId(dummyUserPaymentOptionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_SuspendUPORequest() {

        commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType.SUSPEND, 4);
    }

    @Test
    public void testSuccessfulValidation_EnableUPORequest() {

        NuveiBaseRequest nuveiRequest = EnableUPORequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addUserTokenId(dummyUserId)
                .addUserPaymentOptionId(dummyUserPaymentOptionId)
                .build();
        assertTrue(nuveiRequest != null);
    }

    @Test
    public void testFailedValidation_EnableUPORequest() {

        commonFailedValidationEnableUPORequest(Constants.EditUpoBuilderType.ENABLE, 4);
    }

    @Test
    public void testSuccessfulValidation_GetCardDetailsRequest() {
        CardDetailsRequest getCardDetailsRequest = CardDetailsRequest.builder()
                .addCardNumber(dummyCcCardNumber)
                .addClientUniqueId(dummyClientUniqueId)
                .addMerchantInfo(validMerchantInfo)
                .build();

        assertNotNull(getCardDetailsRequest);
    }

    @Test
    public void testFailedValidation_GetCardDetailsRequest() {
        try {
            CardDetailsRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetDccDetails() {
        DccDetailsRequest dccDetailsRequest = DccDetailsRequest.builder()
                .addCardNumber(dummyCcCardNumber)
                .addClientUniqueId(dummyClientUniqueId)
                .addMerchantInfo(validMerchantInfo)
                .addOriginalAmount("20")
                .addOriginalCurrency("BGN")
                .build();

        assertNotNull(dccDetailsRequest);
    }

    @Test
    public void testFailedValidation_GetDccDetails() {
        try {
            DccDetailsRequest.builder()
                    .addCardNumber(dummyCcCardNumber)
                    .addClientUniqueId(dummyClientUniqueId)
                    .addMerchantInfo(validMerchantInfo)
                    .addOriginalAmount("20")
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidation_GetMcpRates() {
        McpRatesRequest mcpRatesRequest = McpRatesRequest.builder()
                .addClientUniqueId(dummyClientUniqueId)
                .addMerchantInfo(validMerchantInfo)
                .addFromCurrency("BGN")
                .build();

        assertNotNull(mcpRatesRequest);
    }

    @Test
    public void testFailedValidation_GetMcpRates() {
        try {
            McpRatesRequest.builder()
                    .addClientUniqueId(dummyClientUniqueId)
                    .addMerchantInfo(validMerchantInfo)
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }

    @Test
    public void testSuccessfulValidationWithDeprecatedCountryCode_AccountCapture() {
        AccountCaptureRequest accountCaptureRequest = AccountCaptureRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addPaymentMethod("payment_method")
                .addCountryCode("BG")
                .addUserTokenId("userTokenId")
                .addCurrencyCode("BGN")
                .addLanguageCode("en")
                .build();

        assertNotNull(accountCaptureRequest);
    }

    @Test
    public void testSuccessfulValidation_AccountCapture() {
        AccountCaptureRequest accountCaptureRequest = AccountCaptureRequest.builder()
                .addMerchantInfo(validMerchantInfo)
                .addPaymentMethod("payment_method")
                .addCountry("BG")
                .addUserTokenId("userTokenId")
                .addCurrencyCode("BGN")
                .addLanguageCode("en")
                .build();

        assertNotNull(accountCaptureRequest);
    }

    @Test
    public void testFailedValidation_AccountCapture() {
        try {
            AccountCaptureRequest.builder()
                    .addMerchantInfo(validMerchantInfo)
                    .addPaymentMethod("payment_method")
                    .addCountry("BG")
                    .addUserTokenId("userTokenId")
                    .addCurrencyCode("BGN")
                    .build();
            fail(CONSTRAINT_VIOLATION_EXCEPTION_EXPECTED_BUT_OBJECT_CREATION_PASSED_SUCCESSFULLY);
        } catch (ConstraintViolationException e) {
            assertEquals(1, e.getConstraintViolations().size());
        }
    }
}
