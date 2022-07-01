package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.Collection;

@Controller("status")
public class StatusController {

    @Inject
    private Collection<Store> stores;

    @Inject
    private StatusStore status;

    @Inject
    private PongStore pongs;

    @Inject
    private ConfigStore config;

    @Get(produces = MediaType.TEXT_PLAIN)
    @ExecuteOn(TaskExecutors.IO)
    public String currentStatus() throws IOException {
        for(var store : stores) {
            store.update();
        }
        var s = status.currentStatus();
        var c = pongs.currentCounter();
        var m = config.currentMessage();
        return String.format(
                """
                %s
                %s: %s.
                Ping / Pongs: %s
                """, m, s.getCreatedAt(), s.getToken(), c);
    }
}
