package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import logic.Pong;

public class PongFrame extends JFrame
{

    private JPanel jContentPane = null;
    private Pong panel = null;

    private Pong getPanel()
    {
        if (panel == null)
            panel = new Pong();
        return panel;
    }

    public PongFrame()
    {
        super();
        initialize();

        // pobieranie z klawiatury
        this.addKeyListener(new KeyAdapter()
        {

            // klawisz wciśnięty
            @Override
            public void keyPressed(KeyEvent evt)
            {
                formKeyPressed(evt);
            }

            // klawisz puszczony
            @Override
            public void keyReleased(KeyEvent evt)
            {
                formKeyReleased(evt);
            }
        });
    }

    private void formKeyPressed(KeyEvent evt)
    {
        panel.keyPressed(evt);
    }

    private void formKeyReleased(KeyEvent evt)
    {
        panel.keyReleased(evt);
    }

    private void initialize()
    {

        this.setResizable(false);
        this.setBounds(new Rectangle(312, 184, 250, 250)); // pozycja na pulpicei
        this.setMinimumSize(new Dimension(400, 250));
        this.setMaximumSize(new Dimension(400, 250));
        this.setContentPane(getJContentPane());
        this.setTitle("Pong");
    }

    private JPanel getJContentPane()
    {
        if (jContentPane == null)
        {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getPanel(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                PongFrame thisClass = new PongFrame();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(false);
            }
        });
    }
}
