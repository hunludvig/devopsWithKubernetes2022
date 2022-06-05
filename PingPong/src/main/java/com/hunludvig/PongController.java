package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("pingpong")
public class PongController {

    @Inject
    private RequestCounter counter;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String pong() {
        var value = counter.currentValue();
        counter.increment();
        return String.format("pong %s", value);
    }
}
