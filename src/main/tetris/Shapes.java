package main.tetris;

import java.awt.*;
import java.util.ArrayList;

class Shapes {
   private TetrisApp tetrisApp;
   private ArrayList<Blocks> figure = new ArrayList<Blocks>();
   private int[][] shape = new int[4][4];
   private int type, size, color;
   private int x = 4, y = 0; // starting left up corner

   public Shapes(TetrisApp tetrisApp) {
       this.tetrisApp = tetrisApp;
       type = tetrisApp.random.nextInt(tetrisApp.SHAPES.length);
       size = tetrisApp.SHAPES[type][4][0];
       color = tetrisApp.SHAPES[type][4][1];
       if (size == 4) y = -1;
       for (int i = 0; i < size; i++)
           System.arraycopy(tetrisApp.SHAPES[type][i], 0, shape[i], 0, tetrisApp.SHAPES[type][i].length);
       createFromShape();
   }

   void createFromShape() {
       for (int x = 0; x < size; x++)
           for (int y = 0; y < size; y++)
               if (shape[y][x] == 1) figure.add(new Blocks(tetrisApp, x + this.x, y + this.y));
   }

   boolean isTouchGround() {
       for (Blocks block : figure) if (tetrisApp.mine[block.getY() + 1][block.getX()] > 0) return true;
       return false;
   }

   boolean isCrossGround() {
       for (Blocks block : figure) if (tetrisApp.mine[block.getY()][block.getX()] > 0) return true;
       return false;
   }

   void leaveOnTheGround() {
       for (Blocks block : figure) tetrisApp.mine[block.getY()][block.getX()] = color;
   }

   boolean isTouchWall(int direction) {
       for (Blocks block : figure) {
           if (direction == tetrisApp.LEFT && (block.getX() == 0 || tetrisApp.mine[block.getY()][block.getX() - 1] > 0)) return true;
           if (direction == tetrisApp.RIGHT && (block.getX() == tetrisApp.FIELD_WIDTH - 1 || tetrisApp.mine[block.getY()][block.getX() + 1] > 0))
               return true;
       }
       return false;
   }

   void move(int direction) {
       if (!isTouchWall(direction)) {
           int dx = direction - 38; // LEFT = -1, RIGHT = 1
           for (Blocks block : figure) block.setX(block.getX() + dx);
           x += dx;
       }
   }

   void stepDown() {
       for (Blocks block : figure) block.setY(block.getY() + 1);
       y++;
   }

   void drop() {
       while (!isTouchGround()) stepDown();
   }

   boolean isWrongPosition() {
       for (int x = 0; x < size; x++)
           for (int y = 0; y < size; y++)
               if (shape[y][x] == 1) {
                   if (y + this.y < 0) return true;
                   if (x + this.x < 0 || x + this.x > tetrisApp.FIELD_WIDTH - 1) return true;
                   if (tetrisApp.mine[y + this.y][x + this.x] > 0) return true;
               }
       return false;
   }

   void rotateShape(int direction) {
       for (int i = 0; i < size / 2; i++)
           for (int j = i; j < size - 1 - i; j++)
               if (direction == tetrisApp.RIGHT) { // clockwise
                   int tmp = shape[size - 1 - j][i];
                   shape[size - 1 - j][i] = shape[size - 1 - i][size - 1 - j];
                   shape[size - 1 - i][size - 1 - j] = shape[j][size - 1 - i];
                   shape[j][size - 1 - i] = shape[i][j];
                   shape[i][j] = tmp;
               } else { // counterclockwise
                   int tmp = shape[i][j];
                   shape[i][j] = shape[j][size - 1 - i];
                   shape[j][size - 1 - i] = shape[size - 1 - i][size - 1 - j];
                   shape[size - 1 - i][size - 1 - j] = shape[size - 1 - j][i];
                   shape[size - 1 - j][i] = tmp;
               }
   }

   void rotate() {
       rotateShape(tetrisApp.RIGHT);
       if (!isWrongPosition()) {
           figure.clear();
           createFromShape();
       } else
           rotateShape(tetrisApp.LEFT);
   }

   void paint(Graphics g) {
       for (Blocks block : figure) block.paint(g, color);
   }
}
