/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.response;

import java.util.ArrayList;
import java.util.List;

import com.nuvei.model.CashierPaymentMethodDetails;

/**
 * <p>
 * Request to add Credit/Debit card User Payment Option(UPO) to a User.
 * <p>
 * This goal of this request is to add a credit card UPO for a specific user according to their User Token ID.
 * Once a credit card UPO is added to the user’s list of UPOs, the credit card is displayed in the payment page.
 *
 * @author <a mailto:bozhidarsh@nuvei.com>Bozhidar Shumanov</a>
 * @since 3/21/2017
 */
public class GetUserUPOsResponse extends NuveiResponse {

    private final List<CashierPaymentMethodDetails> paymentMethods = new ArrayList<CashierPaymentMethodDetails>();

    public List<CashierPaymentMethodDetails> getPaymentMethods() {
        return paymentMethods;
    }
}
