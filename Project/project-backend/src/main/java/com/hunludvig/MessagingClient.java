package com.hunludvig;

import io.micronaut.messaging.annotation.MessageBody;
import io.micronaut.nats.annotation.NatsClient;
import io.micronaut.nats.annotation.Subject;

@NatsClient
public interface MessagingClient {

    @Subject("${messaging.add-todo-subject}")
    void sendAdd(@MessageBody TodoDto todo);

    @Subject("${messaging.update-todo-subject}")
    void sendUpdate(@MessageBody TodoDto todo);
}
