package model.bikes;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BikeTest {
    private static Bike bike;
    private static FoldingBike foldingBike;
    private static ElectricBike electricBike;
    private static ElectricBike speedelec;

    @BeforeClass
    public static void createObject() {
        bike = new Bike("test_brand1", 12009, true, "black", 3500);
        foldingBike = new FoldingBike("test_brand2", 43578, false, "black",
                23425, 32, 6);
        electricBike = new ElectricBike("test_brand3", 54723, false, "white",
                7895, 8, 15678);
        speedelec = new Speedelec("test_brand4", 3456, false, "brown",
                10000, 80, 546899);
    }

    @Test
    public void getBikeDataBaseTypeOk() {
        Assert.assertEquals(5, bike.getDataBaseType().split("; ").length);
    }

    @Test
    public void getFoldingBikeDataBaseTypeOk() {
        Assert.assertEquals(7, foldingBike.getDataBaseType().split("; ").length);
    }

    @Test
    public void getElectricBikeDataBaseTypeOk() {
        Assert.assertEquals(7, electricBike.getDataBaseType().split("; ").length);
    }

    @Test
    public void getSpeedelecDataBaseTypeOk() {
        Assert.assertEquals(7, speedelec.getDataBaseType().split("; ").length);
    }
}
