package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.RequestAttribute;
import jakarta.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class.getCanonicalName());

    private final Map<Long, TodoDto> todoStore = new HashMap<>();

    @PostConstruct
    public void init() {
        todoStore.put(1L, new TodoDto(1L, "TODO 1"));
        todoStore.put(2L, new TodoDto(2L, "TODO 2"));
    }

    @Get("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> fetchTodos() throws URISyntaxException {
        return todoStore.values();
    }

    @Post("todos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> addTodo(@RequestAttribute("content") final String content) throws URISyntaxException {
        LOG.info("Todo added with content {}", content);
        var id = Collections.max(todoStore.keySet()) + 1;
        todoStore.put(id, new TodoDto(id, content));
        return fetchTodos();
    }
}
