package com.oodlefinance.samuel.catalano.internal.handler;

public record ErrorResponse(String status, String message, Integer code) {
}