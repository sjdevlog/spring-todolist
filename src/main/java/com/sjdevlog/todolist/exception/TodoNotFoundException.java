package com.sjdevlog.todolist.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("존재하지 않는 todo id : " + id);
    }
}
