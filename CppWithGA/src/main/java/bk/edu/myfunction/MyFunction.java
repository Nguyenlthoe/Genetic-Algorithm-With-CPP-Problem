package bk.edu.myfunction;

import bk.edu.model.Matrix;
import bk.edu.model.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyFunction {
    public static Path initRandomPath(){
        Path path = new Path();
        Random rand = new Random();
        path.getOperator().add(-Matrix.y);
        path.getOperator().add(0);
        path.getChromosome().add(-Matrix.y);
        path.getChromosome().add(0);
        boolean[] checkPoint = initCheckPoint(Matrix.x * Matrix.y);
        checkPoint[0] = true;
        while (!checkPathSuccess(checkPoint)){
            int direct = path.getChromosome().get(path.getChromosome().size() -2);
            int start = path.getChromosome().get(path.getChromosome().size() - 1);
            List<Integer> pointList = new ArrayList<>();

            int straight = Matrix.straight(direct,start);
            if(straight != -1 && !checkPoint[straight]){
                pointList.add(straight);
            }

            int left = Matrix.left(direct,start);
            if(left != -1 && !checkPoint[left]){
                pointList.add(left);
            }

            int right = Matrix.right(direct, start);
            if(right != -1 && !checkPoint[right]){
                pointList.add(right);
            }

            if(pointList.size() == 0){
                Path nextPoint = findNextPoint(direct, start, checkPoint);
                int nextChromosome = nextPoint.getChromosome().get(nextPoint.getChromosome().size() - 1);
                path.getOperator().add(nextChromosome);
                path.getCharacters().addAll(nextPoint.getCharacters());
                path.getChromosome().addAll(nextPoint.getChromosome());
                checkPoint[nextChromosome] = true;
            } else {
                int randomInt = rand.nextInt(pointList.size());
                if(pointList.get(randomInt) == left){
                    path.getOperator().add(left);
                    path.getChromosome().add(left);
                    path.getCharacters().add('l');
                }

                if(pointList.get(randomInt) == right){
                    path.getOperator().add(right);
                    path.getCharacters().add('r');
                    path.getChromosome().add(right);
                }
                if(pointList.get(randomInt) == straight){
                    path.getOperator().add(straight);
                    path.getCharacters().add('s');
                    path.getChromosome().add(straight);
                }
                checkPoint[pointList.get(randomInt)] = true;
            }
        }
        path.getOperator().remove(0);
        path.getChromosome().remove(0);
        return path;
    };

    public static Path initZicZacPath(){
        Path path = new Path();
        Random rand = new Random();
        path.getOperator().add(-Matrix.y);
        path.getOperator().add(0);
        path.getOperator().add(1);
        path.getCharacters().add('r');
        path.getChromosome().add(-Matrix.y);
        path.getChromosome().add(0);
        path.getChromosome().add(1);
        boolean[] checkPoint = initCheckPoint(Matrix.x * Matrix.y);
        checkPoint[0] = true;
        checkPoint[1] = true;
        while (!checkPathSuccess(checkPoint)){
            int sizeNow = path.getChromosome().size();
            int direct = path.getChromosome().get(sizeNow -2);
            int start = path.getChromosome().get(sizeNow - 1);
            int direct2 = path.getChromosome().get(sizeNow -3);
            int straight = Matrix.straight(direct,start);
            int left = Matrix.left(direct,start);
            int right = Matrix.right(direct, start);

            if(left != -1){
                if(Matrix.left(start, left) == direct2){
                    if(!checkPoint[left]){
                        path.getOperator().add(left);
                        path.getCharacters().add('l');
                        path.getChromosome().add(left);
                        checkPoint[left] = true;
                        continue;
                    }
                }
            }

            if(right != -1){
                if(Matrix.right(start, right) == direct2){
                    if(!checkPoint[right]){
                        path.getOperator().add(right);
                        path.getCharacters().add('r');
                        path.getChromosome().add(right);
                        checkPoint[right] = true;
                        continue;
                    }
                }
            }

            List<Integer> pointList = new ArrayList<>();


            if(straight != -1 && !checkPoint[straight]){
                pointList.add(straight);
            }
            if(pointList.size() == 1){
                path.getOperator().add(straight);
                path.getCharacters().add('s');
                path.getChromosome().add(straight);
                checkPoint[straight] = true;
                continue;
            }

            if(left != -1 && !checkPoint[left]){
                pointList.add(left);
            }

            if(right != -1 && !checkPoint[right]){
                pointList.add(right);
            }

            if(pointList.size() == 0){
                Path nextPoint = findNextPoint(direct, start, checkPoint);
                int nextchromosome = nextPoint.getChromosome().get(nextPoint.getChromosome().size() - 1);
                path.getOperator().add(nextchromosome);
                path.getCharacters().addAll(nextPoint.getCharacters());
                path.getChromosome().addAll(nextPoint.getChromosome());
                checkPoint[nextchromosome] = true;
            } else {
                int randomInt = rand.nextInt(pointList.size());
                if(pointList.get(randomInt) == left){
                    path.getOperator().add(left);
                    path.getChromosome().add(left);
                    path.getCharacters().add('l');
                }

                if(pointList.get(randomInt) == right){
                    path.getOperator().add(right);
                    path.getCharacters().add('r');
                    path.getChromosome().add(right);
                }
                checkPoint[pointList.get(randomInt)] = true;
            }
        }
        path.getOperator().remove(0);
        path.getChromosome().remove(0);
        return path;
    }
    public static Path initRandomStraightPath(){
        Path path = new Path();
        Random rand = new Random();
        path.getOperator().add(-Matrix.y);
        path.getOperator().add(0);
        path.getChromosome().add(-Matrix.y);
        path.getChromosome().add(0);
        boolean[] checkPoint = initCheckPoint(Matrix.x * Matrix.y);
        checkPoint[0] = true;
        while (!checkPathSuccess(checkPoint)){
            int direct = path.getChromosome().get(path.getChromosome().size() -2);
            int start = path.getChromosome().get(path.getChromosome().size() - 1);
            List<Integer> pointList = new ArrayList<>();

            int straight = Matrix.straight(direct,start);
            if(straight != -1 && !checkPoint[straight]){
                pointList.add(straight);
            }
            if(pointList.size() == 1){
                path.getOperator().add(straight);
                path.getCharacters().add('s');
                path.getChromosome().add(straight);
                checkPoint[straight] = true;
                continue;
            }
            int left = Matrix.left(direct,start);
            if(left != -1 && !checkPoint[left]){
                pointList.add(left);
            }

            int right = Matrix.right(direct, start);
            if(right != -1 && !checkPoint[right]){
                pointList.add(right);
            }

            if(pointList.size() == 0){
                Path nextPoint = findNextPoint(direct, start, checkPoint);
                int nextchromosome = nextPoint.getChromosome().get(nextPoint.getChromosome().size() - 1);
                path.getOperator().add(nextchromosome);
                path.getCharacters().addAll(nextPoint.getCharacters());
                path.getChromosome().addAll(nextPoint.getChromosome());
                checkPoint[nextchromosome] = true;
            } else {
                int randomInt = rand.nextInt(pointList.size());
                if(pointList.get(randomInt) == left){
                    path.getOperator().add(left);
                    path.getChromosome().add(left);
                    path.getCharacters().add('l');
                }

                if(pointList.get(randomInt) == right){
                    path.getOperator().add(right);
                    path.getCharacters().add('r');
                    path.getChromosome().add(right);
                }
                checkPoint[pointList.get(randomInt)] = true;
            }
        }
        path.getOperator().remove(0);
        path.getChromosome().remove(0);
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

              if(!checkPoint[startPath] && startPath != start){
                  path.getChromosome().remove(0);
                  path.getChromosome().remove(0);
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

    public static Path findPath(int start, int end, int direct) {
        int x = Matrix.x;
        int y = Matrix.y;
        List<Integer> points = new ArrayList<>();
        // todo: tim duong
        List<Path> pathList = new ArrayList<>();
        Path newPath= new Path();
        newPath.getOperator().add(direct);
        newPath.getOperator().add(start);
        pathList.add(newPath);
        boolean[] checkAppear = new boolean[x * y];
        for(int i = 0; i < checkAppear.length; i++){
            checkAppear[i] = false;
        }
        Path finalPath;
        while (true) {
            List<Integer> lastPoint = pathList.get(0).getOperator();
            if(lastPoint.get(lastPoint.size() - 1) == end){
                finalPath = pathList.get(0).copy();
                finalPath.getOperator().remove(0);
                finalPath.getOperator().remove(0);
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
                        straightPath.getOperator().add(straight);
                        pathList.add(straightPath);
                        checkAppear[straight] = true;
                    }
                }
                if(left >= 0 && left < x * y){
                    if(!checkAppear[left]){
                        Path leftPath = path.copy();
                        leftPath.getCharacters().add('l');
                        leftPath.getOperator().add(left);
                        pathList.add(leftPath);
                        checkAppear[left] = true;
                    }
                }
                if(right >= 0 && right < x * y){
                    if(!checkAppear[right]){
                        Path rightPath = path.copy();
                        rightPath.getCharacters().add('r');
                        rightPath.getOperator().add(right);
                        pathList.add(rightPath);
                        checkAppear[right] = true;
                    }

                }
                Collections.sort(pathList, (path1, t1) -> {
                    return (int)(path1.cost() - t1.cost());
                });
            }
        }
        return finalPath;
    }

    public static boolean statistic(int percent){
        Random random = new Random();
        int ranInt = random.nextInt(100);
        if(ranInt < percent){
            return true;
        }
        return false;
    }

}
