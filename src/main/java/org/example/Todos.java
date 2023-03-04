package org.example;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Todos {
    private static final int MAX_TASKS = 7;
    private Set<String> tasks;
    private final Stack<Request> requestList = new Stack<>();

    public Todos() {
        this.tasks = new TreeSet<>();
    }

    public void addTask(String task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public void addLastTask(Request request) {
        requestList.add(request);
    }

    public void restoreRequest() {
        if (!requestList.isEmpty()) {
            Request lastRequest = requestList.pop();
            switch (lastRequest.getType()) {
                case "ADD":
                    tasks.remove(lastRequest.getTask());
                    break;
                case "REMOVE":
                    tasks.add(lastRequest.getTask());
                    break;
                default:
                    break;
            }
        }
    }

    public String getAllTasks() {
        StringBuilder tasksString = new StringBuilder();
        for (String s : tasks) {
            tasksString.append(s);
            tasksString.append(" ");
        }
        return tasksString.toString().trim();
    }

    public Set<String> getTasks() {
        return tasks;
    }

    public void setTasks(Set<String> tasks) {
        this.tasks = tasks;
    }

}
