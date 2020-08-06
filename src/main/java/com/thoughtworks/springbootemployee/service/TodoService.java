package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.TodoResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.mapper.TodoMapper;
import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoMapper todoMapper = new TodoMapper();

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> getTodoList() throws NoSuchDataException {
        List<Todo> todos = todoRepository.findAll();
        if(todos.isEmpty()) {
            throw new NoSuchDataException();
        }
        return todos.stream().map(todo -> todoMapper.mapTodoResponse(todo)).collect(Collectors.toList());
    }

    public TodoResponse addTodo(Todo todo) throws IllegalOperationException {
        Todo addedTodo = todoRepository.save(todo);
        if(addedTodo.getId() == 0) {
            throw new IllegalOperationException();
        }
        return todoMapper.mapTodoResponse(addedTodo);
    }

    public TodoResponse deleteTodo(int id) throws NoSuchDataException {
        Todo todo = todoRepository.findById(id).orElse(null);
        assert todo != null;
        if(todo.getId() == 0) {
            throw new NoSuchDataException();
        }

        todoRepository.deleteById(id);
        return todoMapper.mapTodoResponse(todo);
    }

    public TodoResponse ChangeStatus(int id, Todo todo) throws NoSuchDataException, IllegalOperationException {
        if(id != todo.getId()) {
            throw new IllegalOperationException();
        }
        Todo findTodo = todoRepository.findById(id).orElse(null);
        assert findTodo != null;
        int ida = findTodo.getId();
        if(!(findTodo.getId() > 0)) {
            throw  new NoSuchDataException();
        }
        findTodo.setStatus(todo.getStatus());
        todoRepository.save(findTodo);
        return todoMapper.mapTodoResponse(todo);
    }
}
