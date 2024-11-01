/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.model;

import com.nuvei.util.Constants;
import com.nuvei.util.ValidChecksum;

/**
 * <p>
 * Holder for User's personal details and a "generic" address. Note that a User can have different billing/shipping
 * address per order stored in {@link UserAddress} object.
 *
 * @author <a mailto:nikolad@nuvei.com>Nikola Dichev</a>
 * @since 3/21/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.USER_DETAILS)
public class UserDetails extends CommonUserDetailsInfo {

    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetails{");
        sb.append(super.toString());
        sb.append(", Birthdate='")
                .append(birthdate)
                .append('\'');
        sb.append('}');

        return sb.toString();
    }
}
