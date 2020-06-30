package model;

import org.junit.Assert;
import org.junit.Test;
import util.MyContentFormatter;

import java.util.List;

public class MyContentFormatTest {
    List<String> content = List.of("FOLDING BIKE VNV; 24; 1; 10100; true; red; 575",
            "E-BIKE SkyBike; 45; 21700; true; 10000; black; 2119",
            "SPEEDELEC Kugoo; 15; 21100; false; 9700; flame; 1429");
    @Test
    public void changeFermatOk() {
        for (String newContent: MyContentFormatter.changeFormat(content)) {
            Assert.assertTrue(newContent.matches(".* with .* and .*head/tail light.\nPrice:.*euro."));
        }
    }
}
