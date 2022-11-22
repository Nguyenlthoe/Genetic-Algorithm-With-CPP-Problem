package bk.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
    public int x = 8;
    public int y = 7;
    public int[][] map = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},};
    public List<Integer> initSolution(){
        Random rand = new Random();
        List<Integer> solution = new ArrayList<>();
        int size = x * y;
        boolean[] check = new boolean[size];
        for(int i = 1; i < size; i++){
            check[i] = false;
        }
        solution.add(-7);
        solution.add(0);
        check[0] = true;
        while (!checkFill(check)){
            int nextPoint = 0;
            int nextX = 0;
            int nextY = 0;
            while (check[nextPoint] || map[nextX][nextY] == 1){
                nextPoint = rand.nextInt(size);
                nextX = nextPoint / y;
                nextY = nextPoint % y;
            }
        }
        return solution;
    }

    public boolean checkFill(boolean check[]){
        for (boolean b : check) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
