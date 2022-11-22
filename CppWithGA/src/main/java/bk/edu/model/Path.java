package bk.edu.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Path {
    List<Integer> points = new ArrayList<>();
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
}
