package com.servicearc.notification.controller;

import com.servicearc.notification.dto.request.NotificationRequest;
import com.servicearc.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public void sendNotification(NotificationRequest notificationRequest){
        log.info("Sending notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
