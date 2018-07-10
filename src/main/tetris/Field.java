package main.tetris;

import javax.swing.*;
import java.awt.*;

class Field extends JPanel {
    private TetrisApp tetrisApp;

    public Field(TetrisApp tetrisApp) {
        this.tetrisApp = tetrisApp;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 0; x < tetrisApp.FIELD_WIDTH; x++)
            for (int y = 0; y < tetrisApp.FIELD_HEIGHT; y++) {
                if (x < tetrisApp.FIELD_WIDTH - 1 && y < tetrisApp.FIELD_HEIGHT - 1) {
                    g.setColor(Color.lightGray);
                    g.drawLine((x + 1) * tetrisApp.BLOCK_SIZE - 2, (y + 1) * tetrisApp.BLOCK_SIZE, (x + 1) * tetrisApp.BLOCK_SIZE + 2, (y + 1) * tetrisApp.BLOCK_SIZE);
                    g.drawLine((x + 1) * tetrisApp.BLOCK_SIZE, (y + 1) * tetrisApp.BLOCK_SIZE - 2, (x + 1) * tetrisApp.BLOCK_SIZE, (y + 1) * tetrisApp.BLOCK_SIZE + 2);
                }
                if (tetrisApp.mine[y][x] > 0) {
                    g.setColor(new Color(tetrisApp.mine[y][x]));
                    g.fill3DRect(x * tetrisApp.BLOCK_SIZE + 1, y * tetrisApp.BLOCK_SIZE + 1, tetrisApp.BLOCK_SIZE - 1, tetrisApp.BLOCK_SIZE - 1, true);
                }
            }
        if (tetrisApp.gameOver) {
            g.setColor(Color.white);
            for (int y = 0; y < tetrisApp.GAME_OVER_MSG.length; y++)
                for (int x = 0; x < tetrisApp.GAME_OVER_MSG[y].length; x++)
                    if (tetrisApp.GAME_OVER_MSG[y][x] == 1) g.fill3DRect(x * 11 + 18, y * 11 + 160, 10, 10, true);
        } else
            tetrisApp.figure.paint(g);
    }
}
