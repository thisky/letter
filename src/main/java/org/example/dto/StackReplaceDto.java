package org.example.dto;

import java.util.List;

public class StackReplaceDto {

    private StringBuilder sb;

    // sb index
    private int index;

    // max repeat char size
    private int size;

    // output string
    private List<String> list;

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
