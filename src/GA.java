public class GA {
    //GA parameter
    private static final double crossoverRate = 0.5;
    private static final double mutationRate = 0.8;
    private static final int selectionSize = 5;
    private static final boolean elitism = true;
    private static final int populationSize = 40;
    private static final int geneLength = 33;
    private static final int generation = 10;



    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);
        //Elitism
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }
        //Selection
        //Crossover
        //Mutation
        return newPopulation;
    }
}
