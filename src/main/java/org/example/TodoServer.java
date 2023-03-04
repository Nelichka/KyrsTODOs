package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private Todos todos;
    private final int port;
    private final Gson gson;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
        gson = new Gson();
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                System.out.println("Starting server at " + port + "...");
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    System.out.println("Сервер запущен!");
                    Request newTask = gson.fromJson(in.readLine(), Request.class);
                    if (newTask.getType().equals("ADD") || newTask.getType().equals("REMOVE")) {
                        todos.addLastTask(newTask);
                    }
                    switch (newTask.getType()) {
                        case "ADD":
                            todos.addTask(newTask.getTask());
                            break;
                        case "REMOVE":
                            todos.removeTask(newTask.getTask());
                            break;
                        case "RESTORE":
                            todos.restoreRequest();
                            break;
                        default:
                            break;
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
