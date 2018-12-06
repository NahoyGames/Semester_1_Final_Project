package _Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener
{
    private Color[] programColors = new Color[]
            {
                new Color(28, 28, 29),
                new Color(71, 79, 91)
            };

    public GUI()
    {
        setBackground(programColors[0]);

        new Timer(20, this).start();
    }

    public void drawFrame(Graphics g)
    {
        g.setColor(programColors[1]);
        g.fillOval(0, getHeight() - 100, getWidth(), 200);

        Game.getPlayer(1).drawHand(g, getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        drawFrame(g);
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        repaint();
    }

}
