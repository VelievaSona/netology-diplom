package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.Random;

import static java.lang.reflect.Modifier.TRANSIENT;

public class Client {
    private static Gson gson = new Gson();

    public static char pickRandomChar() {
        String chars = "ABCDEFG";
        return chars.charAt(new Random().nextInt(chars.length()));
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Добавить задачу \"Погулять с собакой\"");
        String response = sendRequest(CommandType.ADD, "Погулять с собакой");
        System.out.println(response);

        System.out.println("Добавить задачу \"Вынести мусор\"");
        response = sendRequest(CommandType.ADD, "Вынести мусор");
        System.out.println(response);

        System.out.println("Добавить задачу \"Вынести мусор\"");
        response = sendRequest(CommandType.ADD, "Вынести мусор");
        System.out.println(response);

        System.out.println("Удалить задачу \"Погулять с собакой\"");
        response = sendRequest(CommandType.REMOVE, "Погулять с собакой");
        System.out.println(response);

        System.out.println("Удалить задачу \"Погулять с собакой\"");
        response = sendRequest(CommandType.REMOVE, "Погулять с собакой");
        System.out.println(response);
    }

    private static String sendRequest(CommandType type, String task) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
        ) {
            CommandDto commandDto = new CommandDto(type, task);
            String commandJson = gson.toJson(commandDto, CommandDto.class);

            out.write(commandJson + "\n");
            out.flush();

            String response = in.readLine();
            return response;
        }
    }
}
