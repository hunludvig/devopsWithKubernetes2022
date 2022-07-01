package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.io.IOException;

@Controller("status")
public class StatusController {

    @Inject
    private StatusStore status;

    @Inject
    private PongStore pongs;

    @Get(produces = MediaType.TEXT_PLAIN)
    @ExecuteOn(TaskExecutors.IO)
    public String currentStatus() throws IOException {
        status.update();
        pongs.update();
        var s = status.currentStatus();
        var c = pongs.currentCounter();
        return String.format(
                """
                %s: %s.
                Ping / Pongs: %s
                """, s.getCreatedAt(), s.getToken(), c);
    }
}
