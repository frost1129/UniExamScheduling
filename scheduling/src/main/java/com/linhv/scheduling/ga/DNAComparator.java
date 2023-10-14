package com.linhv.scheduling.ga;

import java.util.Comparator;

public class DNAComparator implements Comparator<DNA> {
    public static DNAComparator obj = new DNAComparator();

    public DNAComparator() {}

//    1: o2 smaller; -1: o1 smaller; 0: equal
    @Override
    public int compare(DNA o1, DNA o2) {
        int o1Fitness = o1.getFitness();
        int o2Fitness = o2.getFitness();

        if (o1Fitness < o2Fitness)
            return -1;
        else if (o1Fitness > o2Fitness)
            return 1;
        else
            return 0;
    }
}
