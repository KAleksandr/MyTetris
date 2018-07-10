package main.tetris;

import java.awt.*;

public class Blocks {
    private TetrisApp tetrisApp;
    private int x, y;

    public Blocks(TetrisApp tetrisApp, int x, int y) {
        this.tetrisApp = tetrisApp;
        setX(x);
        setY(y);
    }

    void setX(int x) { this.x = x; }
    void setY(int y) { this.y = y; }

    int getX() { return x; }
    int getY() { return y; }

    void paint(Graphics g, int color) {
        g.setColor(new Color(color));
        g.drawRoundRect(x* tetrisApp.BLOCK_SIZE+1, y* tetrisApp.BLOCK_SIZE+1, tetrisApp.BLOCK_SIZE-2, tetrisApp.BLOCK_SIZE-2, tetrisApp.ARC_RADIUS, tetrisApp.ARC_RADIUS);
    }
}
