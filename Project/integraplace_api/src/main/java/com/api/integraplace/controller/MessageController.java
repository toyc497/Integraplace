package com.api.integraplace.controller;

import com.api.integraplace.entity.MessageEntity;
import com.api.integraplace.form.MassiveMessageForm;
import com.api.integraplace.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService _MessageService;

    @PostMapping("/save")
    public ResponseEntity<List<MessageEntity>> saveMassiveMessages(@RequestBody MassiveMessageForm messageList){

        return ResponseEntity.status(HttpStatus.CREATED).body(_MessageService.createMassiveMessages(messageList));

    }

    @GetMapping("/all/{Id}")
    public ResponseEntity<List<MessageEntity>> findByEditalId(@PathVariable("Id") Long idAux){

        return ResponseEntity.status(HttpStatus.OK).body(_MessageService.findAllByEditalId(idAux));

    }

}
