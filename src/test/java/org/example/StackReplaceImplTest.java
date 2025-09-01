package org.example;

import org.example.service.Replace;
import org.example.service.impl.StackReplaceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StackReplaceImplTest {

    @Test
    void simpleReplace() {
        Replace replace = new StackReplaceImpl();

        Assertions.assertLinesMatch(replace.simpleProcess("a"), List.of("a"));
        Assertions.assertLinesMatch(replace.simpleProcess("aa"), List.of("aa"));
        Assertions.assertLinesMatch(replace.simpleProcess("aaa"), List.of(""));
        Assertions.assertLinesMatch(replace.simpleProcess("aaaa"), List.of(""));

        Assertions.assertLinesMatch(replace.simpleProcess("abc"), List.of("abc"));
        Assertions.assertLinesMatch(replace.simpleProcess("aabbcc"), List.of("aabbcc"));

        Assertions.assertLinesMatch(replace.simpleProcess("aaabbbccc"), List.of("bbbccc", "ccc", ""));
        Assertions.assertLinesMatch(replace.simpleProcess("aaaabbbbcccc"), List.of("bbbbcccc", "cccc", ""));

        Assertions.assertLinesMatch(replace.simpleProcess("aabcccbbad"), List.of("aabbbad", "aaad", "d"));
    }

    @Test
    void advanceReplace() {
        Replace replace = new StackReplaceImpl();

        Assertions.assertLinesMatch(replace.advanceProcess("a"), List.of("a"));
        Assertions.assertLinesMatch(replace.advanceProcess("aa"), List.of("aa"));
        Assertions.assertLinesMatch(replace.advanceProcess("aaa"), List.of(""));
        Assertions.assertLinesMatch(replace.advanceProcess("aaaa"), List.of(""));

        Assertions.assertLinesMatch(replace.advanceProcess("abc"), List.of("abc"));
        Assertions.assertLinesMatch(replace.advanceProcess("aabbcc"), List.of("aabbcc"));

        Assertions.assertLinesMatch(replace.advanceProcess("aaabbbccc"), List.of(
                "bbbccc",
                "accc, bbb is replaced by a",
                "ab, ccc is replaced by b"
        ));
        Assertions.assertLinesMatch(replace.advanceProcess("aaaaddddyyyyzzzz"), List.of(
                "ddddyyyyzzzz",
                "cyyyyzzzz, dddd is replaced by c",
                "cxzzzz, yyyy is replaced by x",
                "cxy, zzzz is replaced by y"
        ));

        Assertions.assertLinesMatch(replace.advanceProcess("abcccbad"), List.of(
                "abbbad, ccc is replaced by b",
                "aaad, bbb is replaced by a",
                "d"
        ));
    }
}