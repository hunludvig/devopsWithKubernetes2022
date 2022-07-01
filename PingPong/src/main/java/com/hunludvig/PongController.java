package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

@Controller("/")
public class PongController {

    @Inject
    private RequestCounter counter;

    @Get("pingpong")
    @Produces(MediaType.TEXT_PLAIN)
    public String pong() {
        var value = counter.currentValue();
        counter.increment();
        return String.format("pong %s", value);
    }

    @Get("pongs")
    @Produces(MediaType.TEXT_PLAIN)
    public String pongsCounter() {
        var value = counter.currentValue();
        return value.toString();
    }
}
