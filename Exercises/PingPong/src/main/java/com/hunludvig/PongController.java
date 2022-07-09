package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

@Controller("/")
public class PongController {

    @Inject
    private PongRepository pongs;

    @Get("pingpong")
    @Produces(MediaType.TEXT_PLAIN)
    public String pong() {
        var value = pongs.currentValue();
        pongs.increment();
        return String.format("pong %s", value);
    }

    @Get("pongs")
    @Produces(MediaType.TEXT_PLAIN)
    public String pongsCounter() {
        var value = pongs.currentValue();
        return String.valueOf(value);
    }

    @Get
    public String imOkay() {
        return "I'm a lumberjack and I'm okay!";
    }
}
