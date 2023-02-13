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
//        Path authorPath = new Path();
//        Integer[] operator = {0, 18, 2, 3, 4, 5, 6, 7, 8, 9, 10, 32, 12, 13, 11, 15, 16, 17, 35, 34, 33, 29, 31, 30, 14, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 1, 36, 41, 42, 38, 39, 40, 37, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119,137, 136, 135, 134, 133, 132, 150, 151, 152, 153, 154, 155, 173, 172, 171, 170, 169, 168, 186, 187, 188, 189, 190, 191, 192, 174, 175, 176, 177, 195, 196, 178, 179, 197, 215, 214,213, 212, 194, 193, 211, 210, 209, 208, 207, 206, 205, 204, 203, 202, 201, 200, 199, 198, 216, 217, 218, 219, 220, 221, 222, 223, 224, 242, 241, 240, 239, 238, 237, 236, 235, 234, 252, 253, 254, 255, 256, 257, 258,259, 260, 278, 277, 276, 275, 274, 273, 272, 271, 289, 290, 291, 292, 293, 294,295,296,297, 314, 313, 312, 311, 310, 309, 308, 307, 306, 163, 270, 180, 162, 144, 126, 288, 145, 127, 181};
//        authorPath.getOperator().addAll(Arrays.asList(operator));
//        authorPath.refactorPath();
//        System.out.println(authorPath.cost());
      //parent.add(authorPath);
        for(int i = 0; i < 20; i++){
            Path path = MyFunction.initZicZacPath();
            if(!parent.contains(path)){
                parent.add(path);
            }
        }
        for(int i = 0; i < 10; i++){
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
//        for(int i = 0; i < size; i++){
//            Path parent1 = this.parent.get(i);
//            int ranInt = random.nextInt(size);
//            if(statistic(80)){
//                List<Path> children = GAFunction.crossOver(parent1, this.parent.get(ranInt));
//                this.parent.addAll(children);
//            }
//        }
//        for(int i = 0; i < size; i++){
//            Path parent1 = this.parent.get(i);
//            int ranInt = random.nextInt(size);
//            if(statistic(80)){
//                List<Path> children = GAFunction.crossOver(parent1, this.parent.get(ranInt));
//                this.parent.addAll(children);
//            }
//        }
        for(int i = 0; i < size; i++){
            Path parent1 = this.parent.get(i);
            int ranInt = random.nextInt(size);
            if(statistic(80)){
                List<Path> children = GAFunction.crossOver2(parent1, this.parent.get(ranInt));
                this.parent.addAll(children);
            }
        }
        for(int i = 0; i < parent.size(); i++){
            if(statistic(90)){
                Path mutatePath = GAFunction.mutate(parent.get(i));
                parent.add(mutatePath);
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
