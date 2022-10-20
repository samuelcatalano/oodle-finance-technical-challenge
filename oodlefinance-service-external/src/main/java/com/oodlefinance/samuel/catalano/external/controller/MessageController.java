package com.oodlefinance.samuel.catalano.external.controller;

import com.oodlefinance.samuel.catalano.external.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.external.exception.OodleFinanceException;
import com.oodlefinance.samuel.catalano.external.feignclient.MessageFeignClient;
import com.oodlefinance.samuel.catalano.external.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/message")
public class MessageController {

    @Autowired
    private MessageFeignClient messageFeignClient;

    /**
     * Return a list of {@link Message}.
     * @return a list of Message
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "all", produces = "application/json")
    public ResponseEntity<List<MessageDTO>> getAllMessages() throws OodleFinanceException {
        return this.messageFeignClient.getAllMessages();
    }

    /**
     * Returna a specific message by id.
     * @param id the message id
     * @return a specific message by id
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable final Long id) throws OodleFinanceException {
        return this.messageFeignClient.getMessageById(id);
    }

    /**
     * Create a new message.
     * @param message the new message
     * @return a new message
     * @throws OodleFinanceException exception to be launched
     */
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<MessageDTO> createNewMessage(@RequestBody final Message message) throws OodleFinanceException {
        return this.messageFeignClient.createNewMessage(message);
    }

    /**
     * Delete a message by id.
     * @param id the message id
     * @return a message deleted
     * @throws OodleFinanceException exception to be launched
     */
    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable final Long id) throws OodleFinanceException {
        return this.messageFeignClient.delete(id);
    }
}