package model.mapper;

import exception.WrongValuesException;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class BikeMapperTest {
    private final BikeMapper bikeMapper = new BikeMapper();
    private final List<String> failedValues = List.of("brand", "max_speed/wheel_size",
            "battery_capacity/gears", "weight",
            "isAvailableLight", "color", "price");
    private final List<String> valuesForElectricBike = List.of("brand1", "65", "16000", "5000",
            "true", "white", "8000");
    private final List<String> valuesForFoldingBike = List.of("brand2", "29", "24",
            "3550", "false", "black", "3500");
    private final List<String> negativeValues = List.of("brand2", "-29", "-24",
            "0", "false", "black", "-3500");

    @Test(expected = NumberFormatException.class)
    public void getBikeFail() {
        bikeMapper.getFoldingBike(failedValues);
    }

    @Test(expected = WrongValuesException.class)
    public void getBikeWithNegativeValues() {
        bikeMapper.getFoldingBike(negativeValues);
    }

    @Test
    public void getFoldingBikeOk() {
        Assert.assertNotNull(bikeMapper.getFoldingBike(valuesForFoldingBike));
    }

    @Test
    public void getElectricBikeOk() {
        Assert.assertNotNull(bikeMapper.getElectricBike(valuesForElectricBike));
    }

    @Test
    public void getSpeedelecBikeOk() {
        Assert.assertNotNull(bikeMapper.getSpeedelec(valuesForElectricBike));
    }
}
