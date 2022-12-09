package com.example.anchorflow.delegate;

import com.maersk.telikos.model.Booking;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FullfillmentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {



        Booking booking = (Booking) delegateExecution.getVariable("booking");
        if(Integer.parseInt(String.valueOf(booking.getBookingId().charAt(booking.getBookingId().length()-1))) % 2 != 0)
            throw new BpmnError("error-simulate");


    }
}
