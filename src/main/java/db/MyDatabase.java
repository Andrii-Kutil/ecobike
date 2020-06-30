package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.bikes.Bike;

public class MyDatabase {
    private static MyDatabase database;
    private final List<Bike> bikes;
    private final List<String> content;
    private List<String> searchedResult;

    private MyDatabase() {
        bikes = new ArrayList<>();
        content = new ArrayList<>();
        searchedResult = new ArrayList<>();
    }

    public static MyDatabase getInstance() {
        if (database == null) {
            database = new MyDatabase();
        }
        return database;
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public List<String> getContent() {
        return content;
    }

    public static MyDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(MyDatabase database) {
        MyDatabase.database = database;
    }

    public List<String> getSearchedResult() {
        return searchedResult;
    }

    public void setSearchedResult(List<String> searchedResult) {
        this.searchedResult = searchedResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyDatabase that = (MyDatabase) o;
        return Objects.equals(bikes, that.bikes)
                && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bikes, content);
    }

    @Override
    public String toString() {
        return "MyDatabase{"
                + "bikes=" + bikes
                + ", content=" + content
                + '}';
    }
}
