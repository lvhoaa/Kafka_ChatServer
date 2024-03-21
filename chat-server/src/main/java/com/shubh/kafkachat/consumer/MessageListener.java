package com.shubh.kafkachat.consumer;

import com.shubh.kafkachat.constants.KafkaConstants;
import com.shubh.kafkachat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    // LISTENER SIDE -- messages from ChatController TOPIC will comes here 
    // the @KafkaListener annotated the method that will listen for the Kafka queue messages
    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC,groupId = KafkaConstants.GROUP_ID)
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        // convert the message and display to CLIENTS via websocket
        template.convertAndSend("/topic/group", message);
    }
}
