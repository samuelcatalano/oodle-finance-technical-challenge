package com.oodlefinance.samuel.catalano.internal.entity;

import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MessageTest {

  private final ModelMapper mapper = new ModelMapper();
  private MessageDTO messageDTO;

  @BeforeEach
  void setup() {
    messageDTO = MessageDTO.builder()
        .id(1L)
        .message("Any message")
        .build();
  }

  @Test
  void testMessageMapping() throws Exception {
    final Message message = this.mapper.map(messageDTO, Message.class);
    Assertions.assertEquals(message.getId(), message.getId());
    Assertions.assertEquals(message.getMessage(), message.getMessage());
  }
}
