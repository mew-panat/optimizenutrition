import com.sun.corba.se.impl.naming.cosnaming.NamingUtils;

import java.util.Random;
import java.util.Scanner;

public class NutritionMain{
    private static int popSize = 40;
    private static int age;
    private static float weight;
    private static int height;
    private static String gender;
    private static float BMR = 0.0f;
    public static double energy;
    static double Lb_protein;
    static double Ub_protein;
    static double Lb_carb;
    static double Ub_carb;
    static double Lb_fat;
    static double Ub_fat;
    static double Lb_sugar;
    static double Ub_sugar;
    static double Lb_calcium;
    static double Ub_calcium;
    public static double fiber;

    private static void printPop(){
        Population nutrition = new Population();
        for(int i=0;i<popSize;i++) {
            Population.printPopulation(i);
            System.out.println();
        }
    }
    static void inputFromUser(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your gender: m (men) / f (female) ");
        gender = reader.nextLine();
        System.out.println("Enter your age in year: ");
        age = reader.nextInt();
        System.out.println("Enter your weight in kilogram: ");
        weight = reader.nextFloat();
        System.out.println("Enter your height in centimeter: ");
        height = reader.nextInt();
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
        energy = BMR;
        Lb_protein = 0.1*energy/4;
        Ub_protein = 0.35*energy/4;
        Lb_carb = 0.45*energy/4;
        Ub_carb = 0.65*energy/4;
        Lb_fat = energy*0.2/9;
        Ub_fat = energy*0.35/9;
        Lb_sugar = 24;
        Ub_sugar = 60;
        fiber = energy*14/1000;
        Lb_calcium = 1000;
        Ub_calcium = 1500;
    }

    public static void main(String[] args) {
        FoodData food = new FoodData();
        food.readData();
        food.getData();

        printPop();

        Individual bestIndiv = Population.GA();
    }
}
