package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
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

    @GetMapping
    public List<Todo> getTodoList() throws NoSuchDataException {
        return todoService.getTodoList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo Todo) throws IllegalOperationException {
        return todoService.addTodo(Todo);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable int id) throws NoSuchDataException {
        return todoService.deleteTodo(id);
    }

    @PutMapping("/{id}")
    public Todo changeStatus(@PathVariable int id, @RequestBody Todo todo) throws IllegalOperationException, NoSuchDataException {
        return todoService.ChangeStatus(id, todo);
    }

}
