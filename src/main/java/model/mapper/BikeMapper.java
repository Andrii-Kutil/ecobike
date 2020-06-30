package model.mapper;

import exception.WrongValuesException;
import java.util.List;
import model.bikes.Bike;
import model.bikes.ElectricBike;
import model.bikes.FoldingBike;
import model.bikes.Speedelec;

public class BikeMapper {

    private Bike getBike(List<String> values) {
        String brand = values.get(0).toLowerCase();
        boolean isAvailableLight = Boolean.parseBoolean(values.get(4).toLowerCase());
        int weight = Integer.parseInt(values.get(3));
        String color = values.get(5).toLowerCase();
        int price = Integer.parseInt(values.get(6));
        if (weight < 1 || price < 1) {
            throw new WrongValuesException("Weight or price can't be less 1. ");
        }
        return new Bike(brand, weight, isAvailableLight, color, price);
    }

    public FoldingBike getFoldingBike(List<String> values)
            throws NumberFormatException, WrongValuesException {
        Bike bike = getBike(values);
        int gears = Integer.parseInt(values.get(2));
        int wheelSize = Integer.parseInt(values.get(1));
        if (gears < 1 || wheelSize < 1) {
            throw new WrongValuesException("Number of gears or wheel's size can't be less 1. ");
        }
        return new FoldingBike("FOLDING BIKE " + bike.getBrand(), bike.getWeight(),
                bike.isAvailableLight(), bike.getColor(), bike.getPrice(), wheelSize, gears);
    }

    public Speedelec getSpeedelec(List<String> values)
            throws NumberFormatException, WrongValuesException {
        Bike bike = getBike(values);
        int maxSpeed = Integer.parseInt(values.get(1));
        int batteryCapacity = Integer.parseInt(values.get(2));
        if (maxSpeed < 1 || batteryCapacity < 1) {
            throw new WrongValuesException("Maximum speed or battery capacity can't be less 1. ");
        }
        return new Speedelec("SPEEDELEC " + bike.getBrand(), bike.getWeight(),
                bike.isAvailableLight(), bike.getColor(), bike.getPrice(),
                maxSpeed, batteryCapacity);
    }

    public ElectricBike getElectricBike(List<String> values)
            throws NumberFormatException, WrongValuesException {
        ElectricBike electricBike = getSpeedelec(values);
        electricBike.setBrand("E-BIKE " + values.get(0));
        return electricBike;
    }
}
