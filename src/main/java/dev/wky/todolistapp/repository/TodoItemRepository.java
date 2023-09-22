package dev.wky.todolistapp.repository;

import dev.wky.todolistapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoItemRepository extends JpaRepository<TodoItem, UUID>{
}
