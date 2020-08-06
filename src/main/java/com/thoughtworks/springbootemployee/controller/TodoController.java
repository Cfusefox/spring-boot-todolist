package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.dto.TodoRequest;
import com.thoughtworks.springbootemployee.dto.TodoResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.mapper.TodoMapper;
import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    private TodoMapper todoMapper = new TodoMapper();

    @GetMapping
    public List<TodoResponse> getTodoList() throws NoSuchDataException {
        return todoService.getTodoList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody TodoRequest Todo) throws IllegalOperationException {
        return todoService.addTodo(todoMapper.mapTodo(Todo));
    }

    @DeleteMapping("/{id}")
    public TodoResponse deleteTodo(@PathVariable int id) throws NoSuchDataException {
        return todoService.deleteTodo(id);
    }

    @PutMapping("/{id}")
    public TodoResponse changeStatus(@PathVariable int id, @RequestBody TodoRequest todo) throws IllegalOperationException, NoSuchDataException {
        return todoService.ChangeStatus(id, todoMapper.mapTodo(todo));
    }

}
