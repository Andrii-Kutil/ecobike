package model.bikes;

import java.util.Objects;

public class Bike {
    private String brand;
    private int weight;
    private boolean isAvailableLight;
    private String color;
    private int price;

    public Bike(String brand, int weight, boolean isAvailableLight, String color, int price) {
        this.brand = brand;
        this.weight = weight;
        this.isAvailableLight = isAvailableLight;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isAvailableLight() {
        return isAvailableLight;
    }

    public void setAvailableLight(boolean availableLight) {
        isAvailableLight = availableLight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDataBaseType() {
        return brand + "; " + weight + "; " + isAvailableLight + "; " + color + "; " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bike bike = (Bike) o;
        return weight == bike.weight
                && isAvailableLight == bike.isAvailableLight
                && price == bike.price
                && Objects.equals(brand, bike.brand)
                && Objects.equals(color, bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, weight, isAvailableLight, color, price);
    }

    @Override
    public String toString() {
        return "Bike{"
                + "brand='" + brand + '\''
                + ", weight=" + weight
                + ", isAvailableLight=" + isAvailableLight
                + ", color='" + color + '\''
                + ", price=" + price
                + '}';
    }
}
