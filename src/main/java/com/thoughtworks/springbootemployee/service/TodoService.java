package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
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

    public Todo addTodo(Todo todo) throws IllegalOperationException {
        Todo addedTodo = todoRepository.save(todo);
        if(addedTodo.getId() == 0) {
            throw new IllegalOperationException();
        }
        return addedTodo;
    }

    public Todo deleteTodo(int id) throws NoSuchDataException {
        Todo todo = todoRepository.findById(id).orElse(null);
        assert todo != null;
        if(todo.getId() == 0) {
            throw new NoSuchDataException();
        }

        todoRepository.deleteById(id);
        return todo;
    }

    public Todo ChangeStatus(int id, Todo todo) throws NoSuchDataException, IllegalOperationException {
        if(id != todo.getId()) {
            throw new IllegalOperationException();
        }
        Todo findTodo = todoRepository.findById(id).orElse(null);
        assert findTodo != null;
        if(findTodo.getId() == 0) {
            throw  new NoSuchDataException();
        }
        findTodo.setStatus(todo.getStatus());
        todoRepository.save(findTodo);
        return todo;
    }
}
