package ru.eltex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Authorization {
    public static String HOST;
    public static String LOGIN;
    public static String PASSWORD;

    static {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            HOST = property.getProperty("db.host");
            LOGIN = property.getProperty("db.login");
            PASSWORD = property.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }
}
