package ru.eltex;

import java.io.IOException;

public interface JSON {
    String toJSON() throws IOException;

    void fromJSON(String string) throws IOException;
}
