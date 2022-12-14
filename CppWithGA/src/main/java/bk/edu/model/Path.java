package bk.edu.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Path {
    List<Integer> chromosome = new ArrayList<>();
    List<Character> characters = new ArrayList<>();

    public int cost(){
        int cost = 0;
        for(int i = 0; i < characters.size(); i++){
            if(characters.get(i).charValue() == 's'){
                cost += 1;
            } else {
                cost += 2;
            }
        }
        return cost;
    }

    public Path copy(){
        Path cp = new Path();
        for(int i = 0; i < this.chromosome.size(); i++){
            cp.chromosome.add(this.chromosome.get(i));
        }
        for(int i = 0; i < this.characters.size(); i++){
            cp.characters.add(this.characters.get(i));
        }
        return cp;
    }

    public void printPath(){
        System.out.println("\nOperator:");
        for(int i = 0; i < this.getChromosome().size(); i++){
            System.out.print(this.getChromosome().get(i) + " ");
        }
        System.out.println("\nVectorPath:");
        for (int i = 0; i < this.getCharacters().size(); i++){
            System.out.print(this.getCharacters().get(i) + " ");
        }
    }
}
