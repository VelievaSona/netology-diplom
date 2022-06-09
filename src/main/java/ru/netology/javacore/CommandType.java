package ru.netology.javacore;

import com.google.gson.annotations.SerializedName;

public enum CommandType {
    @SerializedName("0")
    ADD (0),

    @SerializedName("1")
    REMOVE (1),

    @SerializedName("2")
    GET_ALL (2);

    private final int value;
    public int getValue() {
        return value;
    }

    private CommandType(int value) {
        this.value = value;
    }
}
