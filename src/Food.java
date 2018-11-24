public class Food {
    private String binary;
    private String name;
    private float energy;
    private float protein;
    private float carb;
    private float fat;
    private float sugar;
    private float calcium;
    private float fiber;
    private float price;
    private float cookingTime;

    public Food(String binary, String name, float energy, float carb, float protein, float fat, float sugar, float calcium, float fiber, float price, float cookingTime) {
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

    public float getProtein() {
        return protein;
    }

    public float getCarb() {
        return carb;
    }

    public float getFat() {
        return fat;
    }

    public float getSugar() {
        return sugar;
    }

    public float getCalcium() {
        return calcium;
    }

    public float getFiber() {
        return fiber;
    }

    public float getPrice() {
        return price;
    }

    public float getCookingTime() {
        return cookingTime;
    }
}
