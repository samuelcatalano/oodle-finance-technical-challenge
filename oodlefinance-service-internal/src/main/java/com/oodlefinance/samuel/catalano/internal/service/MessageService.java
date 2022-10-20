package com.oodlefinance.samuel.catalano.internal.service;

import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.internal.exception.OodleFinanceException;
import com.oodlefinance.samuel.catalano.internal.model.Message;
import com.oodlefinance.samuel.catalano.internal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    /**
     * Return a list of {@link Message}.
     * @return a list of Message
     * @throws OodleFinanceException exception to be launched
     */
    public List<MessageDTO> findAll() throws OodleFinanceException {
        final List<MessageDTO> msgs = new ArrayList<>();
        try {
            List<Message> messages = this.repository.findAll();
            for (Message message : messages) {
                msgs.add(new MessageDTO(message.getId(), message.getMessage()));
            }
            return msgs;
        } catch (Exception e) {
           throw new OodleFinanceException("Error finding all messages", e);
        }
    }

    /**
     * Returna a specific message by id.
     * @param id the message id
     * @return a specific message by id
     * @throws OodleFinanceException exception to be launched
     */
    public MessageDTO findById(final Long id) throws OodleFinanceException {
        try {
            final Message message = this.repository.findById(id);
            return new MessageDTO(message.getId(), message.getMessage());
        } catch (Exception e) {
            throw new OodleFinanceException("Error getting message by id", e);
        }
    }

    /**
     * Create a new message.
     * @param message the new message
     * @return a new message
     * @throws OodleFinanceException exception to be launched
     */
    public MessageDTO create(final Message message) throws OodleFinanceException {
        try {
            final Message response = this.repository.create(message);
            return new MessageDTO(response.getId());
        } catch (Exception e) {
            throw new OodleFinanceException("Error creating new message", e);
        }
    }

    /**
     * Delete a message by id.
     * @param id the message id
     * @return a message deleted
     * @throws OodleFinanceException exception to be launched
     */
    public void delete(final Long id) throws OodleFinanceException {
        try {
            this.repository.delete(id);
        } catch (Exception e) {
            throw new OodleFinanceException("Error deleting a message!", e);
        }
    }
}