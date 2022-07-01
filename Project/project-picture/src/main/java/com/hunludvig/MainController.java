package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.io.IOException;

@Controller("/")
public class MainController {

    @Inject
    private PictureProvider pictures;

    @Get("picture")
    @Produces(MediaType.IMAGE_JPEG)
    @ExecuteOn(TaskExecutors.IO)
    public byte[] picOfTheDay() throws IOException {
        return pictures.pictureOfTheDay();
    }
}
