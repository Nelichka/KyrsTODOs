package org.example;

public class Request {
    private String type;
    private String task;

    public Request(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}

