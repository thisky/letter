package org.example.service.impl;

import org.example.dto.StackReplaceDto;
import org.example.service.Replace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * use Stack handle
 */
public class StackReplaceImpl implements Replace {

    private final int minSize = 3;

    /**
     * @param raw      input data
     * @param consumer post-processing of matching characters
     * @return output data
     */
    public List<String> abstractProcess(String raw, Consumer<StackReplaceDto> consumer) {
        valid(raw);
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(raw);
        Stack<Integer> count = new Stack<>();
        for (int i = 0; i < stringBuilder.length(); ++i) {
            if (i == 0) {
                count.push(1);
                continue;
            }

            boolean isLast = i == stringBuilder.length() - 1;
            if (stringBuilder.charAt(i) == stringBuilder.charAt(i - 1)) {
                int incremented = count.pop() + 1;
                count.push(incremented);
                if (!isLast) {
                    continue;
                }
            }

            int size = count.peek();
            if (size < minSize) {
                count.push(1);
                continue;
            }

            // not equal, use prev index
            if (stringBuilder.charAt(i) != stringBuilder.charAt(i - 1)) {
                --i;
            }

            StackReplaceDto stackReplaceDto = new StackReplaceDto();
            stackReplaceDto.setSb(stringBuilder);
            stackReplaceDto.setIndex(i);
            stackReplaceDto.setSize(size);
            stackReplaceDto.setList(list);

            count.pop();
            consumer.accept(stackReplaceDto);
            i = i - size;
        }

        if (list.isEmpty()) {
            list.add(stringBuilder.toString());
        }
        return list;
    }

    public List<String> simpleProcess(String raw) {
        return abstractProcess(raw, (dto) -> {
            dto.getSb().delete(dto.getIndex() - dto.getSize() + 1, dto.getIndex() + 1);
            dto.getList().add(dto.getSb().toString());
        });
    }

    public List<String> advanceProcess(String raw) {
        return abstractProcess(raw, (dto) -> {
            StringBuilder sb = dto.getSb();
            int startIndex = dto.getIndex() - dto.getSize() + 1;
            int endIndex = dto.getIndex();
            String before = sb.substring(startIndex, endIndex + 1);
            char letter = sb.charAt(endIndex - 1);
            String after = "";
            if (letter != 'a') {
                after = String.valueOf((char) (letter - 1));
            }
            sb.replace(startIndex, endIndex + 1, after);
            if (after.equals("")) {
                dto.getList().add(sb.toString());
            } else {
                dto.getList().add("%s, %s is replaced by %s".formatted(sb.toString(), before, after));
            }
        });
    }
}
