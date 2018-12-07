package _Game;

import Utilities.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements ActionListener
{
    private Color[] programColors = new Color[]
            {
                new Color(28, 28, 29),
                new Color(71, 79, 91)
            };

    // ** Hitbox Stuff **
    private boolean mousePressed = false;
    private Vector2 mousePosition = Vector2.zero();

    public GUI()
    {
        setBackground(programColors[0]);

        new Timer(20, this).start();

        // ** Mouse Listener **
        this.addMouseListener(new MouseListener()
        {
            // ** Mouse Listener
            @Override
            public void mouseClicked(MouseEvent evt)
            {

            }

            @Override
            public void mousePressed(MouseEvent evt)
            {
                mousePressed = true;

                mousePosition.x = evt.getX();
                mousePosition.y = evt.getY();

                ((InputPlayer)Game.getPlayer(1)).detectCollision(mousePosition, getWidth(), getHeight());
            }

            @Override
            public void mouseReleased(MouseEvent evt)
            {
                mousePressed = false;

            }

            @Override
            public void mouseEntered(MouseEvent evt)
            {
            }

            @Override
            public void mouseExited(MouseEvent evt)
            {
                mousePressed = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                mousePosition.x = e.getX();
                mousePosition.y = e.getY();
            }
        });
    }

    public void drawFrame(Graphics g)
    {
        g.setColor(programColors[1]);
        g.fillOval(0, getHeight() - 100, getWidth(), 200);

        if (mousePressed)
        {
            g.fillRect((int)mousePosition.x, (int)mousePosition.y, 30, 30);
        }

        Game.getPlayer(1).drawHand(g, getWidth(), getHeight());

        // Draw Minions
        Game.getBattlefield().getRow(1).drawMinions(g, getWidth(), getHeight());
        Game.getBattlefield().getRow(0).drawMinions(g, getWidth(), getHeight());

    }

    // ** Action Listener **
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
