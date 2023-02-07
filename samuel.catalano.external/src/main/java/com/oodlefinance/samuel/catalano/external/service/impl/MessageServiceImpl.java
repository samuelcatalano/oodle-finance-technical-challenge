package com.oodlefinance.samuel.catalano.external.service.impl;

import lombok.extern.slf4j.Slf4j;

import com.oodlefinance.samuel.catalano.external.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.external.exception.ServiceException;
import com.oodlefinance.samuel.catalano.external.feign.MessageFeignClient;
import com.oodlefinance.samuel.catalano.external.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

  private final MessageFeignClient messageFeignClient;

  @Autowired
  public MessageServiceImpl(final MessageFeignClient messageFeignClient) {
    this.messageFeignClient = messageFeignClient;
  }

  /**
   * Creates a new Message.
   *
   * @param dto MessageDTO object that holds the message data
   * @return MessageDTO object representing the newly created message
   * @throws ServiceException when an error occurs during message creation
   */
  public MessageDTO create(final MessageDTO dto) throws ServiceException {
    try {
      return messageFeignClient.createMessage(dto).getBody();
    } catch (final Exception e) {
      log.error("Error persisting a new Message: {}", e.getMessage(), e);
      throw new ServiceException("Error persisting a new Message", e);
    }
  }

  /**
   * Updates an existing Message.
   *
   * @param id identifier of the message to be updated
   * @param dto MessageDTO object that holds the updated message data
   * @return MessageDTO object representing the updated message
   * @throws ServiceException when an error occurs during message update
   */
  public MessageDTO update(final Long id, final MessageDTO dto) throws ServiceException {
    try {
      return messageFeignClient.updateMessage(id, dto).getBody();
    } catch (final Exception e) {
      log.error("Error updating an existing Message: {}", e.getMessage(), e);
      throw new ServiceException("Error updating an existing Message", e);
    }
  }

  /**
   * Retrieves a message by its id.
   *
   * @param id identifier of the message to retrieve
   * @return MessageDTO object representing the retrieved message
   * @throws ServiceException when message with the specified id cannot be found
   */
  public MessageDTO findById(final Long id) throws ServiceException {
    try {
      return messageFeignClient.getMessageById(id).getBody();
    } catch (final Exception e) {
      log.error("Error find Message by id: " + id + " - " + e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  /**
   * Retrieves a list of all messages.
   *
   * @return list of MessageDTO objects representing all messages
   * @throws ServiceException when an error occurs during message retrieval
   */
  public List<MessageDTO> findAll() throws ServiceException {
    try {
      return messageFeignClient.getAllMessages().getBody();
    } catch (final Exception e) {
      log.error("Error retrieving all existing messages: {}", e.getMessage(), e);
      throw new ServiceException("Error retrieving all existing messages", e);
    }
  }

  /**
   * Deletes a message by its id.
   *
   * @param id identifier of the message to delete
   * @throws ServiceException when message with the specified id cannot be found
   */
  public void deleteById(final Long id) throws ServiceException {
    try {
      messageFeignClient.deleteMessage(id);
    } catch (final Exception e) {
      log.error("Error deleting Message with id: " + id + " - " + e.getMessage(), e);
      throw new ServiceException(e);
    }
  }
}
