import java.util.ArrayList;
import java.util.List;

public class FoodData{
    private static List<Food> beverage = new ArrayList<>();
    private static List<Food> mainDish = new ArrayList<>();
    private static List<Food> fruit = new ArrayList<>();
    private static List<Food> snack = new ArrayList<>();

    public static void readFood(){
        beverage = DataReader.getBeverage();
        mainDish = DataReader.getMainDish();
        fruit = DataReader.getFruit();
        snack = DataReader.getSnack();
    }

    public static void printAllFood() {
        for (Food d : beverage) {
            System.out.println(d.toString());
        }
        for (Food d : mainDish) {
            System.out.println(d.toString());
        }
        for (Food d : fruit) {
            System.out.println(d.toString());
        }
        for (Food d : snack) {
            System.out.println(d.toString());
        }
    }

    public static List<Food> getBeverage() {
        return beverage;
    }

    public static List<Food> getMainDish() {
        return mainDish;
    }

    public static List<Food> getFruit() {
        return fruit;
    }

    public static List<Food> getSnack() {
        return snack;
    }

    public static Food matchBeverage(String binary){
        for (Food a : beverage) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }

    public static Food matchMaindish(String binary){
        for (Food a : mainDish) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }

    public static Food matchFruit(String binary){
        for (Food a : fruit) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }

    public static Food matchSnack(String binary){
        for (Food a : snack) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }
}
