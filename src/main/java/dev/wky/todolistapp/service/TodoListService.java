package dev.wky.todolistapp.service;

import dev.wky.todolistapp.model.TodoList;
import dev.wky.todolistapp.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TodoListService(
        TodoListRepository todoListRepository
) {
    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public TodoList getTodoList(UUID listId) {
        return todoListRepository.findById(listId).orElseThrow(
                () -> new IllegalStateException("TodoList with listId " + listId + "does not exist")
        );
    }


    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }

    public TodoList updateTodoList(UUID listId, TodoList todoList) {
        TodoList existingTodoList = todoListRepository.findById(listId).orElseThrow(
                () -> new IllegalStateException("TodoList with listId " + listId + "does not exist")
        );
        existingTodoList.setTitle(todoList.getTitle());
        return todoListRepository.save(existingTodoList);
    }

    public void deleteTodoList(UUID listId) {
        todoListRepository.deleteById(listId);
    }
}