package com.oodlefinance.samuel.catalano.internal.repository;

import com.oodlefinance.samuel.catalano.internal.exception.OodleFinanceException;
import com.oodlefinance.samuel.catalano.internal.model.Message;
import com.oodlefinance.samuel.catalano.internal.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageRepository implements BaseRepository<Message> {

    // HashMap simulating the database
    private static final Map<Long, Message> messages = new HashMap<>();

    /**
     * Return a list of {@link Message}.
     * @return a list of Message
     * @throws Exception exception to be launched
     */
    @Override
    public List<Message> findAll() throws Exception {
        return new ArrayList(messages.values());
    }

    /**
     * Returna a specific message by id.
     * @param id the message id
     * @return a specific message by id
     * @throws Exception exception to be launched
     */
    @Override
    public Message findById(final Long id) throws Exception {
        if (messages.get(id) == null) {
            throw new OodleFinanceException("There is no message with this id!");
        }
        return messages.get(id);
    }

    /**
     * Create a new message.
     * @param entity the new message
     * @return a new message
     * @throws Exception exception to be launched
     */
    @Override
    public Message create(final Message entity) throws Exception {
        final Message message = new Message(this.generateLongId(), entity.getMessage());
        messages.put(message.getId(), message);

        return message;
    }

    /**
     * Delete a message by id.
     * @param id the message id
     * @return a message deleted exception to be launched
     * @throws Exception
     */
    @Override
    public void delete(final Long id) throws Exception {
        if (messages.get(id) == null) {
            throw new OodleFinanceException("There is no message with this id!");
        }
        messages.remove(id);
    }

    /**
     * Generate a Long id to simutate a database auto increment.
     * @return generateLongId
     */
    private Long generateLongId() {
        long   leftLimit = 1L;
        long   rightLimit = 150L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}