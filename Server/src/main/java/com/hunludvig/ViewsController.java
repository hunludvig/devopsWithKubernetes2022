package com.hunludvig;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import java.util.List;
import java.util.Map;

@Controller("/views")
public class ViewsController {

    @View("home")
    @Get("/")
    public Map<String, Object> home() {
        return Map.of("todos", List.of(new TodoDto(0L, "TODO 1"), new TodoDto(1L, "TODO 2")));
    }
}
