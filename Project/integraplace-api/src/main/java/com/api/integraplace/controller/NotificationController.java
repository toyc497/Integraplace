package com.api.integraplace.controller;

import com.api.integraplace.entity.NotificationEntity;
import com.api.integraplace.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notification")
public class NotificationController {

    @Autowired
    private NotificationService _NotificationService;

    @GetMapping("/all")
    public ResponseEntity<List<NotificationEntity>> findAllNotifications(){
        return ResponseEntity.status(HttpStatus.OK).body(this._NotificationService.getAllNotifications());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable("id") Long id){
        this._NotificationService.deleteNotification(id);
    }

}
