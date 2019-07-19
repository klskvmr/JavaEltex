package ru.eltex;

public enum WeekDay {
    SUNDAY("Воскресенье"),
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота");

    public String title;

    WeekDay(String title) {
        this.title = title;
    }
}
