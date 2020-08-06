package com.thoughtworks.springbootemployee.integration;


import com.thoughtworks.springbootemployee.model.Todo;
import com.thoughtworks.springbootemployee.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private com.thoughtworks.springbootemployee.repository.TodoRepository todoRepository;

    @Test
    void should_return_todos_when_get_todo_list_given_null() throws Exception {
        //given
        Todo todo = new Todo(1, "test", true);
        Todo savedTodo = todoRepository.save(todo);

        //when then
        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value("test"))
                .andExpect(jsonPath("$[0].status").value(true));
    }

    @Test
    void should_return_added_todo_when_add_todo_given_todo() throws Exception {
        //given
        String todo = "{\n" +
                "    \"id\": 1,\n" +
                "    \"content\": \"test111\",\n" +
                "    \"status\": true\n" +
                "}";

        //when then
        mockMvc.perform(post("/todo")
                .contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("test111"))
                .andExpect(jsonPath("$.status").value(true));
    }
}
