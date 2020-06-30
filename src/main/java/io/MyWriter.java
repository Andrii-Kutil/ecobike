package io;

import db.MyDatabase;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import model.bikes.Bike;

public class MyWriter {
    private final MyDatabase myDatabase = MyDatabase.getInstance();

    public void writeBikesFile(String userPath) throws IOException {
        String allBikes = myDatabase.getBikes()
                .stream()
                .map(Bike::getDataBaseType)
                .collect(Collectors.joining("\n"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(userPath, true));
        if (!allBikes.isEmpty()) {
            writer.newLine();
            writer.write(allBikes);
        }
        myDatabase.getBikes().clear();
        writer.close();
    }
}
