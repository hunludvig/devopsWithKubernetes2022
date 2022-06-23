package com.hunludvig;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.RequestAttribute;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.web.router.RouteBuilder;
import jakarta.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class.getCanonicalName());

    @Inject
    private PictureProvider pictures;

    @Inject
    private RouteBuilder.UriNamingStrategy uriNamingStrategy;

    @Get("picture")
    @Produces(MediaType.IMAGE_JPEG)
    @ExecuteOn(TaskExecutors.IO)
    public byte[] picOfTheDay() throws IOException {
        return pictures.pictureOfTheDay();
    }

    @Get("/")
    public HttpResponse<String> mainView() throws URISyntaxException {
        return HttpResponse.redirect(new URI(uriNamingStrategy.resolveUri(ViewsController.class)));
    }

    @Post("add_todo")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<String> addTodo(@RequestAttribute("content") final String content) throws URISyntaxException {
        LOG.info("Todo added with content {}", content);
        return mainView();
    }
}
