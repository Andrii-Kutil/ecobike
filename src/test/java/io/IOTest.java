package io;

import db.MyDatabase;
import model.bikes.Bike;
import model.bikes.ElectricBike;
import model.bikes.FoldingBike;
import model.bikes.Speedelec;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IOTest {
    private static final String PATH = "src/test/java/resources/test.txt";
    private static final MyReader MY_READER = new MyReader();
    private static final MyWriter MY_WRITER = new MyWriter();
    private static final MyDatabase MY_DATABASE = MyDatabase.getInstance();

    @BeforeClass
    public static void createObject() throws IOException {
        if (!Files.exists(Paths.get(PATH))) {
            Files.createFile(Paths.get(PATH));
            List<Bike> bikes = List.of(new FoldingBike("FOLDING BIKE Maraton", 3450,
                            true, "brown", 2332, 34, 5),
                    new ElectricBike("ELECTRIC BIKE gucci", 12345, false,
                            "white", 6000, 45, 16000),
                    new Speedelec("SPEEDELEC mustang", 567, true,
                            "green", 67789, 80, 30000)
            );
            MY_DATABASE.getNewBikes().addAll(bikes);
            MY_WRITER.writeBikesFile(PATH);
        }
    }

    @Test
    public void writeBikesFileOk() throws IOException {
        MY_DATABASE.getNewBikes().addAll(List.of(new Speedelec("SPEEDELEC mustang",
                567, true,
                "green", 67789, 80, 30000)));
        MY_WRITER.writeBikesFile(PATH);
        Assert.assertEquals(4, MY_READER.readAll(PATH).size());
    }

    @Test
    public void readAllOk() {
        List<String> content = MY_READER.readAll(PATH);
        Assert.assertEquals(3, content.size());
    }

    @AfterClass
    public static void deleteTestFile() throws IOException {
        Files.delete(Paths.get(PATH));
    }
}
