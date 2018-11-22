import java.util.ArrayList;
import java.util.List;
public class bruteforce{

    private static List<InitFood> selectedBeverage = new ArrayList<>();
    private static List<InitFood> selectedMainDish = new ArrayList<>();
    private static List<InitFood> selectedFruit = new ArrayList<>();
    private static List<InitFood> selectedSnack = new ArrayList<>();

    private static List<InitFood> optimalBeverage = new ArrayList<>();
    private static List<InitFood> optimalMainDish = new ArrayList<>();
    private static List<InitFood> optimalFruit = new ArrayList<>();
    private static List<InitFood> optimalSnack = new ArrayList<>();
    private static List<InitFood> optimal = new ArrayList<>();
    private static boolean range(double i, double LB, double UB){
        return i >= LB && i <= UB;
    }
    private static int getDayPrice(){
        int price = 0;
        for (InitFood f : selectedBeverage) {
            price += f.getPrice();
        }
        for (InitFood d : selectedMainDish) {
            price += d.getPrice();
        }
        for (InitFood d : selectedFruit) {
            price += d.getPrice();
        }
        for (InitFood d : selectedSnack) {
            price += d.getPrice();
        }
        return price;
    }
    private static boolean checkConstraints() {
        double protein = 0.0;
        double carb = 0.0;
        double fat = 0.0;
        double sugar = 0.0;
        double calcium = 0.0;
        double fiber = 0.0;
        for (InitFood f : selectedBeverage) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (InitFood f : selectedMainDish) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (InitFood f : selectedFruit) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (InitFood f : selectedSnack) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }

        // if violate any constraint - return false
        return range(protein, NutritionMain.Lb_protein, NutritionMain.Ub_protein)
                && (!range(carb, NutritionMain.Lb_carb, NutritionMain.Ub_carb))
                && (!range(fat, NutritionMain.Lb_fat, NutritionMain.Ub_fat))
                && (!range(sugar, NutritionMain.Lb_sugar, NutritionMain.Ub_sugar))
                && (!(fiber < NutritionMain.fiber))
                && (!range(calcium, NutritionMain.Lb_calcium, NutritionMain.Ub_calcium));
    }
    public static void bruteForceAlgo(){
        NutritionMain.inputFromUser();
        FoodData food = new FoodData();
        food.readData();
        //System.out.println(FoodData.getAllFoods());

        selectedBeverage.clear();
        selectedMainDish.clear();
        selectedFruit.clear();
        selectedSnack.clear();

        optimalBeverage.clear();
        optimalMainDish.clear();
        optimalFruit.clear();
        optimalSnack.clear();
        optimal.clear();

        List<InitFood> beverage = FoodData.getBeverage();
        List<InitFood> maindish = FoodData.getMainDish();
        List<InitFood> fruit = FoodData.getFruit();
        List<InitFood> snack = FoodData.getSnack();
        int beverageCount = 0;
        int maindishCount = 0;
        int fruitCount = 0;
        int snackCount = 0;
        List<String> beverageMeal = new ArrayList<>();
        List<String> maindishMeal = new ArrayList<>();
        List<String> fruitMeal = new ArrayList<>();
        List<String> snackMeal = new ArrayList<>();

        //select 2 beverage
        for (int i = 0; i < beverage.size(); i++) {
            for (int j = i + 1; j < beverage.size(); j++) {
                beverageCount++;
                beverageMeal.add(beverage.get(i).getBinary() + beverage.get(j).getBinary());
            }
        }
        //select 3 maindish
        for (int i = 0; i < maindish.size(); i++)
            for (int j = i + 1; j < maindish.size(); j++)
                for (int k = j + 1; k < maindish.size(); k++) {
                    maindishCount++;
                    maindishMeal.add(maindish.get(i).getBinary() + maindish.get(j).getBinary() + maindish.get(k).getBinary());
                }
        //select 1 fruit
        for (InitFood aFruit : fruit) {
            fruitCount++;
            fruitMeal.add(aFruit.getBinary());
        }
        //select 1 snack
        for (InitFood aSnack : snack) {
            snackCount++;
            snackMeal.add(aSnack.getBinary());
        }

        System.out.println(beverageCount + " " + maindishCount + " " + fruitCount + " " + snackCount);

        double price;
        double minPrice = 3000;
        for (int i = 0; i < beverageCount; i++) {
            System.out.print("*");
            selectedBeverage.clear();
            selectedMainDish.clear();
            selectedFruit.clear();
            selectedSnack.clear();
            for(int j = 0; j < maindishCount; j++){
                System.out.print("#");
                for(int k = 0 ; k < fruitCount; k++){
                    System.out.print("+");
                    for(int m = 0; m < snackCount; m++){
                        System.out.print("-");
                        InitFood b1;
                        InitFood b2;
                        InitFood m1;
                        InitFood m2;
                        InitFood m3;
                        InitFood f1;
                        InitFood s1;
                        b1 = FoodData.matchBeverage(beverageMeal.get(i).substring(0, 5));
                        b2 = FoodData.matchBeverage(beverageMeal.get(i).substring(5, 10));
                        m1 = FoodData.matchMainDish(maindishMeal.get(j).substring(0, 5));
                        m2 = FoodData.matchMainDish(maindishMeal.get(j).substring(5, 10));
                        m3 = FoodData.matchMainDish(maindishMeal.get(j).substring(10, 15));
                        f1 = FoodData.matchFruit(fruitMeal.get(k).substring(0, 4));
                        s1 = FoodData.matchSnack(snackMeal.get(m).substring(0, 4));

                        selectedBeverage.add(b1);
                        selectedBeverage.add(b2);
                        selectedMainDish.add(m1);
                        selectedMainDish.add(m2);
                        selectedMainDish.add(m3);
                        selectedFruit.add(f1);
                        selectedSnack.add(s1);

                        //check constraints
                        if(checkConstraints()) {
                            price = getDayPrice();
                            if (price < minPrice) {
                                optimalBeverage.clear();
                                optimalMainDish.clear();
                                optimalFruit.clear();
                                optimalSnack.clear();

                                minPrice = price;
                                optimalBeverage.add(b1);
                                optimalBeverage.add(b2);
                                optimalMainDish.add(m1);
                                optimalMainDish.add(m2);
                                optimalMainDish.add(m3);
                                optimalFruit.add(f1);
                                optimalSnack.add(s1);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Minimum is " + minPrice);
        if (optimalBeverage.size() > 0) {
            System.out.println("2 Beverages: " + optimalBeverage.get(0).getName() + optimalBeverage.get(1).getName()
                    + "\n3 Main Dishes: " + optimalMainDish.get(0).getName() + " " + optimalMainDish.get(1).getName()
                    + " " + optimalMainDish.get(2).getName()
                    + "\n 1 Fruit: " + optimalFruit.get(0).getName() + "\n 1 Snack: " + optimalSnack.get(0).getName());
        } else {
            System.out.println("There is no available menu for you");
        }
    }
}




