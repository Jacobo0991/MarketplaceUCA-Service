package com.marketplace.backend.exceptions.user;

public class EmailNotVerify extends RuntimeException {
    public EmailNotVerify() {
        super("Error while verifying email");
    }
}
