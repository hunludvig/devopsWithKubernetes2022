package com.hunludvig;

public record TodoDto(
        Long id,
        String content,
        String status
) {
    public static TodoDto fromTodo(Todo t) {
        return new TodoDto(
            t.getId().longValue(),
            t.getContent(),
            t.getStatus().name());
    }
}
