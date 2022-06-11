package ru.netology.javacore;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTests {
    @Test
    public void testAddTasks() {
        Todos todos = new Todos();

        String task1 = "B";
        todos.addTask(task1);
        assertEquals(todos.getAllTasks(), task1);

        String task2 = "A";
        todos.addTask(task2);
        assertEquals(todos.getAllTasks(), task2 + " " + task1);
    }

    @Test
    public void testAddTaskIdempotent() {
        Todos todos = new Todos();

        todos.addTask("Дубликат");
        assertEquals(todos.getAllTasks(), "Дубликат");

        // Проверяем идемпотентость при повторном добавлении задачи
        todos.addTask("Дубликат");
        assertEquals(todos.getAllTasks(), "Дубликат");
    }

    @Test
    public void testRemoveTaskIdempotent() {
        Todos todos = new Todos();

        todos.addTask("Один");
        todos.addTask("Два");

        todos.removeTask("Один");
        assertEquals(todos.getAllTasks(), "Два");

        // Проверяем идемпотентость при повторном удалении задачи
        todos.removeTask("Один");
        assertEquals(todos.getAllTasks(), "Два");
    }

    @Test
    public void testGetAllTasksWithoutTasks() {
        Todos todos = new Todos();
        assertEquals(todos.getAllTasks(), "");
    }
}
