package com.sjdevlog.todolist.service;

import com.sjdevlog.todolist.domain.Todo;
import com.sjdevlog.todolist.repository.TodoRepository;
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

    public Todo toggleCompleted(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 todo id: " + id));

        if (todo.isCompleted()) {
            todo.markIncomplete();
        } else {
            todo.markCompleted();
        }

        return todo;
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 todo id : " + id);
        }
        todoRepository.deleteById(id);
    }
}
