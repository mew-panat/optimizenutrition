import java.util.ArrayList;
import java.util.List;

public class FitnessCalc {
    public static float getFitness(Individual individual) {
        float fitness;
        String b1 = convertBinary(0, 4, individual);
        String b2 = convertBinary(4, 8, individual);
        String m1 = convertBinary(8, 13, individual);
        String m2 = convertBinary(13, 18, individual);
        String m3 = convertBinary(18, 23, individual);
        String f1 = convertBinary(23, 27, individual);
        String s1 = convertBinary(27, 31, individual);

        fitness = getBeverage(b1).getPrice() + getBeverage(b2).getPrice()
                + getMaindish(m1).getPrice() + getMaindish(m2).getPrice() + getMaindish(m3).getPrice()
                + getFruit(f1).getPrice() + getSnack(s1).getPrice();
        //System.out.println("Cost:" + fitness);
        return fitness;
    }

    public static String convertBinary(int start, int end, Individual individual){
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
    public static Food getBeverage(String binary) {
        Food food;
        food = FoodData.matchBeverage(binary);
        if(food!=null)
            return food;
        else return null;
    }

    public static Food getMaindish(String binary) {
        Food food;
        food = FoodData.matchMaindish(binary);
        if(food!=null)
            return food;
        else return null;
    }

    public static Food getFruit(String binary) {
        Food food;
        food = FoodData.matchFruit(binary);
        if(food!=null)
            return food;
        else return null;
    }

    public static Food getSnack(String binary) {
        Food food;
        food = FoodData.matchSnack(binary);
        if(food!=null)
            return food;
        else return null;
    }
}
