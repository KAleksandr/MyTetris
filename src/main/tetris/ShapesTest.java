package main.tetris;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapesTest {
    Shapes shapes = new Shapes(new TetrisApp());

    @Test
    public void testIsTouchGround() throws Exception {
        Assert.assertFalse(shapes.isTouchGround());
    }

    @Test
    public void testIsCrossGround() throws Exception {
        Assert.assertFalse(shapes.isCrossGround());
    }

    @Test
    public void testIsTouchWall() throws Exception {
        Assert.assertFalse(shapes.isTouchWall(160));
    }

    @Test
    public void testIsWrongPosition() throws Exception {
        Assert.assertFalse(shapes.isWrongPosition());
    }
}