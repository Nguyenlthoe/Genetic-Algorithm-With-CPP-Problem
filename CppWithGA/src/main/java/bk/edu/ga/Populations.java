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
        for(int i = 0; i < size / 2; i++){
            parent.add(MyFunction.initRandomPath());
            parent.add(MyFunction.initRandomStraightPath());
        }
        this.parent.sort(Path::compareTo);
    }

    public void updatePopulations(){
        Random random = new Random();
        for(int i = 0; i < size; i++){
            int index = 3;
            Path parent1 = this.parent.get(i);
            while (index > 0){
                index --;
                int ranInt = random.nextInt(size);
                List<Path> children = GAFunction.crossOver(parent1, this.parent.get(ranInt));
                this.parent.addAll(children);
            }
            this.parent.add(GAFunction.mutate(parent1));
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
}
