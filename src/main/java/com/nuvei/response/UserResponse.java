/*
 * Copyright (C) 2007 - 2024 Nuvei International Group Limited.
 */

package com.nuvei.response;

public class UserResponse extends NuveiResponse {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}