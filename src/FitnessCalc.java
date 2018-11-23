import java.util.ArrayList;
import java.util.List;

public class FitnessCalc {
    List<Food> beverage = new ArrayList<>();
    List<Food> mainDish = new ArrayList<>();
    List<Food> fruit = new ArrayList<>();
    List<Food> snack = new ArrayList<>();
    static double getFitness(Individual individual) {
        double fitness = 0.0;
        String b1 = convertBinary(0, 5, individual);
        String b2 = convertBinary(5, 10, individual);
        String m1 = convertBinary(10, 15, individual);
        String m2 = convertBinary(15, 20, individual);
        String m3 = convertBinary(20, 25, individual);
        String f1 = convertBinary(25, 29, individual);
        String s1 = convertBinary(29, 33, individual);


        fitness = getBeveragePrice(b1) + getBeveragePrice(b2)
                + getMaindishPrice(m1) + getMaindishPrice(m2) + getMaindishPrice(m3)
                + getFruitPrice(f1) + getSnackPrice(s1);

        return fitness;
    }

    static String convertBinary(int start, int end, Individual individual){
        String[] a = new String[end-start];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(individual.getGene(i));
            System.out.print(a[i-start]);
        }

        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        return builder.toString();
    }
    public static Double getBeveragePrice(String food) {
        if(food.equals(FoodData.matchBeverage(food).getBinary()))
            return FoodData.matchBeverage(food).getPrice();
        else return 0.0;
    }

    public static Double getMaindishPrice(String food) {
        if(food.equals(FoodData.matchMaindish(food).getBinary()))
            return FoodData.matchMaindish(food).getPrice();
        else return 0.0;
    }

    public static Double getFruitPrice(String food) {
        if(food.equals(FoodData.matchFruit(food).getBinary()))
            return FoodData.matchFruit(food).getPrice();
        else return 0.0;
    }

    public static Double getSnackPrice(String food) {
        if(food.equals(FoodData.matchSnack(food).getBinary()))
            return FoodData.matchSnack(food).getPrice();
        else return 0.0;
    }
}
