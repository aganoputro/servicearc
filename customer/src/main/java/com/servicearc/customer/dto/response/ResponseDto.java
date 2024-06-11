package com.servicearc.customer.dto.response;

public record ResponseDto(boolean success, int status, String message, Object response) {
}
