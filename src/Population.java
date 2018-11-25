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
//           System.out.println("individual " + individual.getFitness());
//           System.out.println("Fittest " + fittest.getFitness());
            if (individual.getFitness() < fittest.getFitness()) {
                if(isValid(individual)) {
                    if(checkConstraints()) {
                            fittest = individual;
                    }
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

    public boolean isValid(Individual individual){
        selectBeverage.clear();
        selectMaindish.clear();
        selectFruit.clear();
        selectSnack.clear();
        FitnessCalc fit = new FitnessCalc();
        String b1 = fit.convertBinary(0, 4, individual);
        String b2 = fit.convertBinary(4, 8, individual);
        String m1 = fit.convertBinary(8, 13, individual);
        String m2 = fit.convertBinary(13, 18, individual);
        String m3 = fit.convertBinary(18, 23, individual);
        String f1 = fit.convertBinary(23, 27, individual);
        String s1 = fit.convertBinary(27, 31, individual);

        if(isDuplicateBeverage(b1) || isDuplicateBeverage(b2)
                || isDuplicateMaindish(m1) || isDuplicateMaindish(m2) || isDuplicateMaindish(m3)
                || isDuplicateFruit(f1) || isDuplicateSnack(s1))
            return false;
        return true;
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
                + "Sugar: " + sugar + "\n");
    }

    public boolean checkConstraints(){
        //System.out.print("8888");
        if(selectBeverage.size()!=2 || selectMaindish.size()!=3 || selectFruit.size()!=1 || selectSnack.size()!=1)
            return false;

        float carb = 0;
        float protein = 0;
        float fat = 0;
        float sugar = 0;
        for (Food food : selectBeverage) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
        }
        for (Food food : selectMaindish) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
        }
        for (Food food : selectFruit) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
        }
        for (Food food : selectSnack) {
            carb += food.getCarb();
            protein += food.getProtein();
            fat += food.getFat();
            sugar += food.getSugar();
        }
        //return true if all constraints are in range
        if(carb < NutritionPlan.lb_carb || protein < NutritionPlan.lb_protein
                || fat < NutritionPlan.lb_fat || sugar > NutritionPlan.ub_sugar)
            return false;
        return true;
    }

    public boolean isDuplicateBeverage(String beverage){
        FitnessCalc fit = new FitnessCalc();

        for (Food food : selectBeverage) {
            if (food.getBinary().equals(beverage)) {
                //System.out.println("Duplicate Beverage!");
                return true;
            }
        }
        selectBeverage.add(fit.getBeverage(beverage));
        return false;
    }
    public boolean isDuplicateMaindish(String maindish){
        FitnessCalc fit = new FitnessCalc();
        for (Food food : selectMaindish) {
            if (food.getBinary().equals(maindish)) {
                //System.out.println("Duplicate Main dish!");
                return true;
            }
        }
        selectMaindish.add(fit.getMaindish(maindish));
        return false;
    }
    public boolean isDuplicateFruit(String fruit){
        FitnessCalc fit = new FitnessCalc();
        for (Food food : selectFruit) {
            if (food.getBinary().equals(fruit)) {
                //System.out.println("Duplicate Fruit!");
                return true;
            }
        }
        selectFruit.add(fit.getFruit(fruit));
        return false;
    }
    public boolean isDuplicateSnack(String snack){
        FitnessCalc fit = new FitnessCalc();
        for (Food food : selectSnack) {
            if (food.getBinary().equals(snack)) {
                //System.out.println("Duplicate Snack!");
                return true;
            }
        }
        selectSnack.add(fit.getSnack(snack));
        return false;
    }
}
