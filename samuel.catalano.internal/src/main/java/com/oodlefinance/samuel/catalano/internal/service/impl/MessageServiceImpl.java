package com.oodlefinance.samuel.catalano.internal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.internal.entity.Message;
import com.oodlefinance.samuel.catalano.internal.exception.ServiceException;
import com.oodlefinance.samuel.catalano.internal.repository.MessageRepository;
import com.oodlefinance.samuel.catalano.internal.service.MessageService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

  private static final String MESSAGE_NOT_FOUND = "There is no member found with id: ";

  private final MessageRepository repository;
  private final ModelMapper modelMapper;

  @Autowired
  public MessageServiceImpl(final MessageRepository repository, final ModelMapper modelMapper) {
    this.repository = repository;
    this.modelMapper = modelMapper;
  }

  /**
   * Creates a new Message.
   *
   * @param dto MessageDTO object that holds the message data
   * @return MessageDTO object representing the newly created message
   * @throws ServiceException when an error occurs during message creation
   */
  @Override
  public MessageDTO create(final MessageDTO dto) throws ServiceException {
    var entity = this.modelMapper.map(dto, Message.class);
    try {
      var result = repository.save(entity);
      return this.modelMapper.map(result, MessageDTO.class);
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
   * @throws EntityNotFoundException when message with the specified id cannot be found
   */
  @Override
  public MessageDTO update(final Long id, final MessageDTO dto) throws ServiceException {
    var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE_NOT_FOUND + id));
    modelMapper.map(dto, entity);
    entity.setId(id);

    try {
      var result = repository.save(entity);
      return this.modelMapper.map(result, MessageDTO.class);
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
   * @throws EntityNotFoundException when message with the specified id cannot be found
   */
  @Override
  public MessageDTO findById(final Long id) throws EntityNotFoundException {
    return repository.findById(id)
                     .map(member -> this.modelMapper.map(member, MessageDTO.class))
                     .orElseThrow(() -> new EntityNotFoundException(MESSAGE_NOT_FOUND + id));
  }

  /**
   * Retrieves a list of all messages.
   *
   * @return list of MessageDTO objects representing all messages
   * @throws ServiceException when an error occurs during message retrieval
   */
  @Override
  public List<MessageDTO> findAll() throws ServiceException {
    try {
      final List<MessageDTO> messagesDTO = new ArrayList<>();
      var messages = repository.findAll();
      var messagesList = Streamable.of(messages).toList();

      messagesList.forEach(message -> messagesDTO.add(this.modelMapper.map(message, MessageDTO.class)));

      return messagesDTO;
    } catch (final Exception e) {
      log.error("Error retrieving all existing messages: {}", e.getMessage(), e);
      throw new ServiceException("Error retrieving all existing messages", e);
    }
  }

  /**
   * Deletes a message by its id.
   *
   * @param id identifier of the message to delete
   * @throws EntityNotFoundException when message with the specified id cannot be found
   * @throws DataIntegrityViolationException when an error occurs during message deletion
   */
  @Override
  public void deleteById(final Long id) throws EntityNotFoundException, DataIntegrityViolationException {
    repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE_NOT_FOUND + id));
    try {
      repository.deleteById(id);
    } catch (final DataIntegrityViolationException e) {
      log.error("Error deleting Message with id: " + id + " - " + e.getMessage(), e);
      throw new DataIntegrityViolationException("Error deleting Message with id: " + id + " - " + e.getMessage(), e);
    }
  }
}
