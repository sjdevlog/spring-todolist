package com.sjdevlog.todolist.controller;

import com.sjdevlog.todolist.domain.Todo;
import com.sjdevlog.todolist.dto.TodoCreateRequest;
import com.sjdevlog.todolist.dto.TodoResponse;
import com.sjdevlog.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = TodoService;
    }

    //생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        Todo todo = todoService.create(request.getTitle(), request.getDescription());
        return new TodoResponse(todo);
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
