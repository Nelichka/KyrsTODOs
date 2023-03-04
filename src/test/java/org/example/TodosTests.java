package org.example;

import org.junit.jupiter.api.*;

public class TodosTests {

    Todos todos;

    @BeforeEach
    void initializeData() {
        todos = new Todos();
    }

    @AfterEach
    void resetData() {
        todos = null;
    }

    @Test
    @DisplayName("Тестирование: Добавление новых задач")
    void addTask() {
        todos.addTask("Задача №1");
        todos.addTask("Задача №2");
        todos.addTask("Задача №3");

        Assertions.assertEquals("Задача №1 Задача №2 Задача №3", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование: Количество задач не превышает их максимальное значение")
    void addMoreTasks() {
        for (int i = 0; i < 10; i++) {
            todos.addTask("Задача №" + i);
        }

        Assertions.assertEquals(7, todos.getTasks().size());
    }

    @Test
    @DisplayName("Тестирование: Удаление задачи")
    void removeTask() {
        todos.addTask("Задача №1");
        todos.addTask("Задача №2");
        todos.addTask("Задача №3");
        todos.removeTask("Задача №1");

        Assertions.assertEquals("Задача №2 Задача №3", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование: Вывод задач в отсортированном лексикографическом (словарном) порядке")
    void getAllTasks() {
        todos.addTask("Задача №3");
        todos.addTask("Задача №1");
        todos.addTask("Задача №2");

        Assertions.assertEquals("Задача №1 Задача №2 Задача №3", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование: Отмена последних не-RESTORE-операций")
    void restoreRequest() {
        todos.addTask("Задача №1");
        Request request1 = new Request("ADD", "Задача №1");
        todos.addLastTask(request1);
        todos.addTask("Задача №2");
        Request request2 = new Request("ADD", "Задача №2");
        todos.addLastTask(request2);
        todos.addTask("Задача №3");
        Request request3 = new Request("ADD", "Задача №3");
        todos.addLastTask(request3);
        todos.addTask("Задача №4");
        Request request4 = new Request("ADD", "Задача №4");
        todos.addLastTask(request4);
        todos.restoreRequest();
        todos.removeTask("Задача №1");
        Request request5 = new Request("REMOVE", "Задача №1");
        todos.addLastTask(request5);
        todos.restoreRequest();
        todos.restoreRequest();

        Assertions.assertEquals("Задача №1 Задача №2", todos.getAllTasks());
    }
}


