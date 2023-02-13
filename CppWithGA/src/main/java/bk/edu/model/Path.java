package bk.edu.model;

import bk.edu.myfunction.MyFunction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Path {
    private List<Integer> operator = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();
    private List<Integer> chromosome = new ArrayList<>();
    private int cost = 0;
    public int cost(){
        if(cost == 0){
            int count = 0;
            for(int i = 0; i < characters.size(); i++){
                if(characters.get(i).charValue() == 's'){
                    count += 2;
                } else {
                    count += 3;
                }
            }
            cost = count;
        }

        return cost;
    }

    public Path copy(){
        Path cp = new Path();
        for(int i = 0; i < this.operator.size(); i++){
            cp.getOperator().add(this.operator.get(i));
        }
        for(int i = 0; i < this.characters.size(); i++){
            cp.getCharacters().add(this.characters.get(i));
        }
        for(int i = 0; i < this.chromosome.size(); i++){
            cp.getChromosome().add(this.chromosome.get(i));
        }
        return cp;
    }

    public void printPath(){
        System.out.println("\noperator:");
        for(int i = 0; i < this.getOperator().size(); i++){
            System.out.print(this.getOperator().get(i) + " ");
        }
        System.out.println("\n");
//        System.out.println("\nVectorPath:");
//        for (int i = 0; i < this.getCharacters().size(); i++){
//            System.out.print(this.getCharacters().get(i) + " ");
//        }
    }

    public void refactorPath(){
        boolean[] checkPoint = MyFunction.initCheckPoint(Matrix.x * Matrix.y);
        List<Integer> finalOperator = new ArrayList<>();
        finalOperator.add(this.getOperator().get(0));
        this.getChromosome().add(-Matrix.y);
        this.getChromosome().add(0);
        checkPoint[0] = true;
        int index = 1;
        int sizeChromosome;
        while(!MyFunction.checkPathSuccess(checkPoint) && index < checkPoint.length){
            if(checkPoint[this.getOperator().get(index)]){
                index++;
                continue;
            }

            sizeChromosome = this.getChromosome().size();
            Path path = MyFunction.findPath(this.getChromosome().get(sizeChromosome - 1), this.getOperator().get(index),
                    this.getChromosome().get(sizeChromosome - 2));
            this.getCharacters().addAll(path.getCharacters());
            this.getChromosome().addAll(path.getOperator());
            for(int i = 0; i < path.getOperator().size(); i++){
                int point = path.getOperator().get(i);
                if(!checkPoint[point]){
                    finalOperator.add(point);
                    checkPoint[point] = true;
                }
            }
            index++;
        }
        this.chromosome.remove(0);
        this.operator = finalOperator;
    }

    public void refactorPath2(int start, int a){
        boolean[] checkPoint = MyFunction.initCheckPoint(Matrix.x * Matrix.y);
        List<Integer> finalOperator = new ArrayList<>();
        finalOperator.add(this.getOperator().get(1));
//        this.getChromosome().add(this.getOperator().get(0));
//        this.getChromosome().add(0);
        checkPoint[0] = true;
        int index = 1;
        int sizeChromosome;
        while(!MyFunction.checkPathSuccess(checkPoint)){
            if(checkPoint[this.getOperator().get(index)]){
                index++;
                continue;
            }

            sizeChromosome = this.getChromosome().size();
            Path path = MyFunction.findPath(this.getChromosome().get(sizeChromosome - 1), this.getOperator().get(index),
                    this.getChromosome().get(sizeChromosome - 2));
            this.getCharacters().addAll(path.getCharacters());
            this.getChromosome().addAll(path.getOperator());
            for(int i = 0; i < path.getOperator().size(); i++){
                int point = path.getOperator().get(i);
                if(!checkPoint[point]){
                    finalOperator.add(point);
                    checkPoint[point] = true;
                }
            }
            index++;
        }
        this.chromosome.remove(0);
        this.operator = finalOperator;
    }

    public int compareTo(Object path){
        Path path2 = ((Path) path);
        int pathCost = ((Path) path).cost();
        if(pathCost != this.cost()){
            return (this.cost() - pathCost);
        } else{
            return this.chromosome.size() - path2.chromosome.size() - this.operator.size() + path2.operator.size();
        }

    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Path)) {
            return false;
        }
        List<Integer> operatorOfO = ((Path) o).getOperator();
        for(int i = 0; i < operatorOfO.size(); i++){
            if(!operatorOfO.get(i).equals(this.getOperator().get(i))){
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        return this.cost();
    }

    public static void main (String[] args){
        Path path = MyFunction.initRandomStraightPath();
        System.out.println(path.getOperator().get(0));
    }
}
