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
        Populations populations = new Populations();
        populations.initPopulations(100);
        populations.getParent().get(0).printPath();
//        populations.displayPopulations();
//        System.out.println("----------------------------");
        System.out.println(populations.getMinFitness());
//        System.out.println("----------------------------");
        for(int i = 0; i < 300; i++){
            populations.updatePopulations();
//            populations.displayPopulations();
//            System.out.println("----------------------------");
            System.out.println(populations.getMinFitness());
//            System.out.println("----------------------------");
        }
        populations.getParent().get(0).printPath();
        DisplayMaze.showSolution(populations.getParent().get(0).getChromosome());

        DisplayMaze.showSolution(populations.getParent().get(1).getChromosome());

        DisplayMaze.showSolution(populations.getParent().get(2).getChromosome());

        Path authorPath = new Path();
        Integer[] operator = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119,137, 136, 135, 134, 133, 132, 150, 151, 152, 153, 154, 155, 173, 172, 171, 170, 169, 168, 186, 187, 188, 189, 190, 191, 192, 174, 175, 176, 177, 195, 196, 178, 179, 197, 215, 214,213, 212, 194, 193, 211, 210, 209, 208, 207, 206, 205, 204, 203, 202, 201, 200, 199, 198, 216, 217, 218, 219, 220, 221, 222, 223, 224, 242, 241, 240, 239, 238, 237, 236, 235, 234, 252, 253, 254, 255, 256, 257, 258,259, 260, 278, 277, 276, 275, 274, 273, 272, 271, 289, 290, 291, 292, 293, 294,295,296,297, 314, 313, 312, 311, 310, 309, 308, 307, 306, 288, 270, 180, 162, 144, 126, 127, 145, 163, 181};
        authorPath.getOperator().addAll(Arrays.asList(operator));
        authorPath.refactorPath();
        System.out.println(authorPath.cost());
        DisplayMaze.showSolution(authorPath.getChromosome());
    }
}