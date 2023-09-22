package dev.wky.todolistapp.service;

import dev.wky.todolistapp.model.TodoItem;
import dev.wky.todolistapp.repository.TodoItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

public class TodoItemServiceTest {
    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_createTodoItem() {
        //given
        TodoItem fakeTodoItem = TodoItem.builder()
                .itemId(UUID.randomUUID())
                .description("fake description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        Mockito.when(todoItemRepository.save(Mockito.any())).thenReturn(fakeTodoItem);

        //when
        TodoItem actual = todoItemService.createTodoItem(fakeTodoItem);

        //then
        Assertions.assertThat(actual).isEqualTo(fakeTodoItem);
    }
    @Test
    void should_getTodoItemById() {
        //given
        UUID fakeId = UUID.randomUUID();
        TodoItem fakeTodoItem = TodoItem.builder()
                .itemId(fakeId)
                .description("fake description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        Mockito.when(todoItemRepository.findById(fakeId)).thenReturn(Optional.of(fakeTodoItem));

        //when
        TodoItem actual = todoItemService.getTodoItem(fakeId);

        // then
        Assertions.assertThat(actual).isEqualTo(fakeTodoItem);
    }

    @Test
    void should_updateTodoItem() {
        //given
        UUID fakeId = UUID.randomUUID();
        TodoItem fakeOldTodoItem = TodoItem.builder()
                .itemId(fakeId)
                .description("fake old description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        TodoItem fakeNewTodoItem = TodoItem.builder()
                .itemId(fakeId)
                .description("fake new description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        Mockito.when(todoItemRepository.findById(fakeId)).thenReturn(Optional.of(fakeOldTodoItem));
        Mockito.when(todoItemRepository.save(Mockito.any())).thenReturn(fakeNewTodoItem);

        // when
        TodoItem actual = todoItemService.updateTodoItem(fakeId, fakeNewTodoItem);

        // then
        Assertions.assertThat(actual).isEqualTo(fakeNewTodoItem);
    }
    @Test
    void should_markTodoItemAsCompleted() {
        //given
        UUID fakeId = UUID.randomUUID();
        TodoItem fakeTodoItem = TodoItem.builder()
                .itemId(fakeId)
                .description("fake description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        Mockito.when(todoItemRepository.findById(fakeId)).thenReturn(Optional.of(fakeTodoItem));
        Mockito.when(todoItemRepository.save(Mockito.any())).thenReturn(fakeTodoItem);

        // when
        TodoItem actual = todoItemService.markTodoItemAsCompleted(fakeId);

        // then
        Assertions.assertThat(actual).isEqualTo(fakeTodoItem);
    }
    @Test
    void should_deleteTodoItem() {
        //given
        UUID fakeId = UUID.randomUUID();
        TodoItem fakeTodoItem = TodoItem.builder()
                .itemId(fakeId)
                .description("fake description")
                .completed(false)
                .listId(UUID.randomUUID())
                .build();
        Mockito.when(todoItemRepository.findById(fakeId)).thenReturn(Optional.of(fakeTodoItem));
        Mockito.doNothing().when(todoItemRepository).deleteById(fakeId);

        // when
        todoItemService.deleteTodoItem(fakeId);

        // then
        Mockito.verify(todoItemRepository, Mockito.times(1)).deleteById(fakeId);
    }
}
