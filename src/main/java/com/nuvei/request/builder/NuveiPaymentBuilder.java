/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.request.builder;

import com.nuvei.request.NuveiPaymentRequest;
import com.nuvei.util.Constants;

public abstract class NuveiPaymentBuilder<T extends NuveiPaymentBuilder<T>> extends NuveiOrderBuilder<T> {

    private String customSiteName;
    private String productId;
    private String customData;
    private String relatedTransactionId;
    private Constants.TransactionType transactionType;

    /**
     * The method is used to ad to the builder custom site name which will overwrite the one of the merchant site.
     *
     * @param customSiteName
     * @return this object
     */
    public T addCustomSiteName(String customSiteName) {
        this.customSiteName = customSiteName;
        return (T) this;
    }

    /**
     * Adds product id to request builder.
     *
     * @param productId
     * @return this object
     */
    public T addProductId(String productId) {
        this.productId = productId;
        return (T) this;
    }

    /**
     * Adds custom data to request builder.
     *
     * @param customData
     * @return this object
     */
    public T addCustomData(String customData) {
        this.customData = customData;
        return (T) this;
    }

    /**
     * Adds relatedTransactionId data to request builder.
     *
     * @param relatedTransactionId
     * @return this object
     */
    public T addRelatedTransactionId(String relatedTransactionId) {
        this.relatedTransactionId = relatedTransactionId;
        return (T) this;
    }

    /**
     * Adds transaction type to the request.
     *
     * @param transactionType the type of the transaction. Possible types: {@link com.nuvei.util.Constants.TransactionType}
     * @return this object
     */
    public T addTransactionType(Constants.TransactionType transactionType) {
        this.transactionType = transactionType;
        return (T) this;
    }

    protected <S extends NuveiPaymentRequest> S build(S request) {
        super.build(request);

        request.setTransactionType(transactionType);
        request.setCustomSiteName(customSiteName);
        request.setProductId(productId);
        request.setCustomData(customData);
        request.setRelatedTransactionId(relatedTransactionId);

        return request;
    }
}
