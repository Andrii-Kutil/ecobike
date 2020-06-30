package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.bikes.Bike;

public class MyDatabase {
    private static MyDatabase database;
    private final List<Bike> newBikes;
    private final List<String> content;
    private final List<String> searchedResult;
    private String userPath;

    private MyDatabase() {
        newBikes = new ArrayList<>();
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
        newBikes.add(bike);
    }

    public List<Bike> getNewBikes() {
        return newBikes;
    }

    public List<String> getContent() {
        return content;
    }

    public List<String> getSearchedResult() {
        return searchedResult;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
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
        return Objects.equals(newBikes, that.newBikes)
                && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newBikes, content);
    }

    @Override
    public String toString() {
        return "MyDatabase{"
                + "bikes=" + newBikes
                + ", content=" + content
                + '}';
    }
}
