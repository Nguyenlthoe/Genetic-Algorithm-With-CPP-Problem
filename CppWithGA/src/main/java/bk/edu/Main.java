package bk.edu;

import bk.edu.ga.GAFunction;
import bk.edu.ga.Populations;
import bk.edu.model.Path;
import bk.edu.myfunction.MyFunction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Populations populations = new Populations();
        populations.initPopulations(100);
        populations.getParent().get(0).printPath();
//        populations.displayPopulations();
//        System.out.println("----------------------------");
        System.out.println(populations.getMinFitness());
//        System.out.println("----------------------------");
        for(int i = 0; i < 200; i++){
            populations.updatePopulations();
//            populations.displayPopulations();
//            System.out.println("----------------------------");
            System.out.println(populations.getMinFitness());
//            System.out.println("----------------------------");
        }
        populations.getParent().get(0).printPath();
    }
}