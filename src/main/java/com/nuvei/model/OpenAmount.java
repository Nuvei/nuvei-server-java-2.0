/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Defines open amount restrictions for order
 */
public class OpenAmount {

    /**
     * Maximum amount allowed for order
     */
    @NotNull
    @Size(max = 12)
    private String max;

    /**
     * Minimum amount allowed for order
     */
    @NotNull
    @Size(max = 12)
    private String min;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
