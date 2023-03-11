package bk.edu;

import bk.edu.ga.GAFunction;
import bk.edu.ga.Populations;
import bk.edu.model.Matrix;
import bk.edu.model.Path;
import bk.edu.myfunction.DisplayMaze;
import bk.edu.myfunction.MyFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int minCost = 1000;
        int maxCost = 0;
        int sumCost = 0;
        int minDuplicate = 1000;
        int sumDuplicate = 0;
        long minTime = 999999999999999L;
        long maxTime = 0;
        long sumTime = 0;
        int number = 1;
        int minCost2 = 1000;
        int maxCost2 = 0;
        int sumCost2 = 0;
        int minDuplicate2 = 1000;
        int sumDuplicate2 = 0;
        long minTime2 = 999999999999999L;
        long maxTime2 = 0;
        long sumTime2 = 0;

        for(int k = 0; k < number; k++){
            Populations populations = new Populations();

            populations.initPopulations(200);

            Populations populations2 = populations.copy();
            System.out.println("----------------------------");
            DisplayMaze.showSolution(populations.getParent().get(0).getChromosome(), 50);
            long startTime = System.currentTimeMillis();
            for(int i = 0; i < 500; i++){
                populations.updatePopulations();
//                if(populations.getParent().get(0).cost() == populations.getParent().get(populations.getParent().size() - 1).cost()){
//                    break;
//                }
                System.out.println("Min: " + populations.getMinFitness() + " | Max: " + populations.getParent().get(populations.getParent().size() - 1).cost());

//                populations.getParent().get(0).printPath();
//
//                populations.getParent().get(1).printPath();
            }

            DisplayMaze.showSolution(populations.getParent().get(0).getChromosome(), 200);
//            long timeProcess = System.currentTimeMillis() - startTime;
//            sumTime += timeProcess;
//            if(minTime > timeProcess){
//                minTime = timeProcess;
//            }
//            if(maxTime < timeProcess){
//                maxTime = timeProcess;
//            }
//            Path path = populations.getParent().get(0);
//            int duplicatePath = path.getChromosome().size() - path.getOperator().size();
//            if(minDuplicate > duplicatePath){
//                minDuplicate = duplicatePath;
//            }
//            sumDuplicate += duplicatePath;
//            if(path.getCost() < minCost){
//                minCost = path.getCost();
//            }
//            if(path.getCost() > maxCost){
//                maxCost = path.getCost();
//            }
//            sumCost += path.getCost();
//
//            long startTime2 = System.currentTimeMillis();
//            for(int i = 0; i < 300; i++){
//                populations2.updatePopulations2();
////                if(populations.getParent().get(0).cost() == populations.getParent().get(populations.getParent().size() - 1).cost()){
////                    break;
////                }
////                System.out.println("Min: " + populations.getMinFitness() + " | Max: " + populations.getParent().get(99).cost());
//
////                populations.getParent().get(0).printPath();
////
////                populations.getParent().get(1).printPath();
////
//            }

//            DisplayMaze.showSolution(populations2.getParent().get(0).getChromosome());
//            long timeProcess2 = System.currentTimeMillis() - startTime2;
//            sumTime2 += timeProcess2;
//            if(minTime2 > timeProcess2){
//                minTime2 = timeProcess2;
//            }
//            if(maxTime2 < timeProcess2){
//                maxTime2 = timeProcess2;
//            }
//            Path path2 = populations.getParent().get(0);
//            int duplicatePath2 = path2.getChromosome().size() - path2.getOperator().size();
//            if(minDuplicate2 > duplicatePath2){
//                minDuplicate2 = duplicatePath2;
//            }
//            sumDuplicate2 += duplicatePath;
//            if(path2.getCost() < minCost2){
//                minCost2 = path2.getCost();
//            }
//            if(path2.getCost() > maxCost2){
//                maxCost2 = path2.getCost();
//            }
//            sumCost2 += path2.getCost();
        }
//        System.out.println("Min cost: "  + minCost2);
//        System.out.println("Max cost: " + maxCost2);
//        System.out.println("Average cost: " + sumCost2 * 1.0 / number);
//        System.out.println("Min duplicate: " + minDuplicate2);
//        System.out.println("Average duplicate: " + sumDuplicate2 * 1.0 / number);
//        System.out.println("Min time: " + minTime2);
//        System.out.println("Max time: " + maxTime2);
//        System.out.println("Average time " + sumTime2 * 1.0/ number);
//
//        System.out.println("Min cost: "  + minCost);
//        System.out.println("Max cost: " + maxCost);
//        System.out.println("Average cost: " + sumCost * 1.0 / number);
//        System.out.println("Min duplicate: " + minDuplicate);
//        System.out.println("Average duplicate: " + sumDuplicate * 1.0 / number);
//        System.out.println("Min time: " + minTime);
//        System.out.println("Max time: " + maxTime);
//        System.out.println("Average time " + sumTime * 1.0/ number);



//        System.out.println(populations.getParent().get(99).cost());
//        Path authorPath = new Path();
//        Integer[] operator = {0, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 32, 15, 16, 17, 35, 34, 33, 29, 31, 30, 14, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 1, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119,137, 136, 135, 134, 133, 132, 150, 151, 152, 153, 154, 155, 173, 172, 171, 170, 169, 168, 186, 187, 188, 189, 190, 191, 192, 174, 175, 176, 177, 195, 196, 178, 179, 197, 215, 214,213, 212, 194, 193, 211, 210, 209, 208, 207, 206, 205, 204, 203, 202, 201, 200, 199, 198, 216, 217, 218, 219, 220, 221, 222, 223, 224, 242, 241, 240, 239, 238, 237, 236, 235, 234, 252, 253, 254, 255, 256, 257, 258,259, 260, 278, 277, 276, 275, 274, 273, 272, 271, 289, 290, 291, 292, 293, 294,295,296,297, 314, 313, 312, 311, 310, 309, 308, 307, 306, 163, 270, 180, 162, 144, 126, 288, 145, 127, 181};
//        authorPath.getOperator().addAll(Arrays.asList(operator));
//        authorPath.refactorPath();
//        System.out.println(authorPath.cost());
//        DisplayMaze.showSolution(authorPath.getChromosome());
    }
}