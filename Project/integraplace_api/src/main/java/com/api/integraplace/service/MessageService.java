package com.api.integraplace.service;

import com.api.integraplace.entity.EditalEntity;
import com.api.integraplace.entity.MessageEntity;
import com.api.integraplace.form.MassiveMessageForm;
import com.api.integraplace.form.MessageForm;
import com.api.integraplace.repository.EditalRepository;
import com.api.integraplace.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private EditalRepository _EditalRepository;

    @Autowired
    private MessageRepository _MessageRepository;

    public List<MessageEntity> createMassiveMessages(MassiveMessageForm messageList) {
        List<MessageEntity> listResponseMessages = new ArrayList<>();

        if(messageList.getMessageList().isEmpty()) {
            return listResponseMessages;
        }

        for (MessageForm message : messageList.getMessageList()){

            Optional<EditalEntity> editalDB = _EditalRepository.findById(message.getEdital_id());

            if (editalDB.isPresent()){

                MessageEntity messageAux = new MessageEntity();
                messageAux.setContent(message.getContent());
                messageAux.setOrigin(message.getOrigin());
                messageAux.setMessage_date(message.getMessage_date());
                messageAux.setRead(false);
                messageAux.setEdital(editalDB.get());

                try{

                    MessageEntity messageResponse = _MessageRepository.save(messageAux);
                    listResponseMessages.add(messageResponse);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        }

        return listResponseMessages;
    }

    public List<MessageEntity> findAllByEditalId(Long idAux) {
        List<MessageEntity> listResponseMessages = new ArrayList<>();

        if (idAux <= 0){
            return listResponseMessages;
        }

        Optional<EditalEntity> editalDB = _EditalRepository.findById(idAux);

        if (editalDB.isEmpty()){
            return listResponseMessages;
        }

        return _MessageRepository.findByEditalId(editalDB.get());

    }
}
