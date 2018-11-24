import java.util.Random;

public class Individual {
    public static final int geneLength = 31;
    int[] genes = new int[geneLength];
    private double fitness = 0;

    public void generateIndividual() {
        Random rn = new Random();
        for (int i = 0; i < geneLength; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
    }

    public int getGene(int index) {
        return genes[index];
    }
    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }
    public double getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }


}
