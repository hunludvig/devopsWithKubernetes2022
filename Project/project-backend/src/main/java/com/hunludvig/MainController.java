package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.RequestAttribute;
import jakarta.inject.Inject;
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
    public Collection<TodoDto> fetchTodos() {
        return todos.findAll().stream()
                .map(t -> new TodoDto(t.getId().longValue(), t.getContent()))
                .collect(Collectors.toList());
    }

    @Post("todos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> addTodo(@RequestAttribute("content") final String content)  {
        try {
            var todo = new Todo();
            todo.setContent(content);
            todo.setCreatedAt(ZonedDateTime.now());
            todos.save(todo);
            LOG.info("Todo added with content {}", content);
            return fetchTodos();
        } catch(RuntimeException e) {
            LOG.error("Failed to add todo of content {}", content, e);
            throw e;
        }
    }

    @Get
    public String imOkay() {
        return "I'm a lumberjack and I'm okay!";
    }
}
