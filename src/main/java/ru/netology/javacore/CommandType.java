package ru.netology.javacore;

import com.google.gson.annotations.SerializedName;

public enum CommandType {
    @SerializedName("ADD")
    ADD (0),

    @SerializedName("REMOVE")
    REMOVE (1);

    private final int value;
    public int getValue() {
        return value;
    }

    private CommandType(int value) {
        this.value = value;
    }
}
