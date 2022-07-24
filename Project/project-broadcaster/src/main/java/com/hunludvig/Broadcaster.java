package com.hunludvig;

import io.micronaut.context.annotation.Value;
import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Inject;

@NatsListener
public class Broadcaster {

    @Inject
    private ChatClient chat;

    @Value("${chat-service.source}")
    private String source;

    @Subject(value = "${messaging.add-todo-subject}", queue = "${chat-service.source}")
    void receiveAdd(@MessageBody TodoDto todo) {
        chat.sendMessage(source,
                String.format("New task to do: %s", todo.content()));
    }

    @Subject(value = "${messaging.update-todo-subject}", queue = "${chat-service.source}")
    void receiveUpdate(@MessageBody TodoDto todo) {
        chat.sendMessage(source,
                String.format("Task [%s] is done", todo.content()));
    }
}
