package bk.edu.model;

import javax.print.DocFlavor;
import java.util.*;

public class Matrix {
    public static int x = 8;
    public static int y = 7;
    public static int[][] map = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},};

    public static void main(String[] args){
        findPath(8, 45, 7).forEach(integer -> {
            System.out.println(integer);
        });
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

    private static List<Integer> findPath(int start, int end, int direct) {
        List<Integer> points = new ArrayList<>();
        // todo: tim duong
        List<Path> pathList = new ArrayList<>();
        Path newPath= new Path();
        newPath.points.add(direct);
        newPath.points.add(start);
        pathList.add(newPath);
        boolean[] checkAppear = new boolean[x * y];
        for(int i = 0; i < checkAppear.length; i++){
            checkAppear[i] = false;
        }
        while (true) {
            List<Integer> lastPoint = pathList.get(0).points;
            if(lastPoint.get(lastPoint.size() - 1) == end){
                for(int i = 2; i < lastPoint.size(); i++){
                    points.add(lastPoint.get(i));
                }
                break;
            } else {
                Path path = pathList.get(0);
                int directA = lastPoint.get(lastPoint.size() - 2);
                int startA = lastPoint.get(lastPoint.size() - 1);
                checkAppear[startA] = true;
                pathList.remove(0);
                int straight = Matrix.straight(directA, startA);
                int left = Matrix.left(directA, startA);
                int right = Matrix.right(directA, startA);
                if(straight >= 0 && straight < x * y){
                    if(!checkAppear[straight]){
                        Path straightPath = path.copy();
                        straightPath.characters.add('s');
                        straightPath.points.add(straight);
                        pathList.add(straightPath);
                        checkAppear[straight] = true;
                    }
                }
                if(left >= 0 && left < x * y){
                    if(!checkAppear[left]){
                        Path leftPath = path.copy();
                        leftPath.characters.add('l');
                        leftPath.points.add(left);
                        pathList.add(leftPath);
                        checkAppear[left] = true;
                    }
                }
                if(right >= 0 && right < x * y){
                    if(!checkAppear[right]){
                        Path rightPath = path.copy();
                        rightPath.characters.add('r');
                        rightPath.points.add(right);
                        pathList.add(rightPath);
                        checkAppear[right] = true;
                    }

                }
                Collections.sort(pathList, (path1, t1) -> {
                    return path1.cost() - t1.cost();
                });
            }
        }
        return points;
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
            if (startY + 1 >= y){
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
            if(startX + 1 >= y){
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
            if(startX + 1 >= y){
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
            if(next >= y || next < 0){
                return -1;
            }
            if(map[next][startY] == 1){
                return -1;
            }
            return next * y + startY;
        }
    }
}
