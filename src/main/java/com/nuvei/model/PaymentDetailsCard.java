

/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.model;

public class PaymentDetailsCard {

    private String uniqueCC;

    private PaymentDetailsThreeD threeD;

    public String getUniqueCC() {
        return uniqueCC;
    }

    public void setUniqueCC(String uniqueCC) {
        this.uniqueCC = uniqueCC;
    }

    public PaymentDetailsThreeD getThreeD() {
        return threeD;
    }

    public void setThreeD(PaymentDetailsThreeD threeD) {
        this.threeD = threeD;
    }
}
