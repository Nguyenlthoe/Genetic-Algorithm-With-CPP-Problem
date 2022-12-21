package bk.edu.model;

import bk.edu.myfunction.MyFunction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Path {
    private List<Integer> chromosome = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private List<Integer> position = new ArrayList<>();
    private int fitness = 0;
    public int cost(){
        if(fitness == 0){
            int cost = 0;
            for(int i = 0; i < characters.size(); i++){
                if(characters.get(i).charValue() == 's'){
                    cost += 6;
                } else {
                    cost += 7;
                }
            }
            fitness = cost;
        }

        return fitness;
    }

    public Path copy(){
        Path cp = new Path();
        for(int i = 0; i < this.chromosome.size(); i++){
            cp.getChromosome().add(this.chromosome.get(i));
        }
        for(int i = 0; i < this.characters.size(); i++){
            cp.getCharacters().add(this.characters.get(i));
        }
        for(int i = 0; i < this.position.size(); i++){
            cp.getPosition().add(this.position.get(i));
        }
        return cp;
    }

    public void printPath(){
        System.out.println("\nChromosome:");
        for(int i = 0; i < this.getChromosome().size(); i++){
            System.out.print(this.getChromosome().get(i) + " ");
        }
        System.out.println("\n");
//        System.out.println("\nVectorPath:");
//        for (int i = 0; i < this.getCharacters().size(); i++){
//            System.out.print(this.getCharacters().get(i) + " ");
//        }
    }

    public void refactorPath(){
        boolean[] checkPoint = MyFunction.initCheckPoint(Matrix.x * Matrix.y);
        List<Integer> finalChromosome = new ArrayList<>();
        finalChromosome.add(this.getChromosome().get(0));
        this.getPosition().add(-7);
        this.getPosition().add(0);
        checkPoint[0] = true;
        int index = 1;
        int sizePosition;
        while(!MyFunction.checkPathSuccess(checkPoint)){
            if(checkPoint[this.getChromosome().get(index)]){
                index++;
                continue;
            }

            sizePosition = this.getPosition().size();
            Path path = MyFunction.findPath(this.getPosition().get(sizePosition - 1), this.getChromosome().get(index),
                    this.getPosition().get(sizePosition - 2));
            this.getCharacters().addAll(path.getCharacters());
            this.getPosition().addAll(path.getChromosome());
            for(int i = 0; i < path.getChromosome().size(); i++){
                int point = path.getChromosome().get(i);
                if(!checkPoint[point]){
                    finalChromosome.add(point);
                    checkPoint[point] = true;
                }
            }
            index++;
        }
        this.chromosome = finalChromosome;
    }

    public int compareTo(Object path){
        return (this.cost() - ((Path) path).cost());
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Path)) {
            return false;
        }
        List<Integer> chromosomeOfO = ((Path) o).getChromosome();
        for(int i = 0; i < chromosomeOfO.size(); i++){
            if(!chromosomeOfO.get(i).equals(this.getChromosome().get(i))){
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        return this.cost();
    }
}
