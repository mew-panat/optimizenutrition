import java.util.Scanner;
public class NutritionPlan {
    private static float BMR = 0.0f;
    public static float energy;
    public static float lb_protein;
    public static float lb_carb;
    public static float lb_fat;
    public static float ub_sugar;
    public static int generation;
    public static int populationSize;
    public static double crossoverRate;
    public static double mutationRate;

    public static void getInput(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your gender: m (men) / f (female) ");
        String gender = reader.nextLine();
        System.out.println("Enter your age in year: ");
        int age = reader.nextInt();
        System.out.println("Enter your weight in kilogram: ");
        float weight = reader.nextFloat();
        System.out.println("Enter your height in centimeter: ");
        int height = reader.nextInt();
        System.out.println("Enter your Lifestyle: ");
        System.out.println("\tpress 1 " + "sedentary\t\tLittle or no exercise");
        System.out.println("\tpress 2 " + "lightly active\tLight exercise (1-3 days/week)");
        System.out.println("\tpress 3 " + "moderately\t\tModerate exercise (3-5 days/week)");
        System.out.println("\tpress 4 " + "very active\t\tHeavy exercise (6-7 days/week)");
        System.out.println("\tpress 5 " + "extra active\tVery hard exercise & physical job or 2x training");
        int n = reader.nextInt();


        if(gender.equals("m")){
            BMR = 66.5f + (13.75f * weight) + (5.003f * height) - (6.755f * age);
        }
        else if(gender.equals("f")){
            BMR = 665.1f + (9.563f  * weight) + (1.85f  * height) - (4.676f  * age);
        }
        if(n == 1) BMR = BMR*1.2f;
        else if(n==2) BMR = BMR*1.375f;
        else if(n==3) BMR = BMR*1.55f;
        else if(n==4) BMR = BMR*1.725f;
        else if(n==5) BMR = BMR*1.9f;
        System.out.println("Your BMR = " + BMR + " calories");

        calConstraint();
    }
    public static void inputParameter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Number of Generetion: ");
        generation = reader.nextInt();
        System.out.println("Population size: ");
        populationSize = reader.nextInt();
        System.out.println("Crossover rate: ");
        crossoverRate = reader.nextDouble();
        System.out.println("Mutation rate: ");
        mutationRate = reader.nextDouble();
        reader.close();
        System.out.println("Processing....\n");
    }

//    private static void setParameter(int population, double crossRate, double mutaRate){
//        populationSize = population;
//        crossoverRate = crossRate;
//        mutationRate = mutaRate;
//    }
    private static void calConstraint(){
        energy = BMR;
        lb_protein = 0.25f * energy / 4;
        lb_carb = 0.45f * energy / 4;
        lb_fat = energy * 0.2f / 9;
        ub_sugar = 60;

        System.out.println("\n--- Your nutrition need per day ---\n"
                + " Carbohydrate > " + lb_carb + "\n"
                + " Protein > " + lb_protein + "\n"
                + " Fat > " + lb_fat + "\n"
                + " Sugar < " + ub_sugar + "\n");
    }

    public static void setFood(){
        FoodData.readFood();
    }

    public static void geneticAlgo() {
        int duplicate = 0;
        Individual fittest;
        //initial population
        Population myPop = new Population(populationSize, true);
        //print population
        //myPop.printPopulation();
        for (int i = 0; i < generation; i++) {
            float fitness = myPop.getFittest().getFitness();
            //System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness());
            myPop = GA.evolvePopulation(myPop);
            if (fitness == myPop.getFittest().getFitness()) {
                duplicate++;
                //System.out.println(duplicate);
            }
        }
        if (duplicate > generation) {
            System.out.println("There is no available menu for you");
        } else {
            System.out.println("Solution found!");
            System.out.println("Total cost:");
            fittest = myPop.getFittest();
            System.out.println(fittest.getFitness());
            myPop.printOptimalFood();
        }
    }

    public static void main(String[] args) {
        getInput();
        setFood();

//        System.out.println(" --- Brute Force Algorithm --- \n");
//        bruteforce.bruteForceAlgo();

        System.out.println(" --- Genetic Algorithm --- \n");
        inputParameter();
        geneticAlgo();
    }
}
