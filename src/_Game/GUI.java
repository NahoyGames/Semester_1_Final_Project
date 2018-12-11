package _Game;

import Utilities.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    // ** Graphics References **
    private BufferedImage manaIcon;

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


        // ** Get Graphics **
        try
        {
            manaIcon = ImageIO.read(new File("src/Graphics/manaIcon.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void drawFrame(Graphics g)
    {
        g.setColor(programColors[1]);
        g.fillOval(0, getHeight() - 100, getWidth(), 200);

        if (mousePressed)
        {
            g.fillRect((int)mousePosition.x, (int)mousePosition.y, 30, 30);
        }

        // Draw Hand
        Game.getPlayer(1).drawHand(g, getWidth(), getHeight());

        // Draw Heroes
        Game.getPlayer(1).drawHero(g, getWidth(), getHeight());
        Game.getPlayer(0).drawHero(g, getWidth(), getHeight());

        // Draw Minions
        Game.getBattlefield().getRow(1).drawMinions(g, getWidth(), getHeight());
        Game.getBattlefield().getRow(0).drawMinions(g, getWidth(), getHeight());

        // Draw Mana
        g.drawImage(manaIcon, 25, (int)(getHeight() - 60), 50, 50, this);
        g.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 25));
        g.drawString("" + Game.getPlayer(1).getMana(), 40, (int)(getHeight() - 25));

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
