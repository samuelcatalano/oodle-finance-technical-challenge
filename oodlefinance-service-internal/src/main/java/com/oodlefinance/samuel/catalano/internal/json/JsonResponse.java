package com.oodlefinance.samuel.catalano.internal.json;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class JsonResponse {

    private String message;

}
