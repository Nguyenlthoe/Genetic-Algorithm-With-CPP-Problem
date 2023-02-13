package bk.edu.ga;

import bk.edu.model.Matrix;
import bk.edu.model.Path;
import bk.edu.myfunction.MyFunction;

import java.util.ArrayList;
import java.util.Collections;
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

    public static List<Path> crossOver2(Path parent1, Path parent2){
        List<Path> childPath = new ArrayList<>();
        List<Integer> chromosome = randomOperator(parent1.getOperator().size());
        Path child1 = new Path();
        Path child2 = new Path();
        List<Integer> chromosome1 = parent1.getChromosome();
        List<Integer> chromosome2 = parent2.getChromosome();
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        int start1 = parent1.getOperator().get(chromosome.get(0));
        System.out.println(chromosome1.size());
        System.out.println(chromosome1.indexOf(start1) + " start1 " + chromosome1.indexOf(parent1.getOperator().get(chromosome.get(1))));
        for(int i = chromosome1.indexOf(start1);
            i <=  chromosome1.indexOf(parent1.getOperator().get(chromosome.get(1))); i++){
            part1.add(chromosome1.get(i));
        }

        int start2 = parent2.getOperator().get(chromosome.get(0));
        System.out.println(chromosome2.size());
        System.out.println(chromosome2.indexOf(start2) + " start2 " + chromosome2.indexOf(parent2.getOperator().get(chromosome.get(1))));
        for(int i = chromosome2.indexOf(start2);
            i <=  chromosome2.indexOf(parent2.getOperator().get(chromosome.get(1))); i++){
            part2.add(chromosome2.get(i));
        }

        boolean[] check1 = MyFunction.initCheckPoint(Matrix.x * Matrix.y);
        boolean[] check2 = MyFunction.initCheckPoint(Matrix.x * Matrix.y);

        child1.getChromosome().add(0, -Matrix.y);
        for(int i = 0; i  <= chromosome1.indexOf(parent1.getOperator().get(chromosome.get(0) - 1)); i++){
            child1.getChromosome().add(parent1.getChromosome().get(i));
        }
        Path semiPath1 = MyFunction.findPath(child1.getChromosome().get(child1.getChromosome().size() - 1),
                part2.get(0), child1.getChromosome().get(child1.getChromosome().size() - 2));
        child1.getChromosome().addAll(semiPath1.getOperator());

        if(!child1.getChromosome().get(child1.getChromosome().size() - 2).equals(part2.get(1))){
            for(int i = 1; i < part2.size(); i++){
                child1.getChromosome().add(part2.get(i));
            }
            child1.getOperator().add(0);
            check1[0] = true;
            for(int i = 1; i < child1.getChromosome().size() - 1; i++){
                if(Matrix.left(child1.getChromosome().get(i - 1), child1.getChromosome().get(i)) == child1.getChromosome().get(i+1)){
                    child1.getCharacters().add('l');
                } else  if (Matrix.right(child1.getChromosome().get(i - 1), child1.getChromosome().get(i)) == child1.getChromosome().get(i+1)){
                    child1.getCharacters().add('r');
                } else {
                    child1.getCharacters().add('s');
                };

                if(!check1[child1.getChromosome().get(i+1)]){
                    child1.getOperator().add(child1.getChromosome().get(i+1));
                    check1[child1.getChromosome().get(i + 1)] = true;
                }
            }

            int index = child1.getOperator().size() - 1;
            for(int i = 0; i < parent1.getOperator().size(); i++){
                if(!child1.getOperator().contains(parent1.getOperator().get(i))){
                    child1.getOperator().add(parent1.getOperator().get(i));
                }
            }
            while(!MyFunction.checkPathSuccess(check1)){
                if(check1[child1.getOperator().get(index)]){
                    index++;
                    continue;
                }

                int sizeChromosome = child1.getChromosome().size();
                Path path = MyFunction.findPath(child1.getChromosome().get(sizeChromosome - 1), child1.getOperator().get(index),
                        child1.getChromosome().get(sizeChromosome - 2));
                child1.getCharacters().addAll(path.getCharacters());
                child1.getChromosome().addAll(path.getOperator());
                for(int i = 0; i < path.getOperator().size(); i++){
                    int point = path.getOperator().get(i);
                    if(!check1[point]){
                        check1[point] = true;
                    }
                }
                index++;
            }
            child1.getChromosome().remove(0);
            boolean[] checkOp = MyFunction.initCheckPoint(Matrix.x * Matrix.y);
            int indexx = 0;
            for(int i = 0; i < child1.getChromosome().size(); i++){
                if(!checkOp[child1.getChromosome().get(i)]){
                    checkOp[child1.getChromosome().get(i)] = true;
                    indexx++;
                    if(child1.getChromosome().get(i) != child1.getOperator().get(indexx)){
                        System.out.println("hello");
                    }
                }
            }
            childPath.add(child1);
        }
        ////

        child2.getChromosome().add(0, -Matrix.y);
        for(int i = 0; i  <= chromosome2.indexOf(parent2.getOperator().get(chromosome.get(0) - 1)); i++){
            child2.getChromosome().add(parent2.getChromosome().get(i));
        }
        Path semiPath2 = MyFunction.findPath(child2.getChromosome().get(child2.getChromosome().size() - 1),
                part1.get(0), child2.getChromosome().get(child2.getChromosome().size() - 2));
        child2.getChromosome().addAll(semiPath2.getOperator());

        if(!child2.getChromosome().get(child2.getChromosome().size() - 2).equals(part1.get(1))){
            for(int i = 1; i < part1.size(); i++){
                child2.getChromosome().add(part1.get(i));
            }
            child2.getOperator().add(0);
            check2[0] = true;
            for(int i = 1; i < child2.getChromosome().size() - 1; i++){
                if(Matrix.left(child2.getChromosome().get(i - 1), child2.getChromosome().get(i)) == child2.getChromosome().get(i+1)){
                    child2.getCharacters().add('l');
                } else  if (Matrix.right(child2.getChromosome().get(i - 1), child2.getChromosome().get(i)) == child2.getChromosome().get(i+1)){
                    child2.getCharacters().add('r');
                } else {
                    child2.getCharacters().add('s');
                };
                if(!check2[child2.getChromosome().get(i+1)]){
                    child2.getOperator().add(child2.getChromosome().get(i+1));
                    check2[child2.getChromosome().get(i + 1)] = true;
                }
            }
            int index = child2.getOperator().size() - 1;
            for(int i = 0; i < parent2.getOperator().size(); i++){
                if(!child2.getOperator().contains(parent2.getOperator().get(i))){
                    child2.getOperator().add(parent2.getOperator().get(i));
                }
            }
            while(!MyFunction.checkPathSuccess(check2)){
                if(check2[child2.getOperator().get(index)]){
                    index++;
                    continue;
                }

                int sizeChromosome = child2.getChromosome().size();
                Path path = MyFunction.findPath(child2.getChromosome().get(sizeChromosome - 1), child2.getOperator().get(index),
                        child2.getChromosome().get(sizeChromosome - 2));
                child2.getCharacters().addAll(path.getCharacters());
                child2.getChromosome().addAll(path.getOperator());
                for(int i = 0; i < path.getOperator().size(); i++){
                    int point = path.getOperator().get(i);
                    if(!check2[point]){
                        check2[point] = true;
                    }
                }
                index++;
            }
            child2.getChromosome().remove(0);
            childPath.add(child2);
        }
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
    public static List<Integer> randomOperator(int lengthOperator){
        List<Integer> chromosome = new ArrayList<>();
        Random random = new Random();
        int ranInt = 0;
        while(ranInt == 0){
            ranInt = random.nextInt(lengthOperator - 1);
        }
        chromosome.add(ranInt);
        ranInt = 0;
        while(ranInt == 0 || ranInt == chromosome.get(0)){
            ranInt = random.nextInt(lengthOperator - 1);
        }
        chromosome.add(ranInt);

        Collections.sort(chromosome);
        return chromosome;
    };

    public static void main (String args[]){
        Path path1 = MyFunction.initRandomStraightPath();
        Path path2 = MyFunction.initRandomStraightPath();
        crossOver2(path2, path1);
    }

}
