/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request.builder;

import com.nuvei.model.PaymentOption;
import com.nuvei.request.PaymentRequest;

public abstract class PaymentBuilder<T extends PaymentBuilder<T>> extends NuveiPaymentBuilder<T> {

    private PaymentOption paymentOption;
    private Integer isRebilling;
    private String isMoto;
    private boolean autoPayment3D;

    public T addPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
        return (T) this;
    }

    public T addIsRebilling(Integer isRebilling) {
        this.isRebilling = isRebilling;
        return (T) this;
    }

    public T addIsMoto(String isMoto) {
        this.isMoto = isMoto;
        return (T) this;
    }

    public T addAutoPayment3D(Boolean autoPayment3D) {
        this.autoPayment3D = Boolean.TRUE.equals(autoPayment3D);
        return (T) this;
    }

    protected <S extends PaymentRequest> S build(S request) {
        request.setPaymentOption(paymentOption);
        request.setIsRebilling(isRebilling);
        request.setIsMoto(isMoto);
        request.setIsRebilling(isRebilling);
        request.setAutoPayment3D(autoPayment3D);

        return super.build(request);
    }
}
