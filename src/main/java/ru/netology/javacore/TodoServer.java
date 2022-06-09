package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    private final int port;
    private final Todos todos;
    private final Gson gson;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
        this.gson = new GsonBuilder()
                .setLenient()
                .create();;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                        BufferedReader in = new BufferedReader(inputStreamReader);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        ) {
                    String inputJson = in.readLine();
                    CommandDto command = gson.fromJson(inputJson, CommandDto.class);

                    if (command == null) {
                        continue;
                    }

                    System.out.println("Request has been received. Command type: " + command.getType() + "; task: " + command.getTask());

                    String response = null;

                    switch (command.getType()) {
                        case ADD:
                            try {
                                todos.addTask(command.getTask());
                            } catch(Exception e) {
                                response = e.getMessage();
                                break;
                            }

                            response = "Задача успешно добавлена";
                            break;
                        case REMOVE:
                            try {
                                todos.removeTask(command.getTask());
                            } catch (Exception e){
                                response = e.getMessage();
                                break;
                            }

                            response = "Задача успешно удалена";
                            break;
                        case GET_ALL:
                            response = todos.getAllTasks();
                            break;
                    }

                    out.write(response + "\n");
                    out.flush();

                    System.out.println("Request has been processed");
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
