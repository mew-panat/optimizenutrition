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

    List<Food> beverage = new ArrayList<>();
    List<Food> mainDish = new ArrayList<>();
    List<Food> fruit = new ArrayList<>();
    List<Food> snack = new ArrayList<>();

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
                if(checkConstraints());
                {
                    fittest = individual;
                }
            }
        }
        getOptimalFood(fittest);
        summariseOptimalFood();
        return fittest;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

    public void getOptimalFood(Individual fittest){
        beverage.clear(); //edit here!!!!!!!
        mainDish.clear();
        fruit.clear();
        snack.clear();

        FitnessCalc fit = new FitnessCalc();
        String b1 = fit.convertBinary(0, 4, fittest);
        String b2 = fit.convertBinary(4, 8, fittest);
        String m1 = fit.convertBinary(8, 13, fittest);
        String m2 = fit.convertBinary(13, 18, fittest);
        String m3 = fit.convertBinary(18, 23, fittest);
        String f1 = fit.convertBinary(23, 27, fittest);
        String s1 = fit.convertBinary(27, 31, fittest);

        if(!isDuplicateFood(b1,b2) && !isDuplicateFood(m1,m2) && !isDuplicateFood(m1,m3) && !isDuplicateFood(m2,m3)) {
            beverage.add(fit.getBeverage(b1));
            beverage.add(fit.getBeverage(b2));
            mainDish.add(fit.getMaindish(m1));
            mainDish.add(fit.getMaindish(m2));
            mainDish.add(fit.getMaindish(m3));
            fruit.add(fit.getFruit(f1));
            snack.add(fit.getSnack(s1));
        }
    }
    public void summariseOptimalFood(){

        for(Food food : beverage) {
            energy += food.getEnergy();
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }

        for(Food food : mainDish){
            energy += food.getEnergy();
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for(Food food : fruit){
            energy += food.getEnergy();
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for(Food food : snack){
            energy += food.getEnergy();
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
    }

    public void printOptimalFood(){
        System.out.println("Beverages: ");
        for(Food food : beverage)
            System.out.println(food.getName() + " " + food.getPrice());
        System.out.println("Main dishes: ");
        for(Food food : mainDish)
            System.out.println(food.getName() + " " + food.getPrice());
        System.out.println("Fruits: ");
        for(Food food : fruit)
            System.out.println(food.getName() + " " + food.getPrice());
        System.out.println("Snacks: ");
        for(Food food : snack)
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
        if(beverage.size()!=2 || mainDish.size()!=3 || fruit.size()!=1 || snack.size()!=1)
            return false;

        float carb = 0;
        float protein = 0;
        float fat = 0;
        float sugar = 0;
        float calcium = 0;
        float fiber = 0;
        for (Food food : beverage) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : mainDish) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : fruit) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        for (Food food : snack) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
            calcium += food.getCalcium();
            fiber += food.getFiber();
        }
        return (range(protein, NutritionPlan.lb_protein, NutritionPlan.ub_protein))
                && (range(carb, NutritionPlan.lb_carb, NutritionPlan.ub_carb))
                && (range(fat, NutritionPlan.lb_fat, NutritionPlan.ub_fat))
                && (range(sugar, NutritionPlan.lb_sugar, NutritionPlan.ub_sugar))
                && (fiber < NutritionPlan.fiber)
                && (range(calcium, NutritionPlan.lb_calcium, NutritionPlan.ub_calcium));
    }

    public boolean isDuplicateFood(String a, String b){
        return a.equals(b);
    }
}
