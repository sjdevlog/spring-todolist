package com.sjdevlog.todolist.service;

import com.sjdevlog.todolist.domain.Todo;
import com.sjdevlog.todolist.repository.TodoRepository;
import com.sjdevlog.todolist.exception.TodoNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title은 비어 있을 수 없습니다.");
        }
        Todo todo = new Todo(title.trim(), description);
        return todoRepository.save(todo);
    }
    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @Transactional
    public Todo update(Long id, String title, String description) {
        Todo todo = getById(id);
        todo.update(title, description);   // 아래 Domain 메서드 추가 필요
        return todo;
    }

    public Todo toggleCompleted(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));

        if (todo.isCompleted()) todo.markIncomplete();
        else todo.markCompleted();;

        return todo;
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }
}
