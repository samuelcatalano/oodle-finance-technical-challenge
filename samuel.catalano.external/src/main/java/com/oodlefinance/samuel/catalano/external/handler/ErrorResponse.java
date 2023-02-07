package com.oodlefinance.samuel.catalano.external.handler;

public record ErrorResponse(String status, String message, Integer code) {
}