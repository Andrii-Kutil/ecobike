package util;

import db.MyDatabase;
import exception.WrongValuesException;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class SearcherTest {
    private final Searcher searcher = new Searcher();
    private final MyDatabase myDatabase = MyDatabase.getInstance();
    private final List<String> content = List.of("SPEEDELEC Speedway; 45; 7100; false; 9700; red; 615",
            "FOLDING BIKE Maraton; 20; 19800; false; 15900; silver; 1215",
            "SPEEDELEC Speedway; 45; 7100; true;; 700; white; 615",
            "E-BIKE ION; 20; 20200; false; 20000; grey; 1989",
            "E-BIKE Zaxboard; 30; 22500; false; 28000; pink; 3345");

    @Test
    public void findCertainBikesOk() throws InterruptedException {
        myDatabase.getContent().addAll(content);
        String userRequestOk1 = "SPEEDELEC Speedway;";
        searcher.findCertainBikes(content, userRequestOk1);
        Thread.currentThread().join(10);
        Assert.assertEquals(2, myDatabase.getSearchedResult().size());
        myDatabase.getSearchedResult().clear();
    }

    @Test
    public void findCertainBikesNotValid() throws InterruptedException {
        String userRequestNotValid = "E-BIKE Zaxboard;";
        searcher.findCertainBikes(content, userRequestNotValid);
        Thread.currentThread().join(10);
        Assert.assertNotEquals(2, myDatabase.getSearchedResult().size());
        myDatabase.getSearchedResult().clear();
    }

    @Test(expected = WrongValuesException.class)
    public void findCertainBikesFailed() throws InterruptedException {
        String userRequestNotValid = "E-BIKE Zaxboard; 30; 22500; false; 28000; pink; 3345; extra_field";
        searcher.findCertainBikes(content, userRequestNotValid);
        Thread.currentThread().join(10);
        Assert.fail();
    }
}
