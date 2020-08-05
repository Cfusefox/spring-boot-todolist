package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo Todo) {
        return todoService.addTodo(Todo);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable int id) {
        return todoService.deleteTodo(id);
    }

    @PutMapping("/{id}")
    public Todo changeStatus(@PathVariable int id, @RequestBody Todo todo) {
        return todoService.ChangeStatus(id, todo);
    }

}
