package com.oodlefinance.samuel.catalano.internal.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import com.oodlefinance.samuel.catalano.internal.controller.base.BaseRESTController;
import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.internal.exception.ServiceException;
import com.oodlefinance.samuel.catalano.internal.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController extends BaseRESTController {

  private final MessageService messageService;

  @Autowired
  public MessageController(final MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * REST API endpoint for creating a new message.
   * Maps to the create method in the MessageService class.
   *
   * @param messageDTO The message data transfer object (DTO) that contains the message data.
   * @return A ResponseEntity with the status code 201 (CREATED) and the created message in the response body.
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @PostMapping
  public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody final MessageDTO messageDTO) throws ServiceException {
    try {
      var createdMessage = messageService.create(messageDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    } catch (final Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  /**
   * REST API endpoint for updating an existing message.
   * Maps to the update method in the MessageService class.
   *
   * @param id The ID of the message to update.
   * @param messageDTO The message data transfer object (DTO) that contains the updated message data.
   * @return A ResponseEntity with the status code 200 (OK) and the updated message in the response body.
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @PutMapping("/{id}")
  public ResponseEntity<MessageDTO> updateMessage(@PathVariable final Long id, @Valid @RequestBody final MessageDTO messageDTO) throws ServiceException {
    try {
      var updatedMessage = messageService.update(id, messageDTO);
      return ResponseEntity.ok(updatedMessage);
    } catch (final Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  /**
   * REST API endpoint for retrieving a message by its ID.
   * Maps to the findById method in the MessageService class.
   *
   * @param id The ID of the message to retrieve.
   * @return A ResponseEntity with the status code 200 (OK) and the retrieved message in the response body.
   * @throws EntityNotFoundException If the message with the specified ID cannot be found.
   */
  @GetMapping("/{id}")
  public ResponseEntity<MessageDTO> getMessageById(@PathVariable final Long id) throws EntityNotFoundException {
    try {
      var message = messageService.findById(id);
      return ResponseEntity.ok(message);
    } catch (final Exception e) {
      throw new EntityNotFoundException(e.getMessage(), e);
    }
  }

  /**
   * REST API endpoint for retrieving all messages.
   * Maps to the findAll method in the MessageService class.
   *
   * @return A ResponseEntity with the status code 200 (OK) and a list of all messages in the response body.
   * @throws ServiceException If an error occurs while performing the operation.
   */
  @GetMapping
  public ResponseEntity<List<MessageDTO>> getAllMessages() throws ServiceException {
    try {
      var messages = messageService.findAll();
      return ResponseEntity.ok(messages);
    } catch (final Exception e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  /**
   * REST API endpoint for deleting a message by its ID.
   * Maps to the deleteById method in the MessageService class.
   *
   * @return A ResponseEntity with the status code 204 (No Content) response indicating that the operation was successful.
   * @throws DataIntegrityViolationException If an error occurs while performing the operation.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMessage(@PathVariable Long id) throws DataIntegrityViolationException {
    try {
      messageService.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (final Exception e) {
      throw new DataIntegrityViolationException(e.getMessage(), e);
    }
  }
}
