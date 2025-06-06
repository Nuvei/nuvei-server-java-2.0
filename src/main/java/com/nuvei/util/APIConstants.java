/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.util;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/17/2017
 */
public interface APIConstants {

    /**
     * Use this enum instead of the String constants below. It provides better readability and easier maintenance.
     */
    enum Environment {
        PRODUCTION_HOST("https://secure.nuvei.com/ppp/"),
        INTEGRATION_HOST("https://ppp-test.nuvei.com/ppp/");

        private String url;

        Environment(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }

    // Pre-configured hosts:
    String PRODUCTION_HOST = Environment.PRODUCTION_HOST.url;
    String INTEGRATION_HOST = Environment.INTEGRATION_HOST.url;

    // API enpoints:

    String GET_SESSION_TOKEN_URL = "api/v1/getSessionToken.do";
    String OPEN_ORDER_URL = "api/v1/openOrder.do";
    String UPDATE_ORDER_URL = "api/v1/updateOrder.do";
    String GET_ORDER_DETAILS_URL = "api/v1/getOrderDetails.do";
    String PAYMENT_CC_URL = "api/v1/paymentCC.do";
    String PAYMENT_APM_URL = "api/v1/paymentAPM.do";
    String AUTHORIZATION_3D_URL = "api/v1/authorization3D.do"; // the same method as "api/v1/dynamic3D.do". The provided two entry points are one method.
    String DYNAMIC_3D_URL = "api/v1/dynamic3D.do"; // the same method as "api/v1/authorization3D.do". The provided two entry points are one method.
    String CARD_TOKENIZATION_URL = "api/v1/cardTokenization.do";
    String ADD_UPO_CREDIT_CARD_BY_TEMP_TOKEN_URL = "api/v1/addUPOCreditCardByTempToken.do";
    String PAYMENT_3D_URL = "api/v1/payment3D.do";
    String SETTLE_TRANSACTION_URL = "api/v1/settleTransaction.do";
    String VOID_TRANSACTION_URL = "api/v1/voidTransaction.do";
    String REFUND_TRANSACTION_URL = "api/v1/refundTransaction.do";
    String ADD_UPO_CREDIT_CARD_URL = "api/v1/addUPOCreditCard.do";
    String ADD_UPO_APM_URL = "api/v1/addUPOAPM.do";
    String GET_MERCHANT_PAYMENT_METHODS_REQUEST_URL = "api/v1/getMerchantPaymentMethods.do";
    String CANCEL_SUBSCRIPTION_REQUEST_URL = "api/v1/cancelSubscription.do";
    String CREATE_SUBSCRIPTION_REQUEST_URL = "api/v1/createSubscription.do";
    String GET_SUBSCRIPTION_LIST_REQUEST_URL = "api/v1/getSubscriptionsList.do";
    String GET_SUBSCRIPTION_PLANS_REQUEST_URL = "api/v1/getSubscriptionPlans.do";
    String PAYOUT_URL = "api/v1/payout.do";
    String CREATE_USER_URL = "api/v1/createUser.do";
    String UPDATE_USER_URL = "api/v1/updateUser.do";
    String GET_USER_DETAILS_URL = "api/v1/getUserDetails.do";
    String ADD_UPO_CREDIT_CARD_BY_TOKEN_URL = "api/v1/addUPOCreditCardByToken.do";
    String GET_USER_UPOS_REQUEST = "api/v1/getUserUPOs.do";
    String EDIT_UPO_CREDIT_CARD_URL = "api/v1/editUPOCC.do";
    String EDIT_UPO_APM_URL = "api/v1/editUPOAPM.do";
    String ENABLE_UPO_URL = "api/v1/enableUPO.do";
    String DELETE_UPO_APM_URL = "api/v1/deleteUPO.do";
    String SUSPEND_UPO_APM_URL = "api/v1/suspendUPO.do";
    String PAYMENT_URL = "api/v1/payment.do";
    String INIT_PAYMENT_URL = "api/v1/initPayment.do";
    String GET_PAYMENT_STATUS_URL = "api/v1/getPaymentStatus.do";
    String VERIFY3D_URL = "api/v1/verify3d.do";
    String AUTHORIZE3D_URL = "api/v1/authorize3d.do";
    String GET_CARD_DETAILS_URL = "api/v1/getCardDetails.do";
    String DCC_DETAILS_URL = "api/v1/getDccDetails.do";
    String MCP_RATES_URL = "api/v1/getMcpRates.do";
    String ACCOUNT_CAPTURE_URL = "api/v1/accountCapture.do";
    String GET_PAYOUT_STATUS_URL = "api/v1/getPayoutStatus.do";

    // Request Headers:

    Header[] REQUEST_HEADERS = new Header[]{new BasicHeader("Content-Type", "application/json"), new BasicHeader("Client-Name",
            APIConstants.class.getPackage()
                    .getImplementationTitle()), new BasicHeader("Client-Version", APIConstants.class.getPackage()
            .getImplementationVersion())};

    // Field limitations:

    short ADDRESS_MAX_LENGTH = 120;
    short ADDRESS_LINE_MAX_LENGTH = 60;
    short FIRST_NAME_MAX_LENGTH = 30;
    short LAST_NAME_MAX_LENGTH = 40;
    short CARD_HOLDER_MAX_LENGTH = 70;
    short CITY_MAX_LENGTH = 30;
    short ZIP_MAX_LENGTH = 10;
    short PHONE_MAX_LENGTH = 18;
    short CREDIT_CARD_MAX_LENGTH = 20;
    short MERCHANT_DESCRIPTOR_PHONE_MAX_LENGTH = 13;
    short MERCHANT_DESCRIPTOR_NAME_MAX_LENGTH = 25;
    short COUNTY_MAX_LENGTH = 255;
    short EMAIL_MAX_LENGTH = 100;

    short APM_IDENTIFICATION_TYPE_MAX_LENGTH =100;
    short SSN_IDENTIFICATION_MAX_LENGTH = 15;

    // field limitations using regular expressions:

    String EXP_YEAR_REGEX = "^$|(?:(20)\\d{2}|\\d{2})$"; // two or four digits
    String EXP_MONTH_REGEX = "^$|(0?[1-9]|1[012])$";
    String DATE_OF_BIRTH_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    String IS_DYNAMIC_3D_REGEX = "^(0|1)$";

    String SDK_JAVA_VERSION = "sdk_java_ver";
}
