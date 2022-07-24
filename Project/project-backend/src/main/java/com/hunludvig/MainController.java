package com.hunludvig;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.RequestAttribute;
import jakarta.inject.Inject;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class.getCanonicalName());

    @Inject
    private TodoRepository todos;

    @Inject
    private MessagingClient messaging;

    @Get("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> fetchTodos() {
        return todos.findAll().stream()
                .map(TodoDto::fromTodo)
                .collect(Collectors.toList());
    }

    @Post("todos")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TodoDto> addTodo(@RequestAttribute("content") final String content) {
        try {
            var todo = new Todo();
            todo.setContent(content);
            todo.setCreatedAt(ZonedDateTime.now());
            todos.save(todo);
            messaging.sendAdd(TodoDto.fromTodo(todo));
            LOG.info("Todo added with content {}", content);
            return fetchTodos();
        } catch(RuntimeException e) {
            LOG.error("Failed to add todo of content {}", content, e);
            throw e;
        }
    }

    @Put("todos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Collection<TodoDto> updateTodo(final BigInteger id) {
        var todo = todos.findById(id).orElseThrow();
        switch(todo.getStatus()) {
            case TODO -> {
                todo.setStatus(Todo.Status.DONE);
                todos.save(todo);
                messaging.sendUpdate(TodoDto.fromTodo(todo));
                LOG.info("Todo id {} was updated done", id);
            }
            default -> LOG.info("Todo id {} is already done", id);
        }
        return fetchTodos();
    }

    @Get
    public String imOkay() {
        return "I'm a lumberjack and I'm okay!";
    }
}
