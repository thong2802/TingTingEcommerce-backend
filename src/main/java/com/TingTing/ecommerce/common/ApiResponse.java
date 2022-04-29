package com.TingTing.ecommerce.common;

import java.time.LocalDateTime;

public class ApiResponse {
    private final boolean success;
    private final String messages;

    public ApiResponse(boolean success, String messages) {
        this.success = success;
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessages() {
        return messages;
    }
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
