import db.MyDatabase;
import exception.WrongValuesException;
import io.MyReader;
import io.MyWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import model.mapper.BikeMapper;
import util.MyContentFormatter;
import util.Searcher;

public class Main {
    private static final Searcher SEARCHER = new Searcher();
    private static final MyReader MY_READER = new MyReader();
    private static final BikeMapper BIKE_MAPPER = new BikeMapper();
    private static final MyDatabase MY_DATABASE = MyDatabase.getInstance();
    private static final String MENU =
            "Please, choose a topic below which you are interesting(1-7)\n"
                    + "1 - Show the entire EcoBike catalog\n"
                    + "2 – Add a new folding bike\n"
                    + "3 – Add a new speedelec\n"
                    + "4 – Add a new e-bike\n"
                    + "5 – Find the particular bikes\n"
                    + "6 – Write to file\n"
                    + "7 – Stop the program";
    private static final String SEARCHER_MESSAGE =
            "Please input fields in certain order. Don't forget about ';' after each parameters.\n"
                    + "Examples for each types below:\n"
                    + "FOLDING BIKE bmx(brand); 29(size of wheels); 24(number of gears); "
                    + "3000(weight); true(the availability of lights at front and back); "
                    + "black(color); "
                    + "2000(price)\nELECTRIC BIKE mustang(brand); 80(maximum speed); "
                    + "8000(weight); false(the availability of lights at front "
                    + "and back); 15000(battery capacity); white(color); 15000(price)\n"
                    + "SPEEDELEC porsche(brand); 88(maximum speed); 10000(weight); "
                    + "true(the availability "
                    + "of lights at front and back); 30000(battery capacity); red(color); "
                    + "150000(price)\nYou could input only brand or brand with several parameters.";
    private static final List<String> FORDING_BIKE_FIELDS = List.of("A brand: ", "The size of the "
                    + "wheels (in inch): ",
            "The number of gears: ", "The weight of the bike (in grams): ",
            "The availability of lights at front and back (true/false): ",
            "A color: ", "The price: ");
    private static final List<String> ELECTRIC_BIKE_FIELDS = List.of("A brand: ",
            "The maximum speed (in km/h): ",
            "The battery capacity (in mAh): ", "The weight of the bike (in grams): ",
            "The availability of lights at front and back (true/false): ",
            "A color: ", "The price: ");

    public static void main(String[] args) {
        System.out.println("Dear User! Please, input path to file:");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isWorking = MY_READER.isFillContentFromFile();
            while (isWorking) {
                System.out.println(MENU);
                switch (bufferedReader.readLine()) {
                    case "1":
                        MyContentFormatter.changeFormat(MY_DATABASE.getContent())
                                .forEach(System.out::println);
                        System.out.println("Done!\n");
                        break;
                    case "2":
                        List<String> foldingValues = MY_READER.getValues(FORDING_BIKE_FIELDS);
                        try {
                            MY_DATABASE.addBike(BIKE_MAPPER.getFoldingBike(foldingValues));
                            System.out.println("Done!\n");
                        } catch (WrongValuesException | NumberFormatException e) {
                            System.out.println("Folding bike was not saved! "
                                    + "Please, input values correctly. "
                                    + e.getMessage() + "\n");
                        }
                        break;
                    case "3":
                        List<String> electricValues = MY_READER.getValues(ELECTRIC_BIKE_FIELDS);
                        try {
                            MY_DATABASE.addBike(BIKE_MAPPER.getSpeedelec(electricValues));
                            System.out.println("Done!\n");
                        } catch (NumberFormatException | WrongValuesException e) {
                            System.out.println("Electric bike was not saved! "
                                    + "Please, input values correctly. "
                                    + e.getMessage() + "\n");
                        }
                        break;
                    case "4":
                        List<String> speedelecValues = MY_READER.getValues(ELECTRIC_BIKE_FIELDS);
                        try {
                            MY_DATABASE.addBike(BIKE_MAPPER.getElectricBike(speedelecValues));
                            System.out.println("Done!\n");
                        } catch (NumberFormatException | WrongValuesException e) {
                            System.out.println("Speedelec was not saved! Please, "
                                    + "input values correctly. "
                                    + e.getMessage() + "\n");
                        }
                        break;
                    case "5":
                        System.out.println(SEARCHER_MESSAGE);
                        try {
                            SEARCHER.findCertainBikes(MY_DATABASE.getContent(),
                                    bufferedReader.readLine());
                            Thread.currentThread().join(10);
                            MyContentFormatter.changeFormat(MY_DATABASE.getSearchedResult())
                                    .forEach(System.out::println);
                            MY_DATABASE.getSearchedResult().clear();
                        } catch (WrongValuesException e) {
                            System.out.println(e.getMessage());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Done!\n");
                        break;
                    case "6":
                        MyWriter myWriter = new MyWriter();
                        myWriter.writeBikesFile(MY_DATABASE.getUserPath());
                        System.out.println("Done!\n");
                        break;
                    case "7":
                        if (MY_DATABASE.getNewBikes().size() != 0) {
                            System.out.println("You din't save new data! Would you "
                                    + "like to do it?(yes/no)");
                            String answer = bufferedReader.readLine();
                            if (answer.equals("no")) {
                                isWorking = false;
                                System.out.println("Goodbye! Have a nice day!");
                            }
                        } else {
                            isWorking = false;
                            System.out.println("Goodbye! Have a nice day!");
                        }
                        break;
                    default:
                        System.out.println("Please, use only digital from 1 to 7!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
