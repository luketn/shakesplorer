package com.mycodefu.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public record Work (String workID, String title, String longTitle, Instant date, String genreType, String notes, String source, int totalWords, int totalParagraphs) {

    public static List<Work> load() {
        return DataReader.read("Works.txt", Work::mapFieldsToWork, 9);
    }

    private static Work mapFieldsToWork(String[] fields) {
        int fieldIndex=0;
        String workID = fields[fieldIndex++];
        String title = fields[fieldIndex++];
        String longTitle = fields[fieldIndex++];
        String yearStr = fields[fieldIndex++];
        int year = Integer.parseInt(yearStr);
        Instant date = LocalDate.of(year, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC);
        String genreType = fields[fieldIndex++];
        String notes = fields[fieldIndex++];
        String source = fields[fieldIndex++];
        int totalWords = Integer.parseInt(fields[fieldIndex++]);
        int totalParagraphs = Integer.parseInt(fields[fieldIndex]);

        return new Work(
                workID,
                title,
                longTitle,
                date,
                genreType,
                notes,
                source,
                totalWords,
                totalParagraphs
        );
    }

}
