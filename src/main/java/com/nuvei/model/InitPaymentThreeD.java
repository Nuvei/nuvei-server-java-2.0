/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.model;

import javax.validation.constraints.Size;

public class InitPaymentThreeD {

    @Size(max = 255, message = "methodNotificationUrl must be up to 255 characters!")
    private String methodNotificationUrl;

    private Acquirer acquirer;

    public String getMethodNotificationUrl() {
        return methodNotificationUrl;
    }

    public void setMethodNotificationUrl(String methodNotificationUrl) {
        this.methodNotificationUrl = methodNotificationUrl;
    }

    public Acquirer getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(Acquirer acquirer) {
        this.acquirer = acquirer;
    }
}
