package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.http.scope.RequestScope;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScope
@Controller("hashes")
public class HashesController {

    @Inject
    @Named("persistent")
    private Hash persistent;

    @Inject
    private Hash ephemeral;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String hashes() {
        return String.format("Application %s. Request %s", persistent.get(), ephemeral.get());
    }
}
