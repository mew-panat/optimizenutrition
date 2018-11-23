public class Individual {
    static int geneLength = 33;
    private static int[] genes = new int[geneLength];
    private double fitness = 0;

    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            int gene = (int) Math.round(Math.random()%2);
            genes[i] = gene;
        }
    }
    public int size() {
        return genes.length;
    }

    public static int getGene(int index) {
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
