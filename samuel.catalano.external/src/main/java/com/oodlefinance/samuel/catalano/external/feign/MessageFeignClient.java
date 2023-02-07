package com.oodlefinance.samuel.catalano.external.feign;

import jakarta.validation.Valid;

import com.oodlefinance.samuel.catalano.external.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.external.exception.ServiceException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "oodle-finance-internal", path = "/api/messages")
public interface MessageFeignClient {

  /**
   * Feign Client endpoint for creating a new message.
   * Maps to the create method in the MessageService class.
   *
   * @param messageDTO The message data transfer object (DTO) that contains the message data.
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @PostMapping
  ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody final MessageDTO messageDTO) throws ServiceException;

  /**
   * Feign Client endpoint for updating an existing message.
   * Maps to the update method in the MessageService class.
   *
   * @param id The ID of the message to update.
   * @param messageDTO The message data transfer object (DTO) that contains the updated message data.
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @PutMapping("/{id}")
  ResponseEntity<MessageDTO> updateMessage(@PathVariable final Long id, @Valid @RequestBody final MessageDTO messageDTO) throws ServiceException;

  /**
   * Feign Client endpoint for retrieving a message by its ID.
   * Maps to the findById method in the MessageService class.
   *
   * @param id The ID of the message to retrieve.
   * @throws ServiceException If the message with the specified ID cannot be found.
   */
  @GetMapping("/{id}")
  ResponseEntity<MessageDTO> getMessageById(@PathVariable final Long id) throws ServiceException;

  /**
   * Feign Client endpoint for retrieving all messages.
   * Maps to the findAll method in the MessageService class.
   *
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @GetMapping
  ResponseEntity<List<MessageDTO>> getAllMessages() throws ServiceException;

  /**
   * Feign Client endpoint for deleting a message by its ID.
   * Maps to the deleteById method in the MessageService class.
   *
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteMessage(@PathVariable Long id) throws ServiceException;

}
