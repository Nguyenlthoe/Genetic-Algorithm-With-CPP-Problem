package bk.edu.model;

import bk.edu.myfunction.MyFunction;

import java.util.*;

public class Matrix {
    public static int x = 14;
    public static int y = 13;
    public static final int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};

    public static void main(String[] args){
        for(int i = 0; i < 1000; i++){
            Path path = MyFunction.initRandomPath();
            path.printPath();
        }
    }
    public List<Integer> initSolution() {
        Random rand = new Random();
        List<Integer> solution = new ArrayList<>();
        int size = x * y;
        boolean[] check = new boolean[size];
        for (int i = 1; i < size; i++) {
            check[i] = false;
        }
        solution.add(-7);
        solution.add(0);
        check[0] = true;
        while (!checkFill(check)) {
            int nextPoint = 0;
            int nextX = 0;
            int nextY = 0;
            while (check[nextPoint] || map[nextX][nextY] == 1) {
                nextPoint = rand.nextInt(size);
                nextX = nextPoint / y;
                nextY = nextPoint % y;
            }
        }
        return solution;
    }

    public boolean checkFill(boolean[] check) {
        for (boolean b : check) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    public static int left(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX - startX == 1){
            if(directY - 1 < 0){
                return -1;
            }
            if(map[startX][startY-1] == 1){
                return -1;
            }
            return (startY - 1) + startX * y;
        } else if(directX - startX == -1){
            if (directY + 1 >= y){
                return -1;
            }
            if(map[startX][directY + 1] == 1){
                return -1;
            }
            return (directY + 1) + startX * y;
        } else if(startY - directY == 1){
            if(startX - 1 < 0){
                return -1;
            }
            if(map[startX - 1][startY] == 1){
                return -1;
            }
            return startY + (startX - 1) * y;
        } else {
            if(startX + 1 >= x){
                return -1;
            }
            if(map[startX + 1][startY] == 1){
                return -1;
            }
            return startY + (startX + 1) * y;
        }
    }

    public static int right(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX - startX == 1){
            if (startY + 1 >= y){
                return -1;
            }
            if(map[startX][startY + 1] == 1){
                return -1;
            }
            return (startY + 1) + startX * y;
        } else if(directX - startX == -1){
            if(directY - 1 < 0){
                return -1;
            }
            if(map[startX][directY -1] == 1){
                return -1;
            }
            return (directY - 1) + startX * y;
        } else if(startY - directY == 1){
            if(startX + 1 >= x){
                return -1;
            }
            if(map[startX+1][startY] == 1){
                return -1;
            }
            return startY + (startX + 1) * y;
        } else {
            if(startX - 1 < 0){
                return -1;
            }
            if(map[startX - 1][startY] == 1){
                return -1;
            }
            return startY + (startX - 1) * y;
        }
    }
    public static int straight(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX == startX){
            int next = (startY - directY)*2 + directY;
            if(next >= y || next < 0){
                return -1;
            }
            if(map[startX][next] == 1){
                return -1;
            }
            return next + startX * y;
        } else {
            int next = (startX - directX) * 2 + directX;
            if(next >= x || next < 0){
                return -1;
            }
            if(map[next][startY] == 1){
                return -1;
            }
            return next * y + startY;
        }
    }
}
