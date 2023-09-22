package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


// I could not figure out the Canvas.java implementation, I couldn't find the information on the slides or code :(
// I'm just going to add it when the Prof Rice releases the solution
public class Canvas extends JPanel
{
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    
        Graphics2D graphics = (Graphics2D) g;
        //this was supposed to print my name in the corner
        graphics.drawString("Cecilia Romero", 25,20);
        //Rectangle size = getBounds();

       for (int i = 8; i >0; i --)
       {
        graphics.setColor(Color.RED);
        graphics.drawLine(800-(i*20), 800-(i*20),400-(i*20), 400-(i*20));
        graphics.setColor(Color.RED);
        graphics.drawLine(800-(i*30), 800-(i*30),400-(i*30), 400-(i*30));
       }


        

    
        setVisible(true);
    }
    
}