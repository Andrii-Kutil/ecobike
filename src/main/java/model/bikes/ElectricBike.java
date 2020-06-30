package model.bikes;

import java.util.Objects;

public class ElectricBike extends Bike {
    private int maxSpeed;
    private int batteryCapacity;

    public ElectricBike(String brand, int weight, boolean isAvailableLight, String color,
                        int price, int maxSpeed, int batteryCapacity) {
        super(brand, weight, isAvailableLight, color, price);
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String getDataBaseType() {
        return getBrand() + "; " + maxSpeed + "; " + getWeight() + "; " + isAvailableLight() + "; "
                + batteryCapacity + "; " + getColor() + "; " + getPrice();
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
        ElectricBike that = (ElectricBike) o;
        return maxSpeed == that.maxSpeed
                && batteryCapacity == that.batteryCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
    }

    @Override
    public String toString() {
        return "ElectricBike{"
                + "maxSpeed=" + maxSpeed
                + ", batteryCapacity=" + batteryCapacity
                + '}';
    }
}
