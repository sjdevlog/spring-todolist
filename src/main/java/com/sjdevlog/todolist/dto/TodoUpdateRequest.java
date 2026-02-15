package com.sjdevlog.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoUpdateRequest {

    @NotBlank(message = "title은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "title은 100자를 초과할 수 없습니다.")
    private String title;

    @Size(max = 1000, message = "description은 1000자를 초과할 수 없습니다.")
    private String description;

    public TodoUpdateRequest() {}

    public TodoUpdateRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}

