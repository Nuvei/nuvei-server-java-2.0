/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.test.workflow;

/**
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/28/2017
 */

import com.nuvei.model.Item;
import com.nuvei.model.MerchantInfo;
import com.nuvei.model.SubMerchant;
import com.nuvei.request.*;
import com.nuvei.response.*;
import com.nuvei.test.BaseTest;
import com.nuvei.util.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.nuvei.test.workflow.TestVariables.*;

public class CompleteFunctionalTest extends BaseTest {

    @Test
    public void testSessionTokenRequest() throws IOException {
        GetSessionTokenResponse getSessionTokenResponse = baseMockTest("./mock/request/getSessionToken.json", GetSessionTokenRequest.class);

        Assert.assertTrue(defined(getSessionTokenResponse.getVersion()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, getSessionTokenResponse.getStatus());
    }

    @Test
    public void testGetOrderDetailsRequest() {
        GetOrderDetailsResponse response = baseMockTest("./mock/request/getOrderDetails.json", GetOrderDetailsRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertTrue(defined(response.getCurrency()));
        Assert.assertTrue(response.getAmount() != null && response.getAmount() > 0.0D);
        Assert.assertTrue(response.getItems() != null && !response.getItems()
                .isEmpty());
        Assert.assertTrue(response.getUserDetails() != null);
        Assert.assertTrue(response.getShippingAddress() != null);
        Assert.assertTrue(response.getBillingAddress() != null);
        Assert.assertTrue(response.getMerchantDetails() != null);
        Assert.assertTrue(response.getTransactionCreationDate() != null);
        Assert.assertTrue(response.getOrderCreationDate() != null);
        Assert.assertTrue(response.getOrderStatus() != null);
        Assert.assertTrue(response.getDynamicDescriptor() == null);
        Assert.assertTrue(response.getDeviceDetails() == null);
        Assert.assertTrue(response.getAddendums() == null);
        Assert.assertTrue(response.getTransactionStatus() == null);
        Assert.assertTrue(response.getTransactionId() == null);
        for (Item item : response.getItems()) {
            Assert.assertTrue(validator.validate(item)
                    .size() == 0);
        }

        Assert.assertTrue(response.toString().contains(getOrderDetailsOrderID));
        Assert.assertTrue(response.toString().contains(getOrderDetailsCurrency));
        Assert.assertTrue(response.toString().contains(getOrderDetailsAmount));
        Assert.assertTrue(response.toString().contains(getorderDetailsOrderCreationDate));

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testOpenOrderRequest() {
        OpenOrderResponse response = baseMockTest("./mock/request/openOrder.json", OpenOrderRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testUpdateOrderRequest() {
        UpdateOrderResponse response = baseMockTest("./mock/request/updateOrder.json", UpdateOrderRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testPaymentCCRequest() {
        PaymentCCResponse response = baseMockTest("./mock/request/paymentCC.json", PaymentCCRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertTrue(defined(response.getTransactionId()));
        Assert.assertEquals("APPROVED", response.getTransactionStatus());
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(response.toString().contains(paymentCCOrderID));
        Assert.assertTrue(response.toString().contains(transactionStatusApproved));
        Assert.assertTrue(response.toString().contains(paymentCCTransactionId));
        Assert.assertTrue(response.toString().contains(transactionTypeAuth));
    }

    @Test
    public void testPaymentAPMRequest() {
        PaymentAPMResponse response = baseMockTest("./mock/request/paymentAPM.json", PaymentAPMRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testPayment3DRequest() {
        Payment3DResponse response = baseMockTest("./mock/request/payment3D.json", Payment3DRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testAuthorization3DRequest() {
        Authorization3DResponse response = baseMockTest("./mock/request/authorization3D.json", Authorization3DRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());

        Assert.assertTrue(response.getCVV2Reply() != null);
        Assert.assertTrue(response.getInternalRequestId() != null);
        Assert.assertTrue(response.getTransactionStatus() != null);
        Assert.assertTrue(response.getTransactionId() != null);
        Assert.assertTrue(response.getAcsUrl() == null);
        Assert.assertTrue(response.getPaymentMethodErrorCode() == null);
        Assert.assertTrue(response.getGwErrorReason() == null);

        Assert.assertTrue(response.toString().contains(authorization3DCVV2Reply));
        Assert.assertTrue(response.toString().contains(authorization3DTransactionID));
        Assert.assertTrue(!response.toString().contains(authorization3DInternalRequestID));
    }

    @Test
    public void testAuthorization3DRequestAsDynamic3DRequest() {
        Dynamic3DResponse response = baseMockTest("./mock/request/authorization3D.json", Dynamic3DRequest.class);

        Assert.assertTrue(defined(response.getOrderId()));
        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testAddUPOCreditCardByTempTokenRequest() {
        AddUPOCreditCardByTempTokenResponse response =
                baseMockTest("./mock/request/addUPOCreditCardByTempToken.json", AddUPOCreditCardByTempTokenRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testCardTokenizationRequest() {
        CardTokenizationResponse response = baseMockTest("./mock/request/cardTokenization.json", CardTokenizationRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testAddUpoCreditCardRequest() {
        AddUPOCreditCardResponse response = baseMockTest("./mock/request/addUPOCreditCard.json", AddUPOCreditCardRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(response.getUserPaymentOptionId() != null);
        Assert.assertTrue(response.getCcToken() != null);
        Assert.assertTrue(response.getBrand() != null);
        Assert.assertTrue(response.getUniqueCC() != null);
        Assert.assertTrue(response.getBin() != null);
        Assert.assertTrue(response.getLast4Digits() != null);
        Assert.assertTrue(response.getCardType().isEmpty());

        Assert.assertTrue(response.toString().contains(addUPOMerchantID));
        Assert.assertTrue(response.toString().contains(addUPOCreditCardSessionToken));
        Assert.assertTrue(response.toString().contains(addUPOBin));
        Assert.assertTrue(response.toString().contains(addUPOBrand));

    }

    @Test
    public void testAddUPOAPMRequest() {
        AddUPOAPMResponse response = baseMockTest("./mock/request/addUPOAPM.json", AddUPOAPMRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testSettleTransactionRequest() {
        SettleTransactionResponse response = baseMockTest("./mock/request/settleTransaction.json", SettleTransactionRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());

        Assert.assertTrue(response.getSessionToken() != null);
        Assert.assertTrue(response.getInternalRequestId() != null);
        Assert.assertTrue(response.getVersion() != null);
        Assert.assertTrue(response.getClientRequestId() != null);
        Assert.assertTrue(response.getEci() != null);
        Assert.assertTrue(response.getReason().isEmpty());

        Assert.assertTrue(response.getExternalTransactionId() == null);
        Assert.assertTrue(response.getUserPaymentOptionId() == null);
        Assert.assertTrue(response.getPaymentMethodErrorReason() == null);
        Assert.assertTrue(response.getPaymentMethodErrorCode() == null);
        Assert.assertTrue(response.getPaRequest() == null);
        Assert.assertTrue(response.getRedirectUrl() == null);
    }

    @Test
    public void testVoidTransactionRequest() {
        VoidTransactionResponse response = baseMockTest("./mock/request/voidTransaction.json", VoidTransactionRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());

        Assert.assertTrue(response.getTransactionId() != null);
        Assert.assertTrue(response.getGwExtendedErrorCode() != null);
        Assert.assertTrue(response.getGwErrorCode() != null);
        Assert.assertTrue(response.getAuthCode() != null);
        Assert.assertTrue(response.getMerchantId() != null);
        Assert.assertTrue(response.getMerchantSiteId() != null);

        Assert.assertTrue(response.toString().contains(transactionStatusApproved));
        Assert.assertTrue(response.toString().contains(voidAuthCode));
        Assert.assertTrue(response.toString().contains(voidTransactionID));
        Assert.assertTrue(response.toString().contains(voidEci));
        Assert.assertTrue(response.toString().contains(voidClientRequestID));
    }

    @Test
    public void testRefundTransactionRequest() {
        RefundTransactionResponse response = baseMockTest("./mock/request/refundTransaction.json", RefundTransactionRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testGetMerchantPaymentMethodsRequest() {
        GetMerchantPaymentMethodsResponse response =
                baseMockTest("./mock/request/getMerchantPaymentMethods.json", GetMerchantPaymentMethodsRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testPayoutRequest() {
        PayoutResponse response =
                baseMockTestMethodWithoutSessionToken("./mock/request/payout.json", PayoutRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(!response.getUserPaymentOptionId().trim().isEmpty());
        Assert.assertTrue(!response.getUserTokenId().trim().isEmpty());
        Assert.assertTrue(!response.getClientUniqueId().trim().isEmpty());
        Assert.assertTrue(response.getInternalRequestId() != null);
        Assert.assertTrue(response.getTransactionStatus() == null);
        Assert.assertTrue(response.getPaymentMethodErrorCode() == null);
        Assert.assertTrue(response.getPaymentMethodErrorReason() == null);
        Assert.assertTrue(response.getGwErrorCode() == null);
        Assert.assertTrue(response.getGwErrorReason() == null);
        Assert.assertTrue(response.getPaymentMethodErrorReason() == null);
        Assert.assertTrue(response.getGwExtendedErrorCode() == null);
        Assert.assertTrue(response.getExternalTransactionId() == null);
        Assert.assertTrue(response.getTransactionId() == null);

        Assert.assertTrue(response.toString().contains(payoutMerchantID));
        Assert.assertTrue(response.toString().contains(payoutMerchantSiteID));
        Assert.assertTrue(response.toString().contains(statusSuccess));
        Assert.assertTrue(response.toString().contains(payoutUPOID));
        Assert.assertTrue(response.toString().contains(payoutInternalRequestD));
    }

    @Test
    public void testCreateCashierUserRequest() {
        UserResponse response =
                baseMockTestMethodWithoutSessionToken("mock/request/createUser.json", CreateUserRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(response.getUserId() != 0);
        Assert.assertTrue(response.getInternalRequestId() != 0);
    }

    @Test
    public void testUpdateCashierUserRequest() {
        UserResponse response =
                baseMockTestMethodWithoutSessionToken("mock/request/updateUser.json", UpdateUserRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(response.getUserId() != 0);
        Assert.assertTrue(response.getInternalRequestId() != 0);
    }

    @Test
    public void testGetUserDetailsMethodRequest() {
        GetUserDetailsResponse response =
                baseMockTestMethodWithoutSessionToken("mock/request/getUserDetails.json", GetUserDetailsRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
        Assert.assertTrue(response.getInternalRequestId() != 0);
        Assert.assertTrue(response.getUserDetails() != null);
        Assert.assertTrue(response.getUserDetails().getUserTokenId() != null);
        Assert.assertTrue(!response.getUserDetails().getUserTokenId().isEmpty());
        Assert.assertTrue(response.getUserDetails().getUserId() != null);
    }

    @Test
    public void testAddUPOCreditCardByTokenRequest() {
        AddUPOCreditCardByTokenResponse response =
                baseMockTest("./mock/request/addUPOCreditCardByToken.json", AddUPOCreditCardByTokenRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testGetUserUPOsRequest() {
        GetUserUPOsResponse response = baseMockTest("./mock/request/getUserUPOs.json", GetUserUPOsRequest.class);

        Assert.assertTrue(defined(response.getMerchantId()));
        Assert.assertTrue(defined(response.getMerchantSiteId()));
        Assert.assertTrue(defined(response.getClientRequestId()));
        Assert.assertNotNull(response.getPaymentMethods());
        Assert.assertTrue(!response.getPaymentMethods().isEmpty());
        Assert.assertTrue(defined(response.getPaymentMethods().get(0).getUpoName()));

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testEditUpoCreditCardRequest() {
        EditUPOCreditCardResponse response = baseMockTestMethodWithoutSessionToken("mock/request/editUPOCreditCard.json", EditUPOCreditCardRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testEditUpoAPMRequest() {
        EditUPOAPMResponse response = baseMockTestMethodWithoutSessionToken("mock/request/editUPOAPM.json", EditUPOAPMRequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testEnableUpoRequest() {
        EnableUPOResponse response = baseMockTestMethodWithoutSessionToken("mock/request/basicEditUPO.json", EnableUPORequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }


    @Test
    public void testDeleteUpoRequest() {
        DeleteUPOResponse response = baseMockTestMethodWithoutSessionToken("mock/request/basicEditUPO.json", DeleteUPORequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }


    @Test
    public void testSuspendUpoRequest() {
        SuspendUPOResponse response = baseMockTestMethodWithoutSessionToken("mock/request/basicEditUPO.json", SuspendUPORequest.class);

        Assert.assertEquals(Constants.APIResponseStatus.SUCCESS, response.getStatus());
    }

    @Test
    public void testPaymentRequest() {
        String jsonPath = "mock/request/payment.json";
        NuveiResponse response = baseMockTestMethodWithoutSessionToken(jsonPath, PaymentRequest.class);

        Assert.assertNotNull(response);
        Mockito.verify(nuveiRequestExecutor).executeRequest(Mockito.any(PaymentRequest.class));
    }

    @Test
    public void testInitPaymentRequest() {
        String jsonPath = "mock/request/initPayment.json";
        NuveiResponse response = baseMockTestMethodWithoutSessionToken(jsonPath, InitPaymentRequest.class);

        Assert.assertNotNull(response);
        Mockito.verify(nuveiRequestExecutor).executeRequest(Mockito.any(InitPaymentRequest.class));
    }

    @Test
    public void testGetPaymentStatusRequest() {
        String jsonPath = "mock/request/getPaymentStatus.json";
        NuveiResponse response = baseMockTestMethodWithoutSessionToken(jsonPath, GetPaymentStatusRequest.class);

        Assert.assertNotNull(response);
        Mockito.verify(nuveiRequestExecutor).executeRequest(Mockito.any(GetPaymentStatusRequest.class));
    }

    @Test
    public void testVerify3dRequest() {
        NuveiBaseRequest request = Verify3dRequest.builder()
                .addSessionToken("sessionToken")
                .addMerchantInfo(new MerchantInfo("key", "merchantId", "merchantSiteId", "host", Constants.HashAlgorithm.SHA256))
                .addClientUniqueId("dummyClientUniqueId")
                .addUserTokenId("dummyUserTokenId")
                .addUserId("dummyUserId")
                .addSubMerchant(new SubMerchant())
                .addAmount("100")
                .addCurrency("USD")
                .addRelatedTransactionId("1234567")
                .build();

        NuveiResponse response = baseMockTestMethodWithPrebuiltObjectWithoutSession(request);

        Assert.assertNotNull(response);
        Mockito.verify(nuveiRequestExecutor).executeRequest(Mockito.any(Verify3dRequest.class));
    }
}
