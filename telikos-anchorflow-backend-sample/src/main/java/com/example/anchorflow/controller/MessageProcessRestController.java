package com.example.anchorflow.controller;


import com.maersk.telikos.model.Booking;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message-process")
@RequiredArgsConstructor
@Slf4j
public class MessageProcessRestController {

    private final KafkaTemplate<String, Booking> kafkaTemplate;


    @PostMapping("/start")
    public void startMessageProcess(@RequestBody Booking booking){
        log.info("received *******************");
        kafkaTemplate.send("booking-submission-request-topic", booking);
        log.info("***********Booking request Published***********");
    }

}
