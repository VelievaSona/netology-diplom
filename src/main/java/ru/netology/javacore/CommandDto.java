package ru.netology.javacore;

public class CommandDto {
    private CommandType type;
    private String task;

    public CommandDto(CommandType type, String task) {
        this.type = type;
        this.task = task;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
