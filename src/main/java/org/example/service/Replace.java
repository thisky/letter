package org.example.service;

import java.util.List;
import java.util.regex.Pattern;

public interface Replace {

    // lowercase letters
    String validRegex = "^[a-z]+$";

    /**
     * Example:
     * Input: aabcccbbad
     * Output:
     * -> aabbbad
     * -> aaad
     * -> d
     * """;
     *
     * @param raw input
     * @return output data
     */
    List<String> simpleProcess(String raw);


    /**
     * Input: abcccbad
     * Output:
     * -> abbbad, ccc is replaced by b
     * -> aaad, bbb is replaced by a
     * -> d
     *
     * @param raw input string
     * @return output data
     */
    List<String> advanceProcess(String raw);

    /**
     * check input data
     */
    default void valid(String raw) {
        if (!Pattern.compile(validRegex).matcher(raw).find()) {
            throw new IllegalArgumentException("only need lowercase letters for input");
        }
    }

}
