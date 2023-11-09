/*
 * Copyright (C) 2007 - 2023 Nuvei International Group Limited.
 */

package com.nuvei.response;

/**
 *
 * Interface that all responses working with order must implement.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/17/2017
 */
public interface NuveiOrderResponse {

    /**
     * @return Merchant Order ID to be used as input parameter in update method and payment methods.
     * The parameter passed to define which merchant order to update.
     */
    String getOrderId();

}
