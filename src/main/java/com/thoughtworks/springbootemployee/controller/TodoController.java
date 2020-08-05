package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @GetMapping
    public List<Todo> getTodoList() {
        return null;
    }

    @PostMapping
    public Todo addTodo() {
        return null;
    }

    @DeleteMapping
    public Todo deleteTodo() {
        return null;
    }

    @PutMapping
    public Todo changeStatus() {
        return null;
    }

}
