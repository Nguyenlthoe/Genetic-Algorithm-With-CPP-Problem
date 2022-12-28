package bk.edu.ga;

import bk.edu.model.Matrix;
import bk.edu.model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GAFunction {
    public static List<Path> crossOver(Path parent1, Path parent2){
        List<Path> childPath = new ArrayList<>();
        List<Integer> chromosome = randomOperator(parent1.getOperator().size());
//        System.out.println(chromosome.get(0));
//        System.out.println(chromosome.get(1));
        Path child1 = new Path();
        Path child2 = new Path();
        List<Integer> operator1 = parent1.getOperator();
        List<Integer> operator2 = parent2.getOperator();
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();
        for(int i = chromosome.get(0); i <= chromosome.get(1); i++){
            part1.add(operator1.get(i));
            part2.add(operator2.get(i));
        }
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < chromosome.get(0); i++){
            while(part1.contains(operator2.get(index2))){
                index2++;
            }
            child2.getOperator().add(operator2.get(index2));
            index2++;
            while(part2.contains(operator1.get(index1))){
                index1++;
            }
            child1.getOperator().add(operator1.get(index1));
            index1++;
        }
        child1.getOperator().addAll(part2);
        child2.getOperator().addAll(part1);

        for(int i = chromosome.get(1) + 1; i < parent1.getOperator().size(); i++){
            while(part1.contains(operator2.get(index2))){
                index2++;
            }
            child2.getOperator().add(operator2.get(index2));
            index2++;
            while(part2.contains(operator1.get(index1))){
                index1++;
            }
            child1.getOperator().add(operator1.get(index1));
            index1++;
        }
        child1.refactorPath();
        child2.refactorPath();
        childPath.add(child1);
        childPath.add(child2);
        return childPath;
    }

    public static Path mutate(Path parent){
        Path childPath = parent.copy();
        List<Integer> chromosome = randomOperator(parent.getOperator().size());
        int value1 = childPath.getOperator().get(chromosome.get(0));
        int value2 = childPath.getOperator().get(chromosome.get(1));
        childPath.getOperator().set(chromosome.get(0), value2);
        childPath.getOperator().set(chromosome.get(1), value1);
        childPath.refactorPath();
        return childPath;
    }
    public static List<Integer> randomOperator(int lengthoperator){
        List<Integer> chromosome = new ArrayList<>();
        Random random = new Random();
        int ranInt = 0;
        while(ranInt == 0){
            ranInt = random.nextInt(lengthoperator - 1);
        }
        chromosome.add(ranInt);
        ranInt = 0;
        while(ranInt == 0){
            ranInt = random.nextInt(lengthoperator - chromosome.get(0));
        }
        chromosome.add(chromosome.get(0) + ranInt);
        return chromosome;
    };
}
