/*
 * Copyright (C) 2007 - 2024 Nuvei Corporation.
 */

package com.nuvei.util;

import com.nuvei.model.UrlDetails;

/**
 *
 * @author <a mailto:antona@nuvei.com>Anton Antonov</a>
 * @since 19.09.2017 г.
 */
public class UrlUtils {

    private UrlUtils() {
    }

    public static UrlDetails createUrlDetails(String failureUrl, String pendingUrl, String successUrl, String notificationUrl) {

        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setFailureUrl(failureUrl);
        urlDetails.setPendingUrl(pendingUrl);
        urlDetails.setSuccessUrl(successUrl);
        urlDetails.setNotificationUrl(notificationUrl);

        return urlDetails;
    }
}
