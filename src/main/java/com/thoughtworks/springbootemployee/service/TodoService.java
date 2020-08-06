package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodoList() throws NoSuchDataException {
        List<Todo> todos = todoRepository.findAll();
        if(todos.isEmpty()) {
            throw new NoSuchDataException();
        }
        return todos;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo deleteTodo(int id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo != null) {
            todoRepository.deleteById(id);
            return todo;
        }
        return null;
    }

    public Todo ChangeStatus(int id, Todo todo) {
        Todo findTodo = todoRepository.findById(id).orElse(null);
        if(findTodo != null) {
            findTodo.setStatus(todo.getStatus());
            todoRepository.save(findTodo);
            return todo;
        }
        return null;
    }
}
