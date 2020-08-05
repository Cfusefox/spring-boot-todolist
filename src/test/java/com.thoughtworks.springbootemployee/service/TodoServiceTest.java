package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TodoServiceTest {

    @Test
    void should_return_todo_list_when_get_todo_list_given_null() {
        //given
        List<Todo> todos = Arrays.asList(
           new Todo(1, "test1", true),
           new Todo(2, "test2", false)
        );
        TodoRepository mockTodoReposition = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoReposition);
        given(mockTodoReposition.findAll()).willReturn(todos);

        //when
        List<Todo> todoList = todoService.getTodoList();

        //then
        assertEquals(todos, todoList);
    }

    @Test
    void should_return_added_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo(1, "test", false);
        TodoRepository mockTodoReposition = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoReposition);
        given(mockTodoReposition.save(todo)).willReturn(todo);

        //when
        Todo addedTodo = todoService.addTodo(todo);

        //then
        assertEquals(todo, addedTodo);
    }

    @Test
    void should_return_deleted_todo_when_delete_todo_given_id() {
        //given
        Todo todo = new Todo(1, "test", false);
        TodoRepository mockTodoReposition = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoReposition);
        given(mockTodoReposition.findById(1)).willReturn(java.util.Optional.of(todo));

        //when
        todoService.deleteTodo(1);

        //then
        Mockito.verify(mockTodoReposition).deleteById(1);
    }

    @Test
    void should_return_changed_todo_when_change_status_given_id_and_todo() {
        //given
        Todo todo = new Todo(1, "test", false);
        TodoRepository mockTodoReposition = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoReposition);
        given(mockTodoReposition.findById(1)).willReturn(java.util.Optional.of(todo));

        //when
        Todo changedTodo = todoService.ChangeStatus(1, new Todo(1, "test", true));

        //then
        assertEquals(true, changedTodo.getStatus());
    }
}
