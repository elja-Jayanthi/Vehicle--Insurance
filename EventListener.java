package com.auto.insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.Optional;

@Component
public class EventListener {

    @Autowired
    CustomerRepository customerRepository;

    @StreamListener(Streams.INPUT)
    public void handle(@Payload CreditOkEvent creditOkEvent) {


        Optional<Customer> optional = customerRepository.findBySsn(creditOkEvent.getSsn());

        if(optional.isPresent()){

            Customer customer = optional.get();

            customer.setStatus("APPROVED");

        }


    }
}