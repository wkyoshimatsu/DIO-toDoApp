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

    public TodoItem getTodoItem(UUID itemId) {
        return todoItemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("TodoItem with id " + itemId + "does not exist")
        );
    }

    public TodoItem updateTodoItem(UUID itemId, TodoItem todoItem) {
        TodoItem existingTodoItem = getTodoItem(itemId);
        existingTodoItem.setDescription(todoItem.getDescription());
        existingTodoItem.setCompleted(todoItem.isCompleted());
        return todoItemRepository.save(existingTodoItem);
    }

    public TodoItem markTodoItemAsCompleted(UUID itemId) {
        TodoItem existingTodoItem = getTodoItem(itemId);
        existingTodoItem.setCompleted(true);
        return todoItemRepository.save(existingTodoItem);
    }

    public void deleteTodoItem(UUID itemId) {
        todoItemRepository.deleteById(itemId);
    }
}
