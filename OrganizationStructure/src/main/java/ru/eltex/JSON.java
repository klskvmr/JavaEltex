package ru.eltex;

import java.io.IOException;

public interface JSON {
    String toJSON(String filename) throws IOException;

    void fromJSON(String filename) throws IOException, TypeException;
}
