import org.omg.CORBA.INITIALIZE;

import java.util.ArrayList;
import java.util.List;

public class FoodData {
    private static List<InitFood> allFood = new ArrayList<>();
    private static List<InitFood> beverage = new ArrayList<>();
    private static List<InitFood> mainDish = new ArrayList<>();
    private static List<InitFood> fruit = new ArrayList<>();
    private static List<InitFood> snack = new ArrayList<>();

    public void readData() {
        csvReader foodreader = new csvReader();
        beverage = foodreader.readBeverage();
        mainDish = foodreader.readMainDish();
        fruit = foodreader.readFruit();
        snack = foodreader.readSnack();
    }

    public void getData() {
        //System.out.println(food.size());
        for (InitFood d : beverage) {
            System.out.println(d.toString());
        }
        for (InitFood d : mainDish) {
            System.out.println(d.toString());
        }
        for (InitFood d : fruit) {
            System.out.println(d.toString());
        }
        for (InitFood d : snack) {
            System.out.println(d.toString());
        }
    }

    public static List<InitFood> getAllFoods() {
        for(InitFood a : beverage)
            allFood.add(a);
        for(InitFood a : mainDish)
            allFood.add(a);
        for(InitFood a : fruit)
            allFood.add(a);
        for(InitFood a : snack)
            allFood.add(a);
        return allFood;
    }
    public static List<InitFood> getBeverage(){
        return beverage;
    }
    public static List<InitFood> getMainDish(){
        return mainDish;
    }
    public static List<InitFood> getFruit(){
        return fruit;
    }
    public static List<InitFood> getSnack(){
        return snack;
    }
    public static InitFood matchBeverage(String binary) {
        for (InitFood a : beverage) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }
    public static InitFood matchMainDish(String binary) {
        for (InitFood a : mainDish) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }
    public static InitFood matchFruit(String binary) {
        for (InitFood a : fruit) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }
    public static InitFood matchSnack(String binary) {
        for (InitFood a : snack) {
            if (a.getBinary().equals(binary)) {
                return a;
            }
        }
        return null;
    }
}
