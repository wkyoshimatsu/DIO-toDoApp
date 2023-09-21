package dev.wky.todolistapp.service;

import dev.wky.todolistapp.model.TodoItem;
import dev.wky.todolistapp.repository.TodoItemRepository;
import dev.wky.todolistapp.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TodoItemService(
        TodoItemRepository todoItemRepository,
        TodoListRepository todoListRepository
) {
    public TodoItem createTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public TodoItem getTodoItem(UUID todoItemId) {
        return todoItemRepository.findById(todoItemId).orElseThrow(
                () -> new IllegalStateException("TodoItem with id " + todoItemId + "does not exist")
        );
    }

    public void deleteTodoItem(UUID todoItemId) {
        todoItemRepository.deleteById(todoItemId);
    }

    public TodoItem updateTodoItem(UUID todoItemId, TodoItem todoItem) {
        TodoItem existingTodoItem = getTodoItem(todoItemId);
        existingTodoItem.setDescription(todoItem.getDescription());
        existingTodoItem.setCompleted(todoItem.isCompleted());
        return todoItemRepository.save(existingTodoItem);
    }

    public TodoItem markTodoItemAsCompleted(UUID todoItemId) {
        TodoItem existingTodoItem = getTodoItem(todoItemId);
        existingTodoItem.setCompleted(true);
        return todoItemRepository.save(existingTodoItem);
    }
}
