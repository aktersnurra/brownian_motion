package brownian_motion;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aktersnurra on 2016-02-14.
 */
class Manipulation extends JPanel {
    JSlider distSlider;
    JLabel distance;
    Model theModel;
    JButton pause, quit;
    JPanel actionPanel;
    boolean isPause;

    Manipulation(Model m) {
        theModel = m;

        actionPanel = new JPanel();


        distSlider = new JSlider(JSlider.VERTICAL, 1, 10, theModel.getL());
        distSlider.setMinorTickSpacing(1);
        distSlider.setPaintTicks(true);
        distSlider.setPaintLabels(true);
        ListenForSlider lForSlider = new ListenForSlider();
        distSlider.addChangeListener(lForSlider);

        distance = new JLabel("The current moving distance is: " + distSlider.getValue() + " ");

        pause = new JButton("Pause");
        quit = new JButton("Quit");

        ListenForButton lForButton = new ListenForButton();

        pause.addActionListener(lForButton);
        quit.addActionListener(lForButton);

        this.add(distSlider);
        this.add(distance);
        this.add(pause);
        this.add(quit);


    }

    void changeDistance(int changeL) {
        theModel.setL(changeL);
    }

    void setPause() {
        isPause ^= true;
    }

    boolean getPause() {
        return isPause;
    }


    private class ListenForSlider implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == distSlider) {
                distance.setText("The current moving distance is: " + distSlider.getValue());
                changeDistance(distSlider.getValue());
            }

        }
    }

    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pause) {
                setPause();
                if (getPause()) {
                    pause.setText("Start");
                } else {
                    pause.setText("Pause");
                }
            } else if (e.getSource() == quit) {
                System.exit(0);
            }
        }
    }
}
