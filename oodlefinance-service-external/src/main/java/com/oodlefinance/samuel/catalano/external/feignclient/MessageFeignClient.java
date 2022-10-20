package com.oodlefinance.samuel.catalano.external.feignclient;

import com.oodlefinance.samuel.catalano.external.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.external.exception.OodleFinanceException;
import com.oodlefinance.samuel.catalano.external.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "oodle-finance-internal-service", path = "/api/message")
public interface MessageFeignClient {

    /**
     * Return a list of {@link Message}.
     * @return a list of Message
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "all", produces = "application/json")
    ResponseEntity<List<MessageDTO>> getAllMessages() throws OodleFinanceException;

    /**
     * Returna a specific message by id.
     * @param id the message id
     * @return a specific message by id
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "{id}", produces = "application/json")
    ResponseEntity<MessageDTO> getMessageById(@PathVariable final Long id) throws OodleFinanceException;

    /**
     * Create a new message.
     * @param message the new message
     * @return a new message
     * @throws OodleFinanceException exception to be launched
     */
    @PostMapping(value = "", produces = "application/json")
    ResponseEntity<MessageDTO> createNewMessage(@RequestBody final Message message) throws OodleFinanceException;

    /**
     * Delete a message by id.
     * @param id the message id
     * @return a message deleted
     * @throws OodleFinanceException exception to be launched
     */
    @DeleteMapping(value = "{id}", produces = "application/json")
    ResponseEntity<String> delete(@PathVariable final Long id) throws OodleFinanceException;
}