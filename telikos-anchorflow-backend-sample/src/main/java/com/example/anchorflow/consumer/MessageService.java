package com.example.anchorflow.consumer;


import com.example.anchorflow.util.VariablesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.telikos.model.Booking;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.message.MessageCorrelationResultDto;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {


    private final RuntimeService runtimeService;
    private static  final String BOOKING = "booking";

    public MessageCorrelationResult correlateMessage(Booking booking, String messageName) {
        try {
            log.info("Consuming message {}", messageName);

            MessageCorrelationBuilder messageCorrelationBuilder = runtimeService.createMessageCorrelation(messageName);

            log.info("************Consuming message {}", messageName);
            ConcurrentHashMap<String, Object> map=new ConcurrentHashMap<>();
            map.put(BOOKING,booking);
            messageCorrelationBuilder.setVariables(map);
//            //if (booking.getDto() != null) {
//                Map<String, Object> variables = VariablesUtil.toVariableMap(booking);
//
//                messageCorrelationBuilder.setVariables(VariablesUtil.toVariableMap(booking));
//
//            //}


            MessageCorrelationResult messageResult = messageCorrelationBuilder.processInstanceBusinessKey(booking.getBookingId())
                    .correlateWithResult();

            String messageResultJson = new ObjectMapper().writeValueAsString(MessageCorrelationResultDto.fromMessageCorrelationResult(messageResult));

            log.info("Correlation successful. Process Instance Id: {}", messageResultJson);
            log.info("Correlation key used: {}", booking.getBookingId());

            return messageResult;
        } catch (MismatchingMessageCorrelationException e) {
            log.error("Issue when correlating the message: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unknown issue occurred", e);
        }
        return null;
    }
}
