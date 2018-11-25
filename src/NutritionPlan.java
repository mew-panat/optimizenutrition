import java.util.Scanner;
public class NutritionPlan {
    private static float BMR = 0.0f;
    public static float energy;
    public static float lb_protein;
    public static float ub_protein;
    public static float lb_carb;
    public static float ub_carb;
    public static float lb_fat;
    public static float ub_fat;
    public static float lb_sugar;
    public static float ub_sugar;
    public static float lb_calcium;
    public static float ub_calcium;
    public static float fiber;

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
        System.out.println("Your BMR = " + BMR + " calories");

        calConstraint();
    }

    private static void calConstraint(){
        energy = BMR;
        lb_protein = 0.1f * energy / 4;
        ub_protein = 0.35f * energy / 4;
        lb_carb = 0.45f * energy / 4;
        ub_carb = 0.65f * energy / 4;
        lb_fat = energy * 0.2f / 9;
        ub_fat = energy * 0.35f / 9;
        lb_sugar = 24;
        ub_sugar = 60;
        fiber = energy * 14 / 1000;
        lb_calcium = 1000;
        ub_calcium = 1500;

        System.out.println("\n--- Your nutrition need per day ---\n"
                + lb_carb + " <= Carbohydrate <= " + ub_carb + "\n"
                + lb_protein + " <= Protein <= " + ub_protein + "\n"
                + lb_fat + " <= Fat <= " + ub_fat + "\n"
                + lb_sugar + " <= Sugar <= " + ub_sugar + "\n"
                + lb_calcium + " <= Calcium <= " + ub_calcium + "\n"
                + "Fiber < " + fiber + "\n");
    }

    private static void setFood(){
        FoodData.readFood();
    }

    public static void main(String[] args) {
        int generation = 2000;
        Individual fittest;
        getInput();
        setFood();
        //initial population
        Population myPop = new Population(100, true);
        //print population
        //myPop.printPopulation();
        for(int i=0 ; i<generation ;i++) {
            myPop.getFittest().getFitness();
            //System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness());
            myPop = GA.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Total cost:");
        fittest = myPop.getFittest();
        System.out.println(fittest.getFitness());
        myPop.printOptimalFood();
    }
}
