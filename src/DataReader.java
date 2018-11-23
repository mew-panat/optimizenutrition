import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DataReader {
    private static List<Food> beverage = new ArrayList<>();
    private static List<Food> mainDish = new ArrayList<>();
    private static List<Food> fruit = new ArrayList<>();
    private static List<Food> snack = new ArrayList<>();

    private void readBeverage(String file) {
        Food newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new Food(splitStr[0], splitStr[1], Float.parseFloat(splitStr[2])
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
    private void readMainDish(String file) {
        Food newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new Food(splitStr[0], splitStr[1], Float.parseFloat(splitStr[2])
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
    private void readFruit(String file) {
        Food newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new Food(splitStr[0], splitStr[1], Float.parseFloat(splitStr[2])
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
    private void readSnack(String file) {
        Food newFood = null;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] splitStr = line.trim().split(",");
                newFood = new Food(splitStr[0], splitStr[1], Float.parseFloat(splitStr[2])
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

    public static List<Food> getBeverage() {
        String file = "dataset/Beverage.csv";
        DataReader foodreader = new DataReader();
        foodreader.readBeverage(file);
        return beverage;
    }
    public static List<Food> getMainDish() {
        String file = "dataset/MainDish.csv";
        DataReader foodreader = new DataReader();
        foodreader.readMainDish(file);
        return mainDish;
    }
    public static List<Food> getFruit() {
        String file = "dataset/Fruit.csv";
        DataReader foodreader = new DataReader();
        foodreader.readFruit(file);
        return fruit;
    }

    public static List<Food> getSnack() {
        String file = "dataset/Snacks.csv";
        DataReader foodreader = new DataReader();
        foodreader.readSnack(file);
        return snack;
    }
}
