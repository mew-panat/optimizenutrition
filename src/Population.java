import java.util.ArrayList;
import java.util.List;

public class Population {
    Individual[] individuals;
    final static int geneLength = 31;
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
            if (individual.getFitness() <= fittest.getFitness()) {
                fittest = individual;
            }
        }
        getOptimalFood(fittest);
        return fittest;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

    public void getOptimalFood(Individual fittest){
        FitnessCalc fit = new FitnessCalc();
        String b1 = fit.convertBinary(0, 4, fittest);
        String b2 = fit.convertBinary(4, 8, fittest);
        String m1 = fit.convertBinary(8, 13, fittest);
        String m2 = fit.convertBinary(13, 18, fittest);
        String m3 = fit.convertBinary(18, 23, fittest);
        String f1 = fit.convertBinary(23, 27, fittest);
        String s1 = fit.convertBinary(27, 31, fittest);

        beverage.add(fit.getBeverage(b1));
        beverage.add(fit.getBeverage(b2));
        mainDish.add(fit.getMaindish(m1));
        mainDish.add(fit.getMaindish(m2));
        mainDish.add(fit.getMaindish(m3));
        fruit.add(fit.getFruit(f1));
        snack.add(fit.getSnack(s1));
    }

    public void printOptimalFood(){
        for(Food food : beverage)
            System.out.println("Beverage: " + food.getName() + " " + food.getPrice());
        for(Food food : mainDish)
            System.out.println("Main dish: " + food.getName() + " " + food.getPrice());
        for(Food food : fruit)
            System.out.println("Fruit: " + food.getName() + " " + food.getPrice());
        for(Food food : snack)
            System.out.println("Snack: " + food.getName() + " " + food.getPrice());
    }
}
