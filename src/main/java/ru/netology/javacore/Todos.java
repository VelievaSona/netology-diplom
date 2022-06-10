package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private final ArrayList<String> tasks;

    public Todos() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (task == null || task.isEmpty()) {
            throw new IllegalArgumentException("Task is empty");
        }

        if (tasks.contains(task)) {
            throw new IllegalArgumentException("Такая задача уже была добавлена");
        }

        tasks.add(task);
    }

    public void removeTask(String task) {
        if (task == null || task.isEmpty()) {
            throw new IllegalArgumentException("Task is empty");
        }

        if (!tasks.contains(task)) {
            throw new NoSuchElementException("Задача не найдена");
        }

        tasks.remove(task);
    }

    public String getAllTasks() {
        return tasks.stream().reduce((s1, s2) -> s1 + " " + s2).orElse("");
    }
}
