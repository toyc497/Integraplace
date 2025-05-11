package com.api.integraplace.service;

import com.api.integraplace.entity.NotificationEntity;
import com.api.integraplace.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository _NotificationRepository;

    public List<NotificationEntity> getAllNotifications() {
        return this._NotificationRepository.findAll();
    }

    public void deleteNotification(Long id) {

        this._NotificationRepository.deleteById(id);

    }
}
