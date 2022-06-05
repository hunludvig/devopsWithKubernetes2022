package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import java.io.IOException;

@Controller("status")
public class StatusController {

    @Inject
    private StatusStore status;

    @Get(produces = MediaType.APPLICATION_JSON)
    public Status currentStatus() throws IOException {
        status.update();
        return status.currentStatus();
    }
}
