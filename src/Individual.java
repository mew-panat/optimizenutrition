import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Individual {
    public static final int chorm = 33;
    int[] genes = new int[chorm];
    private int fitnessValue;

    private static List<InitFood> optimal = new ArrayList<>();
    private List<Integer> totalFitness = new ArrayList<>();
    private List<InitFood> selectedBeverage = new ArrayList<>();
    private List<InitFood> selectedMainDish = new ArrayList<>();
    private List<InitFood> selectedFruit = new ArrayList<>();
    private List<InitFood> selectedSnack = new ArrayList<>();
    Population pop = new Population();
    Individual[] sss = pop.getPopulation();


    public void randGenes() {
        Random rn = new Random();
        for(int i=0; i<chorm; ++i) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
    }

    public int getGenes(int i){
        return genes[i];
    }
    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    public int getFitnessValue() {
        return fitnessValue;
    }
    public void setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public void mutate() {
        Random rand = new Random();
        int index = rand.nextInt(chorm);
        this.setGene(index, 1-this.getGenes(index));    // flip
    }

    public InitFood extractBeverage(int start, int end) {
        String[] a = new String[5];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(genes[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        String str = builder.toString();

        InitFood food = null;
        if(FoodData.matchBeverage(str)!=null)
            food = FoodData.matchBeverage(str);
        return food;
    }
    public InitFood extractMainDish(int start, int end) {
        String[] a = new String[5];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(genes[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        String str = builder.toString();

        InitFood food = null;
        if(FoodData.matchMainDish(str)!=null)
            food = FoodData.matchMainDish(str);
        return food;
    }
    public InitFood extractFruit(int start, int end) {
        String[] a = new String[4];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(genes[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        String str = builder.toString();

        InitFood food = null;
        if(FoodData.matchFruit(str)!=null)
            food = FoodData.matchFruit(str);
        return food;
    }
    public InitFood extractSnack(int start, int end) {
        String[] a = new String[4];

        for (int i = start; i < end; i++) {
            a[i-start] = String.valueOf(genes[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : a) {
            builder.append(s);
        }
        String str = builder.toString();

        InitFood food = null;
        if(FoodData.matchSnack(str)!=null)
            food = FoodData.matchSnack(str);
        return food;
    }

    public List<InitFood> getIndividual() {
        optimal.clear();
        InitFood b1 = extractBeverage(0, 5);
        InitFood b2 = extractBeverage(5, 10);
        InitFood m1 = extractMainDish(10, 15);
        InitFood m2 = extractMainDish(15, 20);
        InitFood m3 = extractMainDish(20, 25);
        InitFood f = extractFruit(25, 29);
        InitFood s = extractSnack(29, 33);

        optimal.add(b1);
        optimal.add(b2);
        optimal.add(m1);
        optimal.add(m2);
        optimal.add(m3);
        optimal.add(f);
        optimal.add(s);
        optimal.add(getOptimalSummarize());
        //cost = beverage.getPrice()+grain.getPrice()+meat.getPrice()+vegetable.getPrice()+fruit.getPrice()+snack.getPrice();
        //System.out.println("cost : " + cost);
        return optimal;
    }
    private static InitFood getOptimalSummarize() {
        String binary = "xxxxxx";
        String name = "Total";
        double energy = 0.0;
        double protein = 0.0;
        double carb = 0.0;
        double fat = 0.0;
        double sugar = 0.0;
        double calcium = 0.0;
        double fiber = 0.0;
        double price = 0.0;
        double cookingTime = 0.0;
        for (InitFood f : optimal) {
            energy += f.getCal();
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            price += f.getPrice();
            calcium += f.getCalcium();
            fiber += f.getFiber();
            cookingTime += f.getCookingTime();
        }
        InitFood sum = new InitFood(binary, name, energy, carb, protein, fat, sugar, calcium, fiber, price, cookingTime);
        return sum;
    }

    public int evaluate() {
        int fitness = 0;
        InitFood b1 = extractBeverage(0, 5);
        InitFood b2 = extractBeverage(5, 10);
        InitFood m1 = extractMainDish(10, 15);
        InitFood m2 = extractMainDish(15, 20);
        InitFood m3 = extractMainDish(20, 25);
        InitFood f = extractFruit(25, 29);
        InitFood s = extractSnack(29, 33);

        fitness += b1.getPrice();
        fitness += b2.getPrice();
        fitness += m1.getPrice();
        fitness += m2.getPrice();
        fitness += m3.getPrice();
        fitness += f.getPrice();
        fitness += s.getPrice();

        this.setFitnessValue(fitness);
        totalFitness.add(fitness);
        return fitness;
    }

    public int finalEvaluate() {
        int fitness = 0;

        InitFood b1 = extractBeverage(0, 5);
        InitFood b2 = extractBeverage(5, 10);
        InitFood m1 = extractMainDish(10, 15);
        InitFood m2 = extractMainDish(15, 20);
        InitFood m3 = extractMainDish(20, 25);
        InitFood f = extractFruit(25, 29);
        InitFood s = extractSnack(29, 33);

        fitness += b1.getPrice();
        fitness += b2.getPrice();
        fitness += m1.getPrice();
        fitness += m2.getPrice();
        fitness += m3.getPrice();
        fitness += f.getPrice();
        fitness += s.getPrice();

        if (!checkConstraints())
            fitness += 10000;

        this.setFitnessValue(fitness);
        totalFitness.add(fitness);
        return fitness;
    }

    public boolean isDuplicatedBeverage(InitFood randFood) {

        for (InitFood food : selectedBeverage)
            if (food.getBinary().equals(randFood.getBinary())) {
                return true;
            }
        // getting here means no duplicate, add this one to selected food
        selectedBeverage.add(randFood);
        return false;
    }
    public boolean isDuplicatedMainDish(InitFood randFood) {
        for (InitFood food : selectedMainDish) {
            if (randFood.getBinary().equals(food.getBinary())) {
                return true;
            }
        }
        // getting here means no duplicate, add this one to selected food
        selectedMainDish.add(randFood);
        return false;
    }
    public boolean isDuplicatedFruit(InitFood randFood) {

        for (InitFood food : selectedFruit){
            if (randFood.getBinary().equals(food.getBinary())) {
                return true;
            }
        }
        // getting here means no duplicate, add this one to selected food
        selectedFruit.add(randFood);
        return false;
    }
    public boolean isDuplicatedSnack(InitFood randFood) {

        for (InitFood food : selectedSnack){
            if (randFood.getBinary().equals(food.getBinary())) {
                return true;
            }
        }
        // getting here means no duplicate, add this one to selected food
        selectedSnack.add(randFood);
        return false;
    }

    public boolean checkConstraints() {
        // Don't need to calculate for this
        if ((selectedBeverage.size() != 2) || (selectedMainDish.size() != 3)
                || (selectedFruit.size() != 1) || (selectedSnack.size() != 1))
            return false;

        double protein = 0.0;
        double carb = 0.0;
        double fat = 0.0;
        double sugar = 0.0;
        double fiber = 0.0;
        double calcium = 0.0;

        for (InitFood b : selectedBeverage) {
            protein += b.getProtein();
            carb += b.getCarb();
            fat += b.getFat();
            sugar += b.getSugar();
            fiber += b.getFiber();
            calcium += b.getCalcium();
        }
        for (InitFood m : selectedMainDish) {
            protein += m.getProtein();
            carb += m.getCarb();
            fat += m.getFat();
            sugar += m.getSugar();
            fiber += m.getFiber();
            calcium += m.getCalcium();
        }
        for (InitFood f : selectedFruit) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (InitFood s : selectedSnack) {
            protein += s.getProtein();
            carb += s.getCarb();
            fat += s.getFat();
            sugar += s.getSugar();
            fiber += s.getFiber();
            calcium += s.getCalcium();
        }

        // if violate any constraint - return false
        return (range(protein, NutritionMain.Lb_protein, NutritionMain.Ub_protein))
                || (range(carb, NutritionMain.Lb_carb, NutritionMain.Ub_carb))
                || (range(fat, NutritionMain.Lb_fat, NutritionMain.Ub_fat))
                || (range(sugar, NutritionMain.Lb_sugar, NutritionMain.Ub_sugar))
                || (fiber < NutritionMain.fiber)
                || (range(calcium, NutritionMain.Lb_calcium, NutritionMain.Ub_calcium));
    }
    public static boolean range(double i, double LB, double UB){
        return i >= LB && i <= UB;
    }
    public boolean isValidPopulation(){
        selectedBeverage.clear();
        selectedSnack.clear();
        selectedMainDish.clear();
        selectedFruit.clear();
        InitFood b1 = extractBeverage(0, 5);
        InitFood b2 = extractBeverage(5, 10);
        InitFood m1 = extractMainDish(10, 15);
        InitFood m2 = extractMainDish(15, 20);
        InitFood m3 = extractMainDish(20, 25);
        InitFood f = extractFruit(25, 29);
        InitFood s = extractSnack(29, 33);

        // Penalty - set fitness for this individual high so, this individual won't be our solution
        return (!isDuplicatedBeverage(b1)) && (!isDuplicatedBeverage(b2))
                && (!isDuplicatedMainDish(m1)) && (!isDuplicatedMainDish(m2)) && (!isDuplicatedMainDish(m3))
                && (!isDuplicatedFruit(f)) && (!isDuplicatedSnack(s));
    }
}
