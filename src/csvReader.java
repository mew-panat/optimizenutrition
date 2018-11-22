import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvReader {
    static List<InitFood> beverage = new ArrayList<InitFood>();
    static List<InitFood> mainDish = new ArrayList<InitFood>();
    static List<InitFood> fruit = new ArrayList<InitFood>();
    static List<InitFood> snack = new ArrayList<InitFood>();
    public void readbeverage(String file) {
        InitFood newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new InitFood(splitStr[0], splitStr[1], Double.parseDouble(splitStr[2])
                        , Double.parseDouble(splitStr[3]), Double.parseDouble(splitStr[4])
                        , Double.parseDouble(splitStr[5]), Double.parseDouble(splitStr[6])
                        , Double.parseDouble(splitStr[7]), Double.parseDouble(splitStr[8])
                        , Double.parseDouble(splitStr[9]), Double.parseDouble(splitStr[10]));
                beverage.add(newFood);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readMainDish(String file) {
        InitFood newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new InitFood(splitStr[0], splitStr[1], Double.parseDouble(splitStr[2])
                        , Double.parseDouble(splitStr[3]), Double.parseDouble(splitStr[4])
                        , Double.parseDouble(splitStr[5]), Double.parseDouble(splitStr[6])
                        , Double.parseDouble(splitStr[7]), Double.parseDouble(splitStr[8])
                        , Double.parseDouble(splitStr[9]), Double.parseDouble(splitStr[10]));
                mainDish.add(newFood);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFruit(String file) {
        InitFood newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new InitFood(splitStr[0], splitStr[1], Double.parseDouble(splitStr[2])
                        , Double.parseDouble(splitStr[3]), Double.parseDouble(splitStr[4])
                        , Double.parseDouble(splitStr[5]), Double.parseDouble(splitStr[6])
                        , Double.parseDouble(splitStr[7]), Double.parseDouble(splitStr[8])
                        , Double.parseDouble(splitStr[9]), Double.parseDouble(splitStr[10]));
                fruit.add(newFood);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readSnack(String file) {
        InitFood newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new InitFood(splitStr[0], splitStr[1], Double.parseDouble(splitStr[2])
                        , Double.parseDouble(splitStr[3]), Double.parseDouble(splitStr[4])
                        , Double.parseDouble(splitStr[5]), Double.parseDouble(splitStr[6])
                        , Double.parseDouble(splitStr[7]), Double.parseDouble(splitStr[8])
                        , Double.parseDouble(splitStr[9]), Double.parseDouble(splitStr[10]));
                snack.add(newFood);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<InitFood> readBeverage() {
        String file = "csv/Beverage.csv";
        csvReader foodreader = new csvReader();
        foodreader.readbeverage(file);
        return beverage;
    }
    public static List<InitFood> readMainDish() {
        String file = "csv/MainDish.csv";
        csvReader foodreader = new csvReader();
        foodreader.readMainDish(file);
        return mainDish;
    }
    public static List<InitFood> readFruit() {
        String file = "csv/Fruit.csv";
        csvReader foodreader = new csvReader();
        foodreader.readFruit(file);
        return fruit;
    }

    public static List<InitFood> readSnack() {
        String file = "csv/Snacks.csv";
        csvReader foodreader = new csvReader();
        foodreader.readSnack(file);
        return snack;
    }

}
