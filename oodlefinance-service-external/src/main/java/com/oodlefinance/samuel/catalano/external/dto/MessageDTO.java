package com.oodlefinance.samuel.catalano.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO implements Serializable {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "message")
    private String message;

    public MessageDTO() {
        // default
    }

    public MessageDTO(final Long id) {
        this.id = id;
    }

    public MessageDTO(final Long id, final String message) {
        this.id = id;
        this.message = message;
    }
}