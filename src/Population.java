
public class Population {
    Individual[] individuals;
    final static int geneLength = 31;
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
            System.out.println("individual " + individual.getFitness());
            System.out.println("Fittest " + fittest.getFitness());
            if (individual.getFitness() <= fittest.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}
