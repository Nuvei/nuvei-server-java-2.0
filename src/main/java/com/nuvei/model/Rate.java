

/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.model;

import java.util.List;

public class Rate {

    private String paymentMethod;

    private List<RateByCurrency> ratesByCurrencies;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<RateByCurrency> getRatesByCurrencies() {
        return ratesByCurrencies;
    }

    public void setRatesByCurrencies(List<RateByCurrency> ratesByCurrencies) {
        this.ratesByCurrencies = ratesByCurrencies;
    }
}
