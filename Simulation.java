package brownian_motion;


/**
 * Created by Gustaf on 2016-02-03.
 */

class Simulation implements Runnable {
    Model theModel;
    View theView;
    Manipulation theManipulation;
    Tracking theTracker;

    Simulation(Model m, View v, Manipulation ma) {
        theModel = m;
        theView = v;
        theManipulation = ma;
    }

    public void run() {
        if (theManipulation.getPause()) {
            return;
        } else {
            theModel.moveAll();
            theView.repaint();
            theView.revalidate();
        }
    }
}

