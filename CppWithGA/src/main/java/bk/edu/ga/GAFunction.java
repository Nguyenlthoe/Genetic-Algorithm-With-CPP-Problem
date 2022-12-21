package bk.edu.ga;

import bk.edu.model.Matrix;
import bk.edu.model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GAFunction {
    public static List<Path> crossOver(Path parent1, Path parent2){
        List<Path> childPath = new ArrayList<>();
        List<Integer> position = randomPosition(parent1.getChromosome().size());
//        System.out.println(position.get(0));
//        System.out.println(position.get(1));
        Path child1 = new Path();
        Path child2 = new Path();
        List<Integer> chromosome1 = parent1.getChromosome();
        List<Integer> chromosome2 = parent2.getChromosome();
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();
        for(int i = position.get(0); i <= position.get(1); i++){
            part1.add(chromosome1.get(i));
            part2.add(chromosome2.get(i));
        }
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < position.get(0); i++){
            while(part1.contains(chromosome2.get(index2))){
                index2++;
            }
            child2.getChromosome().add(chromosome2.get(index2));
            index2++;
            while(part2.contains(chromosome1.get(index1))){
                index1++;
            }
            child1.getChromosome().add(chromosome1.get(index1));
            index1++;
        }
        child1.getChromosome().addAll(part2);
        child2.getChromosome().addAll(part1);

        for(int i = position.get(1) + 1; i < parent1.getChromosome().size(); i++){
            while(part1.contains(chromosome2.get(index2))){
                index2++;
            }
            child2.getChromosome().add(chromosome2.get(index2));
            index2++;
            while(part2.contains(chromosome1.get(index1))){
                index1++;
            }
            child1.getChromosome().add(chromosome1.get(index1));
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
        List<Integer> position = randomPosition(parent.getChromosome().size());
        int value1 = childPath.getChromosome().get(position.get(0));
        int value2 = childPath.getChromosome().get(position.get(1));
        childPath.getChromosome().set(position.get(0), value2);
        childPath.getChromosome().set(position.get(1), value1);
        childPath.refactorPath();
        return childPath;
    }
    public static List<Integer> randomPosition(int lengthChromosome){
        List<Integer> position = new ArrayList<>();
        Random random = new Random();
        int ranInt = 0;
        while(ranInt == 0){
            ranInt = random.nextInt(lengthChromosome - 1);
        }
        position.add(ranInt);
        ranInt = 0;
        while(ranInt == 0){
            ranInt = random.nextInt(lengthChromosome - position.get(0));
        }
        position.add(position.get(0) + ranInt);
        return position;
    };
}
