package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.RequestAttribute;
import jakarta.inject.Inject;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class.getCanonicalName());

    @Inject
    private TodoRepository todos;

    @Get("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> fetchTodos() throws URISyntaxException {
        return todos.findAll().stream()
                .map(t -> new TodoDto(t.getId().longValue(), t.getContent()))
                .collect(Collectors.toList());
    }

    @Post("todos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> addTodo(@RequestAttribute("content") final String content) throws URISyntaxException {
        LOG.info("Todo added with content {}", content);
        var todo = new Todo();
        todo.setContent(content);
        todo.setCreatedAt(ZonedDateTime.now());
        todos.save(todo);
        return fetchTodos();
    }
}
