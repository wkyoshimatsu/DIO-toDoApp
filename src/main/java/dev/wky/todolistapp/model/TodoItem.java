package dev.wky.todolistapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
public class TodoItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID itemId;
  private String description;
  private boolean completed;
  private UUID listId;
}
