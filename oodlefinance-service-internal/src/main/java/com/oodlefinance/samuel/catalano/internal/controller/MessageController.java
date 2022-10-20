package com.oodlefinance.samuel.catalano.internal.controller;

import com.google.gson.Gson;
import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.internal.exception.OodleFinanceException;
import com.oodlefinance.samuel.catalano.internal.json.JsonResponse;
import com.oodlefinance.samuel.catalano.internal.model.Message;
import com.oodlefinance.samuel.catalano.internal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController {

    @Autowired
    private MessageService service;

    /**
     * Return a list of {@link Message}.
     * @return a list of Message
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "all", produces = "application/json")
    public ResponseEntity<List<MessageDTO>> getAllMessages() throws OodleFinanceException {
        return ResponseEntity.ok(this.service.findAll());
    }

    /**
     * Returna a specific message by id.
     * @param id the message id
     * @return a specific message by id
     * @throws OodleFinanceException exception to be launched
     */
    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable final Long id) throws OodleFinanceException {
        return ResponseEntity.ok(this.service.findById(id));
    }

    /**
     * Create a new message.
     * @param message the new message
     * @return a new message
     * @throws OodleFinanceException exception to be launched
     */
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<MessageDTO> createNewMessage(@RequestBody final Message message) throws OodleFinanceException {
        MessageDTO dto = this.service.create(message);
        return ResponseEntity.ok(dto);
    }

    /**
     * Delete a message by id.
     * @param id the message id
     * @return a message deleted
     * @throws OodleFinanceException exception to be launched
     */
    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable final Long id) throws OodleFinanceException {
        final Gson gson = new Gson();
        try {
            this.service.delete(id);
            final JsonResponse jsonResponse = JsonResponse
                    .builder()
                    .message("The message has been deleted!")
                    .build();

            final String message = gson.toJson(jsonResponse, JsonResponse.class);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            throw new OodleFinanceException("Error creating new message", e);
        }
    }
}