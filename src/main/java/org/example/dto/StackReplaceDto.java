package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StackReplaceDto {

    private StringBuilder sb;

    // sb index
    private int index;

    // max repeat char size
    private int size;

    // output string
    private List<String> list;
}
