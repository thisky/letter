package org.example;

import org.example.service.Replace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReplaceTest {

    @Test
    void testInvalid() {
        Replace replace = new Replace() {
            @Override
            public List<String> simpleProcess(String raw) {
                return null;
            }

            @Override
            public List<String> advanceProcess(String raw) {
                return null;
            }
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("1"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("$"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("A"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid(" "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("aA"));
    }
}