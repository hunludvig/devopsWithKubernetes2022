package com.hunludvig;

public record TodoDto(
        Long id,
        String content,
        String status
) { }
