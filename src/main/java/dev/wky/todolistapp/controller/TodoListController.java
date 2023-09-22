package dev.wky.todolistapp.controller;

import dev.wky.todolistapp.model.TodoList;
import dev.wky.todolistapp.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo-lists")
public record TodoListController(
        TodoListService todoListService
) {
    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }

    @GetMapping("/{id}")
    public TodoList getTodoList(@PathVariable("id") UUID id) {
        return todoListService.getTodoList(id);
    }

    @PutMapping("{id}")
    public TodoList updateTodoList(@PathVariable("id") UUID id, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(id, todoList);
    }

    @DeleteMapping("{id}")
    public void deleteTodoList(@PathVariable("id") UUID id) {
        todoListService.deleteTodoList(id);
    }
}
