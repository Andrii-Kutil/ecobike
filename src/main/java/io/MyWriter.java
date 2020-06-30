package io;

import db.MyDatabase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import model.bikes.Bike;

public class MyWriter {
    private final MyDatabase myDatabase = MyDatabase.getInstance();

    public void writeBikesFile(String userPath) throws IOException {
        String allBikes = myDatabase.getNewBikes()
                .stream()
                .map(Bike::getDataBaseType)
                .collect(Collectors.joining("\n"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(userPath, true));
        File newFile = new File(userPath);
        if (!allBikes.isEmpty()) {
            if (!(newFile.length() == 0)) {
                writer.newLine();
            }
            writer.write(allBikes);
            myDatabase.getNewBikes().clear();
            writer.close();
        }
    }
}
