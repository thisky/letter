package org.example.service;

import org.example.service.impl.RegexReplaceImpl;
import org.example.service.impl.StackReplaceImpl;


public class ReplaceFactory {

    /**
     * @param name replace implement name
     * @return replace Object
     */
    public static Replace getReplace(String name) {
        return switch (name) {
            case "regex" -> new RegexReplaceImpl();
            case "stack" -> new StackReplaceImpl();
            default -> throw new RuntimeException("replace class: [ " + name + " ] not exits");
        };
    }
}
