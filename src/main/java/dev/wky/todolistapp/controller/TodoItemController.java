package dev.wky.todolistapp.controller;

import dev.wky.todolistapp.model.TodoItem;
import dev.wky.todolistapp.service.TodoItemService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/todo-items")
public record TodoItemController(
        TodoItemService todoItemService
) {
    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemService.createTodoItem(todoItem);
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItem(@PathVariable("id") UUID id) {
        return todoItemService.getTodoItem(id);
    }

    @PutMapping("update/{id}")
    public TodoItem updateTodoItem(@PathVariable("id") UUID id, @RequestBody TodoItem todoItem) {
        return todoItemService.updateTodoItem(id, todoItem);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTodoItem(@PathVariable("id") UUID id) {
        todoItemService.deleteTodoItem(id);
    }
}
