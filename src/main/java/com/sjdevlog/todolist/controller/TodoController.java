package com.sjdevlog.todolist.controller;

import com.sjdevlog.todolist.dto.TodoUpdateRequest;
import com.sjdevlog.todolist.domain.Todo;
import com.sjdevlog.todolist.dto.TodoCreateRequest;
import com.sjdevlog.todolist.dto.TodoResponse;
import com.sjdevlog.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //생성
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest request) {
        Todo todo = todoService.create(request.getTitle(), request.getDescription());
        return new TodoResponse(todo);
    }

    // 단건 조회
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TodoResponse getOne(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        return new TodoResponse(todo);
    }

    // 수정
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TodoResponse update(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request
    ) {
        Todo updated = todoService.update(id, request.getTitle(), request.getDescription());
        return new TodoResponse(updated);
    }

    //목록
    @GetMapping
    public List<TodoResponse> list() {
        return todoService.findAll().stream().map(TodoResponse::new).toList();
    }

    //완료/미완료 토글
    @PatchMapping("/{id}/toggle")
    public TodoResponse toggle(@PathVariable Long id) {
        Todo todo = todoService.toggleCompleted(id);
        return new TodoResponse(todo);
    }

    //삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
