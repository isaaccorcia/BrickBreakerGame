/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author isaac
 */
public class Bricks 
{
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    
    public Bricks(int rows, int columns)
    {
        map = new int[rows][columns];
        
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j< map[0].length; j++)
            {
                map[i][j] = 1;
            }
        }
        
        brickWidth = 540/columns;
        brickHeight = 150/rows;
    }
    
    public void draw(Graphics2D g)
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j< map[0].length; j++)
            {
                if(map[i][j] > 0)
                {
                    g.setColor(Color.white);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }
    
    public void setBrickValue(int value, int row, int column)
    {
        map[row][column] = value;
    }
}
