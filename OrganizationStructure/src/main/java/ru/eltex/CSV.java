package ru.eltex;

import lombok.SneakyThrows;

import java.io.IOException;
import java.text.ParseException;

public interface CSV {
    String toCSV() throws IOException;

    void fromCSV(String string) throws IOException, TypeException, ParseException;
}
