public class Food {
    private String binary;
    private String name;
    private float energy;
    private double protein;
    private double carb;
    private double fat;
    private double sugar;
    private double calcium;
    private double fiber;
    private double price;
    private double cookingTime;

    public Food(String binary, String name, float energy, double carb, double protein, double fat, double sugar, double calcium, double fiber, double price, double cookingTime) {
        this.binary = binary;
        this.name = name;
        this.energy = energy;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
        this.sugar = sugar;
        this.calcium = calcium;
        this.fiber = fiber;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    public String toString() {
        return binary + " " + name + " " + price;
    }

    public String getBinary() {
        return binary;
    }

    public String getName() {
        return name;
    }

    public double getEnergy() {
        return energy;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarb() {
        return carb;
    }

    public double getFat() {
        return fat;
    }

    public double getSugar() {
        return sugar;
    }

    public double getCalcium() {
        return calcium;
    }

    public double getFiber() {
        return fiber;
    }

    public double getPrice() {
        return price;
    }

    public double getCookingTime() {
        return cookingTime;
    }
}
