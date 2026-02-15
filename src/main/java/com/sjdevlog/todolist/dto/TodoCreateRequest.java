package com.sjdevlog.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoCreateRequest {
    @NotBlank(message = "title은 비어 있을 수 없습니다.")
    @Size(max = 200, message = "title은 200자 이하여야 합니다.")
    private String title;
    @Size(max = 1000, message = "description은 1000자 이하여야 합니다.")
    private String description;

    public TodoCreateRequest() {}

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
