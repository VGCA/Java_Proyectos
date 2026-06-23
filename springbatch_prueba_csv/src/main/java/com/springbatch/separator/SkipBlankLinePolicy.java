package com.springbatch.separator;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class SkipBlankLinePolicy extends SimpleRecordSeparatorPolicy {

    @Override
    public boolean isEndOfRecord(String line) {
        if (line.trim().isEmpty()) {
            return false;
        }
        return super.isEndOfRecord(line);
    }
    
    @Override
    public String postProcess(String message) {
        if (message == null || message.trim().isEmpty()) {
            return null;
        }
        return super.postProcess(message);
    }
}
