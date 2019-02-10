package brownian_motion;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gustaf on 2016-02-03.
 */
class BrownAnimation extends JFrame {


    BrownAnimation() {
        setLayout(new BorderLayout());
        this.setTitle("Brown Animation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        Model m = new Model();
        Tracking t = new Tracking(m);
        View v = new View(m,t);
        Manipulation manipulation = new Manipulation(m);

        this.add(v, LEFT_ALIGNMENT);
        this.add(manipulation);
        this.add(t);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new Simulation(m, v, manipulation), 0L, 20L, TimeUnit.MILLISECONDS);
        this.setVisible(true);
        this.pack();
    }


    public static void main(String[] arg) {
        new BrownAnimation();


    }
}
