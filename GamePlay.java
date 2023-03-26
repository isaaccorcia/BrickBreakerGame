/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author isaac
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener
{
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    
    private Timer timer;
    private int delay = 8;
    
    private int playerX = 310;
    
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    
    private int ballDirectionX = -1;
    private int ballDirectionY = -2;
    
    private Bricks brick;
    
    public GamePlay()
    {
        brick = new Bricks(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(delay, this);
        timer.start();
    }
    
    
    public void paint(Graphics g)
    {
        //background
        
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //drawing map
        
        brick.draw((Graphics2D)g);
        
        //borders
        
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);
        
        //the paddle
        
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        
        //the ball
        
        g.setColor(Color.yellow);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);
        
        if (totalBricks <= 0)
        {
            play = false;
            ballDirectionX = 0;
            ballDirectionY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!", 260, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        if(ballPositionY > 570)
        {
            play = false;
            ballDirectionX = 0;
            ballDirectionY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: "+score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        
        g.dispose();
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            timer.start();
            
            if(play)
            {
                if(new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
                {
                    ballDirectionY = -ballDirectionY;
                }
                
                A: for(int i = 0; i < brick.map.length; i++)
                {
                    for(int j = 0; j < brick.map[0].length; j++)
                    {
                        if(brick.map[i][j] > 0)
                        {
                            int brickX = j*brick.brickWidth + 80;
                            int brickY = i*brick.brickHeight + 50;
                            int brickWidth = brick.brickWidth;
                            int brickHeight = brick.brickHeight;
                            
                            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                            Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20, 20);
                            Rectangle brickRect = rect;
                            
                            if (ballRect.intersects(brickRect))
                            {
                                brick.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                if (ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x +brickRect.width)
                                {
                                    ballDirectionX = -ballDirectionX;
                                }
                                else
                                {
                                    ballDirectionY = -ballDirectionY;
                                }
                                break A;
                            }
                        }
                    }
                }
                ballPositionX += ballDirectionX;
                ballPositionY += ballDirectionY;
                
                if(ballPositionX < 0)
                {
                    ballDirectionX = -ballDirectionX;
                }
                
                if(ballPositionY < 0)
                {
                    ballDirectionY = -ballDirectionY;
                }
                
                if(ballPositionX > 670)
                {
                    ballDirectionX = -ballDirectionX;
                }
            }
            repaint();

    }
    @Override
    public void keyTyped(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void keyReleased(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    @Override
    public void keyPressed(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX >= 600)
            {
                playerX = 600;
            }
            else
            {
                moveRight();
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT)
        {
             if(playerX < 10)
            {
                playerX = 10;
            }
            else
            {
                moveLeft();
            }
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play = true;
                ballPositionX = 120;
                ballPositionY = 350;
                ballDirectionX = -1;
                ballDirectionY = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                brick = new Bricks(3, 7);
                
                repaint();
            }
        }
    }
    
    public void moveRight()
    {
        play = true;
        playerX += 20;
    }
    
    public void moveLeft()
    {
        play = true;
        playerX -= 20;
    }

    

    
    
}
