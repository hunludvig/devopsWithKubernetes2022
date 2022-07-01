package com.hunludvig;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

@Client
public interface PongFetcher {
    @Get("${micronaut.application.pong-service}")
    @Header(name = HttpHeaders.ACCEPT, value = MediaType.TEXT_PLAIN)
    String fetchPongs();
}
