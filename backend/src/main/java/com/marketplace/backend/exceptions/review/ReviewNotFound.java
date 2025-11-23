package com.marketplace.backend.exceptions.review;

public class ReviewNotFound extends RuntimeException {
    public ReviewNotFound() {
        super("Review not found");
    }
}