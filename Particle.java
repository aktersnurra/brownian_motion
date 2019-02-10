
package brownian_motion;


import java.util.Random;

/**
 * Created by Gustaf on 2016-02-03.
 */

class Particle {
    Double x, y;
    Integer L = 2;
    Boolean isMoving = true,isTracked = false;
    private int startingPos = View.boardHeight / 3;

    Particle() {
        x = new Random().nextDouble() * startingPos + startingPos;
        y = new Random().nextDouble() * startingPos + startingPos;
    }

    Particle(double xs, double ys) {
        x = xs;
        y = ys;
    }

    void randomMove() {
        x = x + this.L * Math.cos(new Random().nextDouble() * 2 * Math.PI);
        y = y + this.L * Math.sin(new Random().nextDouble() * 2 * Math.PI);
    }
}

