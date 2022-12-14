package bk.edu.myfunction;

import bk.edu.model.Matrix;
import bk.edu.model.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyFunction {
    private static List<Integer> findPath(int start, int end, int direct) {
        int x = Matrix.x;
        int y = Matrix.y;
        List<Integer> points = new ArrayList<>();
        // todo: tim duong
        List<Path> pathList = new ArrayList<>();
        Path newPath= new Path();
        newPath.getChromosome().add(direct);
        newPath.getChromosome().add(start);
        pathList.add(newPath);
        boolean[] checkAppear = new boolean[x * y];
        for(int i = 0; i < checkAppear.length; i++){
            checkAppear[i] = false;
        }
        while (true) {
            List<Integer> lastPoint = pathList.get(0).getChromosome();
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
                        straightPath.getCharacters().add('s');
                        straightPath.getChromosome().add(straight);
                        pathList.add(straightPath);
                        checkAppear[straight] = true;
                    }
                }
                if(left >= 0 && left < x * y){
                    if(!checkAppear[left]){
                        Path leftPath = path.copy();
                        leftPath.getCharacters().add('l');
                        leftPath.getChromosome().add(left);
                        pathList.add(leftPath);
                        checkAppear[left] = true;
                    }
                }
                if(right >= 0 && right < x * y){
                    if(!checkAppear[right]){
                        Path rightPath = path.copy();
                        rightPath.getCharacters().add('r');
                        rightPath.getChromosome().add(right);
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

    public static Path initRandomPath(){
        Path path = new Path();
        Random rand = new Random();
        path.getChromosome().add(-7);
        path.getChromosome().add(0);
        boolean[] checkPoint = initCheckPoint(Matrix.x * Matrix.y);
        checkPoint[0] = true;
        while (!checkPathSuccess(checkPoint)){
            int direct = path.getChromosome().get(path.getChromosome().size() -2);
            int start = path.getChromosome().get(path.getChromosome().size() - 1);
            List<Integer> pointList = new ArrayList<>();

            int left = Matrix.left(direct,start);
            if(left != -1 && !checkPoint[left]){
                pointList.add(left);
            }

            int right = Matrix.right(direct, start);
            if(right != -1 && !checkPoint[right]){
                pointList.add(right);
            }

            int straight = Matrix.straight(direct,start);
            if(straight != -1 && !checkPoint[straight]){
                pointList.add(straight);
            }

            if(pointList.size() == 0){
//                path.printPath();
//                System.out.println("\nList::");
//                for(int i = 0; i < checkPoint.length; i++){
//                    if(checkPoint[i]){
//                        System.out.print(i + " ");
//                    }
//                }
                Path nextPoint = findNextPoint(direct, start, checkPoint);
                path.getChromosome().add(nextPoint.getChromosome().get(0));
                path.getCharacters().addAll(nextPoint.getCharacters());
                checkPoint[nextPoint.getChromosome().get(0)] = true;
            } else {
                int randomInt = rand.nextInt(pointList.size());
                if(pointList.get(randomInt) == left){
                    path.getChromosome().add(left);
                    path.getCharacters().add('l');
                }

                if(pointList.get(randomInt) == right){
                    path.getChromosome().add(right);
                    path.getCharacters().add('r');
                }

                if(pointList.get(randomInt) == straight){
                    path.getChromosome().add(straight);
                    path.getCharacters().add('s');
                }

                checkPoint[pointList.get(randomInt)] = true;
            }
        }
        path.getCharacters().remove(0);
        return path;
    };

    public static boolean[] initCheckPoint(int size){
        boolean[] checkPoint = new boolean[size];
        for (int i = 0; i < checkPoint.length; i++){
            checkPoint[i] = false;
        }
        for(int i = 0; i < Matrix.x; i++){
            for(int j = 0; j < Matrix.y; j++){
                if(Matrix.map[i][j] == 1){
                    checkPoint[i * Matrix.y + j] = true;
                }
            }
        }
        return checkPoint;
    }

    public static boolean checkPathSuccess(boolean[] checkPoint){
        for (boolean b : checkPoint) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static Path findNextPoint(int direct, int start, boolean[] checkPoint){
          List<Path> paths = new ArrayList<>();
          paths.add(new Path());
          paths.get(0).getChromosome().add(direct);
          paths.get(0).getChromosome().add(start);
          boolean[] checkExistPoint = initCheckPoint(Matrix.x * Matrix.y);
          checkExistPoint[start] = true;
          while (true){
              Path path;
              try {
                  path = paths.get(0);
              } catch (Exception e){
                  System.out.println("\nNo Path Found");
                  return null;
              }
              int directPath = path.getChromosome().get(path.getChromosome().size() -2);
              int startPath = path.getChromosome().get(path.getChromosome().size() - 1);

              if(!checkPoint[startPath]){
                  path.getChromosome().clear();
                  path.getChromosome().add(startPath);
                  break;
              }

              paths.remove(0);
              int left = Matrix.left(directPath,startPath);
              if(left != -1 && !checkExistPoint[left]){
                  Path leftPath = path.copy();
                  leftPath.getChromosome().add(left);
                  leftPath.getCharacters().add('l');
                  checkExistPoint[left] = true;

                  if(!checkPoint[left]){
                      paths.clear();
                  }
                  paths.add(leftPath);
              }

              int right = Matrix.right(directPath, startPath);
              if(right != -1 && !checkExistPoint[right]){
                  Path rightPath = path.copy();
                  rightPath.getChromosome().add(right);
                  rightPath.getCharacters().add('r');
                  checkExistPoint[right] = true;

                  if(!checkPoint[right]){
                      paths.clear();
                  }
                  paths.add(rightPath);
              }

              int straight = Matrix.straight(directPath,startPath);
              if(straight != -1 && !checkExistPoint[straight]){
                  Path straightPath = path.copy();
                  straightPath.getChromosome().add(straight);
                  straightPath.getCharacters().add('s');
                  checkExistPoint[straight] = true;

                  if(!checkPoint[straight]){
                      paths.clear();
                  }
                  paths.add(straightPath);
              }
          }
          return paths.get(0);
    };
}
