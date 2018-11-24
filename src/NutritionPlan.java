import java.util.Scanner;
public class NutritionPlan {
    public static final int populationSize = 40;
    private static float BMR = 0.0f;

    private static void getInput(){
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
        reader.close();

        if(gender.equals("m")){
            BMR = 66.5f + (13.75f * weight) + (5.003f * height) - (6.755f * age);
        }
        else if(gender.equals("f")){
            BMR = 665.1f + (9.563f  * weight) + (1.85f  * height) - (4.676f  * age);
        }
        if(n == 1) BMR = BMR*1.2f;
        else if(n==2) BMR = 1.375f;
        else if(n==3) BMR = 1.55f;
        else if(n==4) BMR = 1.725f;
        else if(n==5) BMR = 1.9f;
        System.out.println("Your BMR = " + BMR);

        calConstraint();
    }

    private static void calConstraint(){
        float energy = BMR;
        double lb_protein = 0.1 * energy / 4;
        double ub_protein = 0.35 * energy / 4;
        double lb_carb = 0.45 * energy / 4;
        double ub_carb = 0.65 * energy / 4;
        double lb_fat = energy * 0.2 / 9;
        double ub_fat = energy * 0.35 / 9;
        double lb_sugar = 24;
        double ub_sugar = 60;
        double fiber = energy * 14 / 1000;
        double lb_calcium = 1000;
        double ub_calcium = 1500;
    }

    private static void setFood(){
        FoodData.readFood();
    }

    public static void main(String[] args) {
        double fitness;
        Individual fittest;
        //getInput();
        setFood();
        //initial population
        Population myPop = new Population(40, true);
        //print population
        myPop.printPopulation();
        fittest = myPop.getFittest();
        fitness = fittest.getFitness();
        System.out.println("fitness " + fitness);
        myPop.printOptimalFood();
        myPop = GA.evolvePopulation(myPop);

    }
}
