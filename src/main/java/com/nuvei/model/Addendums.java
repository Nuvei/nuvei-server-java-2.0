/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.model;

import javax.validation.Valid;

/**
 * <p>
 * Container for additional domain specific payment info such as Airplane tickets data,
 * country's law specific payment data, etc.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 2/14/2017
 */
public class Addendums {

    @Valid
    private AddendumsLocalPayment localPayment;

    @Valid
    private AddendumsCardPresentPointOfSale cardPresentPointOfSale;

    @Valid
    private AddendumsAirlines airlines;

    private RiskInfo risk;

    public AddendumsLocalPayment getLocalPayment() {
        return localPayment;
    }

    public void setLocalPayment(AddendumsLocalPayment localPayment) {
        this.localPayment = localPayment;
    }

    public AddendumsCardPresentPointOfSale getCardPresentPointOfSale() {
        return cardPresentPointOfSale;
    }

    public void setCardPresentPointOfSale(AddendumsCardPresentPointOfSale cardPresentPointOfSale) {
        this.cardPresentPointOfSale = cardPresentPointOfSale;
    }

    public AddendumsAirlines getAirlines() {
        return airlines;
    }

    public void setAirlines(AddendumsAirlines airlines) {
        this.airlines = airlines;
    }

    public RiskInfo getRisk() {
        return risk;
    }

    public void setRisk(RiskInfo risk) {
        this.risk = risk;
    }
}
