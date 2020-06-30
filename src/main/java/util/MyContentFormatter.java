package util;

import java.util.ArrayList;
import java.util.List;

public class MyContentFormatter {
    public static List<String> changeFormat(List<String> contents) {
        List<String> formattedContents = new ArrayList<>();
        String brand;
        String gearsOrBatterySize;
        String isAvailableLight;
        String price;
        for (String content : contents) {
            if (!content.isEmpty()) {
                String[] split = content.split("; ");
                brand = split[0];
                if (split[0].contains("FOLDING BIKE")) {
                    gearsOrBatterySize = Integer.parseInt(split[2]) > 1
                            ? split[2] + " gears" : split[2] + " gear";
                    isAvailableLight = Boolean.parseBoolean(split[4]) ? "" : " no";
                } else {
                    gearsOrBatterySize = split[4] + " mAh battery";
                    isAvailableLight = Boolean.getBoolean(split[3]) ? "" : " no";
                }
                price = split[6];
                String formattedLine = String
                        .format("%s with %s and%s head/tail light.\nPrice: %s euros",
                        brand, gearsOrBatterySize, isAvailableLight, price);
                formattedContents.add(formattedLine);
            }
        }
        return formattedContents;
    }
}
