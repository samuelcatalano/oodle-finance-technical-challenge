package com.oodlefinance.samuel.catalano.external.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oodlefinance.samuel.catalano.external.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.external.service.impl.MessageServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MessageServiceImpl messageService;

  @Test
  void createMessage_Returns201AndCreatedMessage() throws Exception {
    final MessageDTO messageDTO = MessageDTO.builder().message("Created message").build();
    final MessageDTO createdMessage = new MessageDTO();

    given(messageService.create(messageDTO)).willReturn(createdMessage);

    mockMvc.perform(post("/api/messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(messageDTO)))
        .andExpect(status().isCreated());
  }

  @Test
  void updateMessage_Returns200AndUpdatedMessage() throws Exception {
    final Long id = 1L;
    final MessageDTO messageDTO = MessageDTO.builder().message("Updated message").build();
    final MessageDTO updatedMessage = new MessageDTO();

    given(messageService.update(id, messageDTO)).willReturn(updatedMessage);

    mockMvc.perform(put("/api/messages/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(messageDTO)))
        .andExpect(status().isOk());
  }

  @Test
  void getMessageById_Returns200AndMessage() throws Exception {
    final Long id = 1L;
    final MessageDTO message = MessageDTO.builder().message("Updated message").build();

    given(messageService.findById(id)).willReturn(message);

    mockMvc.perform(get("/api/messages/{id}", id))
        .andExpect(status().isOk());
  }

  @Test
  void getAllMessages_Returns200AndListOfMessages() throws Exception {
    final List<MessageDTO> messages = List.of(
        MessageDTO.builder().message("Message 1").build(),
        MessageDTO.builder().message("Message 2").build());

    given(messageService.findAll()).willReturn(messages);

    mockMvc.perform(get("/api/messages"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void deleteMessage_Returns204() throws Exception {
    final Long id = 1L;

    mockMvc.perform(delete("/api/messages/{id}", id))
        .andExpect(status().isNoContent());
  }

  private String asJsonString(final Object obj) throws Exception {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new Exception(e.getMessage(), e);
    }
  }
}
