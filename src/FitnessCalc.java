import java.util.ArrayList;
import java.util.List;

public class FitnessCalc {
    List<Food> beverage = new ArrayList<>();
    List<Food> mainDish = new ArrayList<>();
    List<Food> fruit = new ArrayList<>();
    List<Food> snack = new ArrayList<>();
    public static double getFitness(Individual individual) {
        double fitness;
        String b1 = convertBinary(0, 4, individual);
        String b2 = convertBinary(4, 8, individual);
        String m1 = convertBinary(8, 13, individual);
        String m2 = convertBinary(13, 18, individual);
        String m3 = convertBinary(18, 23, individual);
        String f1 = convertBinary(23, 27, individual);
        String s1 = convertBinary(27, 31, individual);

        fitness = getBeveragePrice(b1) + getBeveragePrice(b2)
                + getMaindishPrice(m1) + getMaindishPrice(m2) + getMaindishPrice(m3)
                + getFruitPrice(f1) + getSnackPrice(s1);
        System.out.println("Cost:" + fitness);
        return fitness;
    }

    private static String convertBinary(int start, int end, Individual individual){
        String[] a = new String[end-start];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(individual.getGene(i));
            //System.out.print(a[i-start]);
        }
        //System.out.println();

        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        return builder.toString();
    }
    private static Double getBeveragePrice(String binary) {
        Food food;
        food = FoodData.matchBeverage(binary);
        if(food!=null)
            return food.getPrice();
        else return 0.0;
    }

    private static Double getMaindishPrice(String binary) {
        Food food;
        food = FoodData.matchMaindish(binary);
        if(food!=null)
            return food.getPrice();
        else return 0.0;
    }

    private static Double getFruitPrice(String binary) {
        Food food;
        food = FoodData.matchFruit(binary);
        if(food!=null)
            return food.getPrice();
        else return 0.0;
    }

    private static Double getSnackPrice(String binary) {
        Food food;
        food = FoodData.matchSnack(binary);
        if(food!=null)
            return food.getPrice();
        else return 0.0;
    }
}
