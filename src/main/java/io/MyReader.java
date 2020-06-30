package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyReader {
    public List<String> readAll(String userPath) {
        Path path = Paths.get(userPath);
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content;
    }

    public List<String> getValues(List<String> fields) throws IOException {
        List<String> values;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        values = new ArrayList<>();
        System.out.println("Please, fill fields below:");
        for (String field : fields) {
            System.out.println(field);
            values.add(bufferedReader.readLine());
        }
        return values;
    }
}
