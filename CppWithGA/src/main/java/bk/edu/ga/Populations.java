package bk.edu.ga;

import bk.edu.model.Matrix;
import bk.edu.model.Path;
import bk.edu.myfunction.MyFunction;
import lombok.Getter;

import java.util.*;
@Getter
public class Populations {
    List<Path> parent = new ArrayList<>();
    private int size;
    public void initPopulations(int size){
        this.size = size;
        for(int i = 0; i < 30; i++){
            Path path = MyFunction.initZicZacPath();
            if(!parent.contains(path)){
                parent.add(path);
            }
        }
        for(int i = 0; i < 50; i++){
            Path path = MyFunction.initRandomStraightPath();
            if(!parent.contains(path)){
                parent.add(path);
            }
        }
        while (parent.size() < 100){
            Path path = MyFunction.initRandomPath();
            if(!parent.contains(path)){
                parent.add(path);
            }
        }
        this.parent.sort(Path::compareTo);
    }

    public void updatePopulations(){
        Random random = new Random();
        for(int i = 0; i < size; i++){
            Path parent1 = this.parent.get(i);
            int ranInt = random.nextInt(size);
            if(statistic(80)){
                List<Path> children = GAFunction.crossOver(parent1, this.parent.get(ranInt));
                this.parent.addAll(children);
            }
        }
        for(int i = 0; i < size; i++){
            Path parent1 = this.parent.get(i);
            int ranInt = random.nextInt(size);
            if(statistic(80)){
                List<Path> children = GAFunction.crossOver(parent1, this.parent.get(ranInt));
                this.parent.addAll(children);
            }
        }
        int sizeNow = this.parent.size();
        for(int i = 0; i < sizeNow; i++){
            if(statistic(90)){
                Path mutatePath = GAFunction.mutate(this.parent.get(i));
                this.parent.add(mutatePath);
            }
        }
        this.parent.sort(Path::compareTo);
        List<Path> finalParent = new ArrayList<>();
        int index = 0;
        while (finalParent.size() < size){
            if(!finalParent.contains(this.parent.get(index))){
                finalParent.add(this.parent.get(index));
            }
            index++;
        }
        this.parent.clear();
        this.parent = finalParent;
    }
    public void displayPopulations(){
        for(int i = 0; i < size; i++){
            parent.get(i).printPath();
            System.out.println(parent.get(i).cost() + "\n");
        }
    }
    public double getMinFitness(){
        return this.parent.get(0).cost();
    }
    public boolean statistic(int percent){
        Random random = new Random();
        int ranInt = random.nextInt(100);
        if(ranInt < percent){
            return true;
        }
        return false;
    }
}
