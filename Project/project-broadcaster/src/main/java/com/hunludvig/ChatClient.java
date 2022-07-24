package com.hunludvig;

import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client
public interface ChatClient {
    @Post("${chat-service.url}")
    @Header(name = "X-API-KEY", value = "${chat-service.api-key}")
    void sendMessage(String user, String message);
}
