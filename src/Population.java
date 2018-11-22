import java.util.Random;
public class Population {
    final static int popSize = 40;
    final static int geneLength = 33;
    final static int Elitism = 15;
    final static int MAX_ITER = 100;            // max number of iterations
    final static double mutationRate = 0.8;    // probability of mutation
    final static double crossoverRate = 1;     // probability of crossover
    private static Random m_rand = new Random();  // random-number generator
    private static Individual[] population;
    private double totalFitness;
    int fittest = 0;

    //Initialize population
    Population() {
        population = new Individual[popSize];
        for (int i = 0; i < popSize; i++) {
            population[i] = new Individual();
            population[i].randGenes();
        }
        //this.evaluate();
    }

    static void printPopulation(int n){
        Individual individual = population[n];
        for(int i=0; i<geneLength ; i++){
            System.out.print(individual.getGenes(i));
        }
    }
    private void copyPopulation(Individual[] newPop){
        System.arraycopy(newPop, 0, population, 0, popSize);
    }

    public Individual[] getPopulation() {
        return population;
    }

    public Individual findBestIndividual() {
        int minIndex = 0;
        double min = 1.0;
        double value;

        for (int i = 0; i < popSize; i++) {
            value = population[i].getFitnessValue();
            if (value < min) {
                min = value;
                minIndex = i;
            }
        }
        return population[minIndex];      // minimization
    }

    private Individual Selection() {
        double randNum = m_rand.nextDouble() * this.totalFitness;
        int idx;
        for (idx=0; idx<popSize && randNum>0; ++idx) {
            randNum -= population[idx].getFitnessValue();
        }
        return population[idx];
    }

    private static Individual[] crossover(Individual indiv1,Individual indiv2) {
        Individual[] newIndiv = new Individual[2];
        newIndiv[0] = new Individual();
        newIndiv[1] = new Individual();

        int randPoint = m_rand.nextInt(Individual.chorm);
        int i;
        for (i=0; i<randPoint; ++i) {
            newIndiv[0].setGene(i, indiv1.getGenes(i));
            newIndiv[1].setGene(i, indiv2.getGenes(i));
        }
        for (; i<Individual.chorm; ++i) {
            newIndiv[0].setGene(i, indiv2.getGenes(i));
            newIndiv[1].setGene(i, indiv1.getGenes(i));
        }

        return newIndiv;
    }

    public double evaluate() {
        this.totalFitness = 0.0;
        for (int i = 0; i < popSize; i++) {
            this.totalFitness += population[i].evaluate();
        }
        return this.totalFitness;
    }

    public double finalEvaluate() {
        this.totalFitness = 0.0;
        for (int i = 0; i < popSize; i++) {
            this.totalFitness += population[i].finalEvaluate();
        }
        return this.totalFitness;
    }

    public static Individual GA(){
        Population pop = new Population();
        Individual[] newPop = new Individual[popSize];
        Individual[] indiv = new Individual[2];
        int duplicate = 0;

        // main loop
        int count;
        for (int iter = 0; iter < MAX_ITER; iter++) {
            System.out.println( "==================" + " Generation " + iter + "==================");

            count = 0;
            // Elitism
            for (int i=0; i<Elitism; ++i) {
                System.out.println("Count = " + count);
                if(count<=Elitism) {
                    newPop[count] = pop.findBestIndividual();
                    count++;
                }
                System.out.println("eilism");
            }

            // build new Population
            while (count < popSize) {
                boolean valid = false;
                duplicate = 0;

                while (!valid) {


                    // Selection
                    indiv[0] = pop.Selection();
                    indiv[1] = pop.Selection();
                    // Crossover
                    if (m_rand.nextDouble() < crossoverRate) {
                        indiv = crossover(indiv[0], indiv[1]);
                    }

                    // Mutation
                    if (m_rand.nextDouble() < mutationRate) {
                        indiv[0].mutate();
                    }
                    if (m_rand.nextDouble() < mutationRate) {
                        indiv[1].mutate();
                    }

                    // invalid indiv[0]
                    if ((!indiv[0].isValidPopulation()) || (!indiv[0].checkConstraints())) {
                        valid = false;
                    } else if ((!indiv[1].isValidPopulation()) || (indiv[1].checkConstraints())) {  // invalid indiv[1]
                        valid = false;
                    } else {    // both indiv are valid
                        valid = true;
                    }

                    duplicate++;

                    System.out.println("duplicate = " + duplicate);
                    if (duplicate == 1000)
                    {
                        System.out.println("STOP");
                        break;
                    }
                }
                if (duplicate == 1000)
                {
                    break;
                }

                // add to new population
                newPop[count] = indiv[0];
                newPop[count+1] = indiv[1];
                count += 2;
                System.out.println("count = " + count);
            }

            if (duplicate == 1000)
            {
                break;
            }

            pop.copyPopulation(newPop);

            // For the last ten generations - start checking for constraints
            if (iter > MAX_ITER-10) {
                pop.finalEvaluate();
            } else {
                pop.evaluate();
            }

            // last generation - print check
            if (iter == MAX_ITER-1)
                for (int i = 0; i < popSize; i++) {
                    printPopulation(i);
                    System.out.println("; fitness = " + pop.getPopulation()[i].getFitnessValue());
                }
        }

        if (duplicate != 1000)
        {
            // best indiv
            return pop.findBestIndividual();
        }
        else
        {
            return null;
        }

    }

        //elitism
        //selection
        //crossover
        //mutation
        //addOffspring to the new gen

}

