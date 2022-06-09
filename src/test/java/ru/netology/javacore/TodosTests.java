package ru.netology.javacore;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTests {
    @Test
    public void testAddDuplicateTask() {
        Todos todos = new Todos();
        todos.addTask("Дубликат");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> todos.addTask("Дубликат"));
        assertEquals(exception.getMessage(), "Такая задача уже была добавлена");
    }

    @Test
    public void testRemoveInvalidTask() {
        Todos todos = new Todos();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> todos.removeTask("Task"));
        assertEquals(exception.getMessage(), "Задача не найдена");
    }

    @Test
    public void testGetAllTasksWithTwoTasks() {
        Todos todos = new Todos();

        String task1 = "Вынести мусор";
        String task2 = "Погулять с собакой";

        todos.addTask(task1);
        todos.addTask(task2);

        assertEquals(todos.getAllTasks(), task1 + " " + task2);
    }

    @Test
    public void testGetAllTasksWithOneTask() {
        Todos todos = new Todos();

        String task1 = "Вынести мусор";

        todos.addTask(task1);

        assertEquals(todos.getAllTasks(), task1);
    }

    @Test
    public void testGetAllTasksWithoutTasks() {
        Todos todos = new Todos();
        assertEquals(todos.getAllTasks(), "");
    }
}
