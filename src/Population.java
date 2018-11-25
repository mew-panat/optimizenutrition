import java.util.ArrayList;
import java.util.List;

public class Population {
    Individual[] individuals;
    final static int geneLength = 31;
    private float energy = 0;
    private float carb = 0;
    private float protein = 0;
    private float fat = 0;
    private float sugar = 0;
    private float calcium = 0;
    private float fiber = 0;

    List<Food> selectBeverage = new ArrayList<>();
    List<Food> selectMaindish = new ArrayList<>();
    List<Food> selectFruit = new ArrayList<>();
    List<Food> selectSnack = new ArrayList<>();
    List<Food> optimal = new ArrayList<>();

    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        if (initialise) {
            for (int i = 0; i < populationSize; i++) {
                individuals[i] = new Individual();
                individuals[i].generateIndividual();
            }
        }
    }

    public void printPopulation(){
        for(int i=0; i<40;i++) {
            for (int j = 0; j < geneLength; j++) {
                System.out.print(individuals[i].getGene(j));
            }
            System.out.println();
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public int size() {
        return individuals.length;
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < individuals.length; i++) {
            Individual individual = individuals[i];
//            System.out.println("individual " + individual.getFitness());
//            System.out.println("Fittest " + fittest.getFitness());
            if (individual.getFitness() < fittest.getFitness()) {
                if(!isDuplicateFood(individual));
                {
                    if(checkConstraints())
                        fittest = individual;
                }
            }
        }
        getOptimalFood(fittest);
        return fittest;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

    public List<Food> getOptimalFood(Individual fittest){
       optimal.clear();

        FitnessCalc fit = new FitnessCalc();
        String b1 = fit.convertBinary(0, 4, fittest);
        String b2 = fit.convertBinary(4, 8, fittest);
        String m1 = fit.convertBinary(8, 13, fittest);
        String m2 = fit.convertBinary(13, 18, fittest);
        String m3 = fit.convertBinary(18, 23, fittest);
        String f1 = fit.convertBinary(23, 27, fittest);
        String s1 = fit.convertBinary(27, 31, fittest);

        optimal.add(fit.getBeverage(b1));
        optimal.add(fit.getBeverage(b2));
        optimal.add(fit.getMaindish(m1));
        optimal.add(fit.getMaindish(m2));
        optimal.add(fit.getMaindish(m3));
        optimal.add(fit.getFruit(f1));
        optimal.add(fit.getSnack(s1));
        optimal.add(summariseOptimalFood());

        return optimal;

    }
    public Food summariseOptimalFood(){
        String binary = "xxxxx";
        String name = "Total cost";
        energy = 0;
        carb = 0;
        protein = 0;
        fat = 0;
        sugar = 0;
        calcium = 0;
        fiber = 0;
        float price = 0;
        float cookingTime = 0;
        for(Food food : optimal) {
            energy += food.getEnergy();
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
            price += food.getPrice();
            cookingTime += food.getCookingTime();
        }

        Food sum = new Food(binary,name,energy,carb,protein,fat,sugar,calcium,fiber,price,cookingTime);
        return sum;
    }

    public void printOptimalFood(){
        System.out.println("--- Foods ---");
        for(Food food : optimal)
            System.out.println(food.getName() + " " + food.getPrice());

        System.out.println("\n--- Your Nutrition ---\n"
                + "Total calories: " + energy + "\n"
                + "Carbohydrate: " + carb + "\n"
                + "Protein: " + protein + "\n"
                + "Fat: " + fat + "\n"
                + "Sugar: " + sugar + "\n"
                + "Calcium: " + calcium + "\n"
                + "Fiber: " + fiber + "\n");

    }

    public boolean range(float i, float LB, float UB){
        return i >= LB && i <= UB;
    }

    public boolean checkConstraints(){
        if(selectBeverage.size()!=2 || selectMaindish.size()!=3 || selectFruit.size()!=1 || selectSnack.size()!=1)
            return false;

        float carb = 0;
        float protein = 0;
        float fat = 0;
        float sugar = 0;
        float calcium = 0;
        float fiber = 0;
        for (Food food : selectBeverage) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : selectMaindish) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : selectFruit) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : selectSnack) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        //return true if all constraints are in range
        return (range(protein, NutritionPlan.lb_protein, NutritionPlan.ub_protein))
                && (range(carb, NutritionPlan.lb_carb, NutritionPlan.ub_carb))
                && (range(fat, NutritionPlan.lb_fat, NutritionPlan.ub_fat))
                && (range(sugar, NutritionPlan.lb_sugar, NutritionPlan.ub_sugar))
                && (fiber < NutritionPlan.fiber)
                && (range(calcium, NutritionPlan.lb_calcium, NutritionPlan.ub_calcium));
    }

    public boolean isDuplicateFood(Individual individual){
        selectBeverage.clear();
        selectMaindish.clear();
        selectFruit.clear();
        selectSnack.clear();


        boolean duplicateFruit = false;
        boolean duplicateSnack = false;
        FitnessCalc fit = new FitnessCalc();
        String b1 = fit.convertBinary(0, 4, individual);
        String b2 = fit.convertBinary(4, 8, individual);
        String m1 = fit.convertBinary(8, 13, individual);
        String m2 = fit.convertBinary(13, 18, individual);
        String m3 = fit.convertBinary(18, 23, individual);
        String f1 = fit.convertBinary(23, 27, individual);
        String s1 = fit.convertBinary(27, 31, individual);

        // check duplicate beverage b1
        boolean duplicateBeverage = false;
        for (Food food : selectBeverage) {
            if (food.equals(fit.getBeverage(b1))) {
                //System.out.println("Duplicate Beverage!");
                duplicateBeverage = true;
            }
        }
        if(!duplicateBeverage) selectBeverage.add(fit.getBeverage(b1));

        // check duplicate beverage b2
        duplicateBeverage = false;
        for (Food food : selectBeverage) {
            if (food.equals(fit.getBeverage(b2))) {
                //System.out.println("Duplicate Beverage!");
                duplicateBeverage = true;
            }
        }
        if(!duplicateBeverage) selectBeverage.add(fit.getBeverage(b2));

        // check duplicate main dish m1
        boolean duplicateMaindish = false;
        for (Food food : selectMaindish) {
            if (food.equals(fit.getMaindish(m1))) {
                //System.out.println("Duplicate Main dish!");
                duplicateMaindish = true;
            }
        }
        if(!duplicateMaindish) selectMaindish.add(fit.getMaindish(m1));

        // check duplicate main dish m2
        duplicateMaindish = false;
        for (Food food : selectMaindish) {
            if (food.equals(fit.getMaindish(m2))) {
                //System.out.println("Duplicate Main dish!");
                duplicateMaindish = true;
            }
        }
        if(!duplicateMaindish) selectMaindish.add(fit.getMaindish(m2));

        // check duplicate main dish m3
        duplicateMaindish = false;
        for (Food food : selectMaindish) {
            if (food.equals(fit.getMaindish(m3))) {
                //System.out.println("Duplicate Main dish!");
                duplicateMaindish = true;
            }
        }
        if(!duplicateMaindish) selectMaindish.add(fit.getMaindish(m3));

        // check duplicate fruit f1
        for (Food food : selectFruit) {
            if (food.equals(fit.getFruit(f1))) {
                //System.out.println("Duplicate Fruit!");
                duplicateFruit = true;
            }
        }
        if(!duplicateFruit) selectFruit.add(fit.getFruit(f1));

        // check duplicate fruit s1
        for (Food food : selectSnack) {
            if (food.equals(fit.getSnack(s1))) {
                //System.out.println("Duplicate Snack!");
                duplicateSnack = true;
            }
        }
        if(!duplicateSnack) selectSnack.add(fit.getSnack(s1));

        //if one of duplicate flags are true, have duplicate food
        return !(duplicateBeverage || duplicateMaindish || duplicateFruit || duplicateSnack);
    }
}
