package com.mycodefu.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class DataReader {

    /**
     * Read a resource file in the Shakespeare data set using a mapper to convert it into the generic type.
     */
    public static <T> List<T> read(String resourceName, Function<String[], T> mapper, int fieldCount) {
        InputStream inputStream = DataReader.class.getResourceAsStream("/data/" + resourceName);
        Scanner scanner = new Scanner(inputStream);

        //Skip header row
        scanner.nextLine();

        List<T> result = new ArrayList<>();
        while(scanner.hasNextLine()){
            String[] fields = getFields(scanner, fieldCount);
            result.add(mapper.apply(fields));
        }
        return result;
    }

    private static String[] getFields(Scanner scanner, int fieldCount) {
        List<String> fields = new ArrayList<>();
        while (fields.size() < fieldCount) {
            String line = scanner.nextLine() + "\n";
            StringBuilder field = new StringBuilder();
            boolean eatingStringField = false;
            for (int charIndex = 0; charIndex < line.length(); charIndex++) {
                char c = line.charAt(charIndex);
                if (c == '~') {
                    eatingStringField = !eatingStringField;
                } else if ((c == ',' || c == '\n') && !eatingStringField) {
                    fields.add(field.toString());
                    field.setLength(0);
                } else {
                    field.append(c);
                }
            }
        }
        return fields.toArray(new String[]{});
    }
}
