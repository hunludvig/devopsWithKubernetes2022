package com.hunludvig;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class MainController {

    @Get
    public String imOkay() {
        return "I'm a lumberjack and I'm okay!";
    }
}
