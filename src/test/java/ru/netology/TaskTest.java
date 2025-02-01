package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testMatchesWhenQueryIsInTitle() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(task.matches("Позвонить родителям"));
        Assertions.assertEquals(5, task.getId());
        Assertions.assertEquals("Позвонить родителям", task.getTitle());
    }

    @Test
    void testMatchesWhenQueryIsNotInTitle() {
        SimpleTask task = new SimpleTask(2, "Позвонить родителям");

        Assertions.assertFalse(task.matches("Купить продукты"));
    }

    @Test
    void testMatchesWithEmptyQuery() {
        SimpleTask task = new SimpleTask(3, "");

        Assertions.assertFalse(task.matches("Купить продукты"));
    }

    @Test
    void testMatchesWhenQueryIsInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic task = new Epic(55, subtasks);

        Assertions.assertTrue(task.matches("Молоко"));
    }

    @Test
    void testMatchesWhenQueryIsNotInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic task = new Epic(50, subtasks);

        Assertions.assertFalse(task.matches("Масло"));
    }

    @Test
    void testMatchesWithEmptyQueryEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic task = new Epic(50, subtasks);

        Assertions.assertFalse(task.matches(""));
    }

    @Test
    void testGetSubtasksReturnsCorrectArray() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic task = new Epic(60, subtasks);

        Assertions.assertArrayEquals(subtasks, task.getSubtasks());
        Assertions.assertEquals(60, task.getId());
    }

    @Test
    void testMatchesWhenQueryIsInTopicAndProject() {
        Meeting task = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(task.matches("Выкатка 3й версии приложения"));
        Assertions.assertTrue(task.matches("Приложение НетоБанка"));
        Assertions.assertEquals("Выкатка 3й версии приложения", task.getTopic());
        Assertions.assertEquals("Приложение НетоБанка", task.getProject());
        Assertions.assertEquals("Во вторник после обеда", task.getStart());
    }

    @Test
    void testMatchesWhenQueryIsInTopic() {
        Meeting task = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(task.matches("Выкатка 3й версии приложения"));
        Assertions.assertFalse(task.matches("Во вторник после обеда"));
    }

    @Test
    void testMatchesWhenQueryIsInProject() {
        Meeting task = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertFalse(task.matches("Встреча"));
        Assertions.assertTrue(task.matches("Приложение НетоБанка"));
    }

    @Test
    void testMatchesNotFound() {
        Meeting task = new Meeting(
                553,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertFalse(task.matches("Встреча1"));
        Assertions.assertFalse(task.matches("Встреча2"));
    }

    @Test
    void twoObjectsWithTheSameId() {
        SimpleTask task1 = new SimpleTask(5, "Позвонить родителям");
        SimpleTask task2 = new SimpleTask(5, "Купить продукты");

        Assertions.assertTrue(task1.equals(task2));
    }

    @Test
    void NotEqualTasksOfDifferentTypes() {
        SimpleTask task = new SimpleTask(10, "Позвонить родителям");
        Epic task1 = new Epic(10, new String[]{"Молоко", "Яйца", "Хлеб"});

        Assertions.assertFalse(task.equals(task1));
    }

    @Test
    void testNotEqualTasksWithDifferentIds() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить родителям");
        SimpleTask task2 = new SimpleTask(2, "Купить продукты");

        Assertions.assertFalse(task1.equals(task2));
    }

    @Test
    void testHashCodesForEqualTasks() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить родителям");
        SimpleTask task2 = new SimpleTask(1, "Купить продукты");

        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void testHashCodesForUnequalTasks() {
        SimpleTask task1 = new SimpleTask(1, "Позвонить родителям");
        SimpleTask task3 = new SimpleTask(2, "Купить продукты");

        Assertions.assertNotEquals(task1.hashCode(), task3.hashCode());
    }

    @Test
    void testEquality() {
        SimpleTask task = new SimpleTask(7, "Позвонить родителям");

        Assertions.assertTrue(task.equals(task));
    }

    @Test
    void testInequality() {
        SimpleTask task = new SimpleTask(7, "Позвонить родителям");

        Assertions.assertFalse(task.equals(null));
    }

    @Test
    void testMatchesAlwaysReturnsFalse() {

        Task task = new Task(1);

        Assertions.assertFalse(task.matches("Позвонить родителям"));
    }

}

