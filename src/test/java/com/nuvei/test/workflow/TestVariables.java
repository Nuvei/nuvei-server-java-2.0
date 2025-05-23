/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.test.workflow;

/**
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 3/29/2017
 */
public class TestVariables {

    public static final String merchantSiteId = "23";
    public static final String merchantSiteId_negative = "0000";
    public static final String merchantSiteId_negative2 = "4011";
    public static final String userTokenId = "Тest_0065";
    public static final String userTokenId_negative = "NotExistingTokenId";
    public static final String userPaymentOptionId = "6209106";
    public static final String userPaymentOptionIdCC = "6240006";
    public static final String paymentMethodNeteller = "apmgw_Neteller";
    public static final String paymentMethodName2 = "apmgw_expresscheckout";
    public static final String paymentMethodName3 = "apmgw_WebMoney";
    public static final String firstName = "Sara";
    public static final String lastName = "Brawn";
    public static final String address = "Baker";
    public static final String state = "CA";
    public static final String city = "Darnassus";
    public static final String zip = "CA1234";
    public static final String county = "DRACULANDIA";
    public static final String countryCode = "US";
    public static final String phone = "889214935";
    public static final String locale = "en_US";
    public static final String email = "testthat@mail.com";
    public static final String clientRequestId = "111899";
    public static final String CHF = "MD5";
    public static final String payComSecretKey = "secret";
    public static final String comment = "here comes the comment comment";
    public static final String descriptorMerchantPhone = "descrMechantPhone";
    public static final String descriptorMerchantName = "descrMerchantName";
    public static final String authCode = "001A53";
    public static final String relatedTransactionId = "1000009053";
    public static final String currency = "EUR";
    public static final String amount = "10";
    public static final String sessionToken = "";
    public static final String transactionType = "Sale";
    public static final String name = "item1";
    public static final String quantity = "1";
    public static final String price = "10";
    public static final String paymentMethod = "apmgw_Neteller";
    public static final String nettelerAccount = "454651018446";
    public static final String nettelerSecureId = "270955";
    public static final String account_id = "SkrillTestUser3@nuvei.com";
    public static final String passwordSkrill = "RAc9AimVLF";
    public static final String paymentMethodSkrill = "apmgw_MoneyBookers";
    public static final String paymentMethodPayPall = "apmgw_expresscheckout";
    public static final String cardNumber = "40XXXXXXXXXXXX12";
    public static final String cardHolderName = "Some Name";
    public static final String expirationMonth = "05";
    public static final String expirationYear = "2025";
    public static final String CVV = "123";
    public static final String deviceType = "DESKTOP";
    public static final String deviceName = "nameOf";
    public static final String deviceOS = "Linux";
    public static final String browser = "Opera";
    public static final String ipAddress = "192.195.196.00";
    public static final String orderId = "";
    public static final String address1 = "stre1";
    public static final String country = "DE";
    public static final String successURL = "https://srv-bsf-devppptrunk.gw-4u.com/ppp/defaultSuccess.do";
    public static final String pendingURL = "https://srv-bsf-devppptrunk.gw-4u.com/ppp/defaultPending.do";
    public static final String errorURL = "https://srv-bsf-devppptrunk.gw-4u.com/ppp/defaultCancel.do";
    public static final String clientUniqueId = "UniqueId";
    public static final String cell = "20502233";
    public static final String merchantName = "Stucco";
    public static final String merchantPhone = "420556444789";
    public static final String customField1 = "rosenrot";
    public static final String customField2 = "bla";
    public static final String addendums = "HotelCalifornia";
    public static final String ccTempToken = "";
    public static final String CVV2 = "1";
    public static final String CVV3 = "1111111";
    public static final String userPaymentOptionId_Invalid = "22222222";
    public static final String userPaymentOptionId_Invalid_APM = "6209106";
    public static final String ccUserPaymentOption_suspended = "6212606";
    public static final String ccUserPaymentOption_disabled = "6198506";
    public static final String cardHolderNameInvalid = "5555";
    public static final String cardHolderNameCyrillic = "Доналд";
    public static final String sessionTokenExpired = "7d051160-4337-45f4-b11d-a31aa6df98c9";
    public static final String userPaymentOptionId_suspended = "6211006";
    public static final String userPaymentOptionId_disabled = "6212806";
    public static final String paymentMethodCyrillic = "пеймънт";
    public static final String paymentMethodInvalid = "123!@#qwe";
    public static final String account_idInvalid = "дададада";
    public static final String emailPPInvalid = "дададада";
    public static final String cardNumberPayCom = "5427033424232218";
    public static final String userPaymentOptionIdPayCom = "6219806";
    public static final String cardNumberIsraeli = "12312312";
    public static final String userPaymentOptionIdNotBelonging = "6220906";
    public static final String userPaymentOptionIdNotBelongingAPM = "6218806";
    public static final String userPaymentOptionIdNeteller = "6225006";
    public static final String PaRes = "";
    public static final String dateOfBirth = "1990-01-01";
    public static final String addUPOCreditCardSessionToken = "297f9f04-9bc5-4ecd-a3f9-51a0fe4aebca";
    
    public static final String paymentCCOrderID = "9169329";
    public static final String statusSuccess = "SUCCESS";
    public static final String transactionStatusApproved = "APPROVED";
    public static final String paymentCCTransactionId = "1000045504";
    public static final String transactionTypeAuth = "Auth";
    public static final String getOrderDetailsOrderID = "9169306";
    public static final String getOrderDetailsCurrency = "EUR";
    public static final String getOrderDetailsAmount = "2.0";
    public static final String getorderDetailsOrderCreationDate = "20170301170326";
    public static final String payoutMerchantID = "7777777";
    public static final String payoutMerchantSiteID = "19";
    public static final String payoutUPOID = "162176";
    public static final String payoutInternalRequestD = "7424";
    public static final String addUPOBin = "544308";
    public static final String addUPOBrand = "master_card";
    public static final String addUPOMerchantID = "7228839313016442768";
    public static final String authorization3DCVV2Reply = "3434";
    public static final String authorization3DTransactionID = "1000045200";
    public static final String authorization3DInternalRequestID = "822";
    public static final String voidAuthCode = "0910F8";
    public static final String voidTransactionID = "1000047955";
    public static final String voidEci = "0";
    public static final String voidClientRequestID = "1490270436000";
    
}
