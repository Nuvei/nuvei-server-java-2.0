

/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.model;

public class PaymentDetailsPaymentOption {

    private String userPaymentOptionId;

    private PaymentDetailsCard card;

    private PaymentDetailsAlternativePaymentMethod alternativePaymentMethod;

    public String getUserPaymentOptionId() {
        return userPaymentOptionId;
    }

    public void setUserPaymentOptionId(String userPaymentOptionId) {
        this.userPaymentOptionId = userPaymentOptionId;
    }

    public PaymentDetailsCard getCard() {
        return card;
    }

    public void setCard(PaymentDetailsCard card) {
        this.card = card;
    }

    public PaymentDetailsAlternativePaymentMethod getAlternativePaymentMethod() {
        return alternativePaymentMethod;
    }

    public void setAlternativePaymentMethod(PaymentDetailsAlternativePaymentMethod alternativePaymentMethod) {
        this.alternativePaymentMethod = alternativePaymentMethod;
    }
}
