package com.oodlefinance.samuel.catalano.internal.model;

import com.oodlefinance.samuel.catalano.internal.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Message extends BaseEntity {

    private Long id;
    private String message;

    public Message() {
        // default
    }

    public Message(final Long id, final String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}