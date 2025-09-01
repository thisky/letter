package org.example;

import org.example.service.ReplaceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

public class Application {
    public static void main(String[] args) {
        var replaceName = System.getProperty("replaceName", "stack");
        var replace = ReplaceFactory.getReplace(replaceName);
        var reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("replace implement: " + replace.getClass().getName());

        var tips = """
                Example:
                Input: aabcccbbad
                Output:
                -> aabbbad
                -> aaad
                -> d
                """;
        System.out.println(tips);
        process(reader, replace::simpleProcess);

        tips = """
                                
                                
                Example:
                 ccc -> b
                 bbb -> a
                 Input: abcccbad
                 Output:
                 -> abbbad, ccc is replaced by b
                 -> aaad, bbb is replaced by a
                 -> d
                """;
        System.out.println(tips);

        process(reader, replace::advanceProcess);

    }

    /**
     * process input output
     *
     * @param reader   console input
     * @param function replace handler
     */
    public static void process(BufferedReader reader, Function<String, List<String>> function) {
        while (true) {
            try {
                var line = reader.readLine();
                if ("".equals(line)) {
                    break;
                }
                System.out.println("Input:");
                var output = function.apply(line.strip());
                System.out.println("Output:");
                output.forEach(row -> System.out.println("-> " + row));
                System.out.println("Press Enter to end");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
