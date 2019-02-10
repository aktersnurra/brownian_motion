package brownian_motion;

import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * Created by Gustaf on 2016-02-11.
 */
class View extends JPanel {
    public static int boardHeight = 300, boardWidth = 300;
    Model theModel;
    Tracking theTracking;
    HashMap<Integer, Integer> trackingMap;


    View(Model m, Tracking t) {
        theModel = m;
        theTracking = t;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        trackingMap = theTracking.getHashMap();

        for (Particle p : theModel.particles) {
            if (p.isMoving && !p.isTracked) {
                g.setColor(Color.WHITE);
                g.fillOval(p.x.intValue(), p.y.intValue(), 1, 2);
            } else if (p.isTracked) {
                g.setColor(Color.WHITE);
                g.fillRect(p.x.intValue() - 2,p.y.intValue() - 10,20,10);

                g.setColor(Color.BLACK);
                g.fillOval(p.x.intValue(), p.y.intValue(), 1, 2);
                g.drawString(trackingMap.get(i).toString(), p.x.intValue(), p.y.intValue());

                g.setColor(Color.GRAY);
                g.drawLine(0, p.y.intValue(), getHeight(), p.y.intValue());
                g.drawLine(p.x.intValue(), 0, p.x.intValue(), getWidth());
                g.setColor(Color.YELLOW);
                g.fillOval(p.x.intValue(), p.y.intValue(), 4, 4);

            } else {
                g.setColor(Color.DARK_GRAY);
                g.fillOval(p.x.intValue(), p.y.intValue(), 1, 2);
            }
            i++;


        }
    }

}

