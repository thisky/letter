package org.example.service.impl;

import org.example.service.Replace;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * use regex handle
 */
public class RegexReplaceImpl implements Replace {

    String replaceRegex = "(\\w)\\1{2,}";

    public List<String> simpleProcess(String raw) {
        valid(raw);

        int oldLength = raw.length();
        var replaced = raw;
        var list = new ArrayList<String>();
        while (true) {
            replaced = replaced.replaceFirst(replaceRegex, "");

            if (replaced.length() == oldLength) {
                // if not matches, return raw data
                if (replaced.length() == raw.length()) {
                    list.add(replaced);
                }
                break;
            }
            list.add(replaced);
            oldLength = replaced.length();
        }
        return list;
    }

    public List<String> advanceProcess(String raw) {
        valid(raw);
        var oldLength = raw.length();
        var replaced = raw;
        var list = new ArrayList<String>();

        while (true) {
            var matches = new ArrayList<String>();
            replaced = Pattern.compile(replaceRegex).matcher(replaced).replaceFirst((r) -> {
                var before = r.group();
                var letter = before.charAt(0);
                if (letter == 'a') {
                    return "";
                }
                var after = String.valueOf((char) (letter - 1));
                matches.add(before + " is replaced by " + after);
                return after;
            });
            if (replaced.length() == oldLength) {
                // if not matches, return raw data
                if (replaced.length() == raw.length()) {
                    list.add(replaced);
                }
                break;
            }
            var row = replaced;
            if (!matches.isEmpty()) {
                row += ", " + String.join(", ", matches);
            }
            list.add(row);

            oldLength = replaced.length();
        }
        return list;
    }
}
