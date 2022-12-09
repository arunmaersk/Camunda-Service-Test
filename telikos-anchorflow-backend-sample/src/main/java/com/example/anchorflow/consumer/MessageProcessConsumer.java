package com.example.anchorflow.consumer;


import com.maersk.telikos.model.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProcessConsumer {

    private final RuntimeService runtimeService;
    private final MessageService messageService;
    private final static String MESSAGE_START = "StartBookingProcess";
    private final static String MESSAGE_INTERMEDIATE = "MessageIntermediate";

    private final static String CAPTURE_MESSAGE_INTERMEDIATE = "ResumeCapture";

    private final static String VALIDATE_MESSAGE_INTERMEDIATE = "ResumeValidate";

    private final static String CHECK_WITH_FULFILLMENT_MESSAGE_INTERMEDIATE = "ResumeCheckWithFulfillment";

    private final static String RECORD_BOOKING_MESSAGE_INTERMEDIATE = "ResumeRecordBooking";


    @KafkaListener(topics = "booking-submission-request-topic")
    public void startMessageProcess(Booking booking){
        System.out.println("********At consumer ***********");
        messageService.correlateMessage(booking, MESSAGE_START);
    }


    @KafkaListener(topics = "capture-topic")
    public void listenCapture(Booking booking){
        messageService.correlateMessage(booking, CAPTURE_MESSAGE_INTERMEDIATE);
    }

    @KafkaListener(topics = "validate-response-topic")
    public void listenValidate(Booking booking){
        messageService.correlateMessage(booking, VALIDATE_MESSAGE_INTERMEDIATE);
    }


    @KafkaListener(topics = "fulfilment-response-topic")
    public void listenCheckWithFulfillment(Booking booking){
        messageService.correlateMessage(booking, CHECK_WITH_FULFILLMENT_MESSAGE_INTERMEDIATE);
    }

/*    @KafkaListener(topics = "record-booking-response-topic")
    public void listenRecordBooking(Booking booking){
        messageService.correlateMessage(booking, RECORD_BOOKING_MESSAGE_INTERMEDIATE);
    }*/
}
