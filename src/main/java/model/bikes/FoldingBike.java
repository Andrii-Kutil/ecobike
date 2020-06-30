package model.bikes;

import java.util.Objects;

public class FoldingBike extends Bike {
    private int wheelSize;
    private int gears;

    public FoldingBike(String brand, int weight, boolean isAvailableLight,
                       String color, int price, int wheelSize, int gears) {
        super(brand, weight, isAvailableLight, color, price);
        this.wheelSize = wheelSize;
        this.gears = gears;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    @Override
    public String getDataBaseType() {
        return getBrand() + "; " + wheelSize + "; " + gears + "; " + getWeight()
                + "; " + isAvailableLight()
                 + "; " + getColor() + "; " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FoldingBike that = (FoldingBike) o;
        return wheelSize == that.wheelSize
                && gears == that.gears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelSize, gears);
    }

    @Override
    public String toString() {
        return "FoldingBike{"
                + "wheelSize=" + wheelSize
                + ", gears=" + gears
                + '}';
    }
}
