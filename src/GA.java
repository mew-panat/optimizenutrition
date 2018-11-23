public class GA {
    //GA parameter
    private static final double crossoverRate = 0.5;
    private static final double mutationRate = 0.8;
    private static final int selectionSize = 5;
    private static final boolean elitism = true;
    private static final int populationSize = 40;
    private static final int geneLength = 33;
    private static final int generation = 10;
    public static void printPopulation(Population pop){
        for(int i=0;i<populationSize;i++){
            for(int j=0 ;j<geneLength ;j++)
                System.out.print(pop.getIndividual(i).getGene(j));
            System.out.print("\n");
        }
    }

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


    public static void GeneticAlgo(){
        //initial population
        Population myPop = new Population(populationSize, true);

        //print population
       // printPopulation(myPop);

       for(int i=0;i<generation;i++){
           myPop = evolvePopulation(myPop);
       }
    }
}
