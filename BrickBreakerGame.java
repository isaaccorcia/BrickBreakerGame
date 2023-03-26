/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;
import javax.swing.JFrame;

/**
 *
 * @author isaac
 */
public class BrickBreakerGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame brickObj = new JFrame();
        
        GamePlay playObj = new GamePlay();
        
        brickObj.setBounds(10, 10, 700, 600);
        brickObj.setTitle("BreakOut Ball");
        brickObj.setResizable(false);
        brickObj.setVisible(true);
        brickObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        brickObj.add(playObj);
    }
    
}
