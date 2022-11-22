package bk.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
    public int x = 8;
    public static int y = 7;
    public int[][] map = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},};

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

    private List<Integer> findPath(int start, int end, int direct) {
        List<Integer> points = new ArrayList<>();
        int startX = start / y;
        int startY = start % y;
        int endX = end / y;
        int endY = end % y;
        return points;
    }

    private static int left(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX - startX == 1){
            return (startY - 1) + startX * y;
        } else if(directX - startX == -1){
            return (directY + 1) + startX * y;
        } else if(startY - directY == 1){
            return startY + (startX - 1) * y;
        } else {
            return startY + (startX + 1) * y;
        }
    }

    private static int right(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX - startX == 1){
            return (startY + 1) + startX * y;
        } else if(directX - startX == -1){
            return (directY - 1) + startX * y;
        } else if(startY - directY == 1){
            return startY + (startX + 1) * y;
        } else {
            return startY + (startX - 1) * y;
        }
    }
    private static int straight(int direct, int start){
        int startX = start / y;
        int startY = start % y;
        int directX = direct / y;
        int directY = direct % y;
        if(directX == startX){
            return (startY - directY) * 2 + directY + startX * y;
        } else {
            return ((startX - directX) * 2 + directX) * y + startY;
        }
    }
}
