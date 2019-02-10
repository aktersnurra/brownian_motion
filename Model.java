package brownian_motion;

/**
 * Created by Gustaf on 2016-02-03.
 */

class Model {
    boolean[][] adjMatrix;
    Particle[] particles;
    private int i;
    boolean stuckRight, stuckLeft, stuckUp, stuckDown, stuckLeftUp, stuckLeftDown, stuckRightUp, stuckRightDown, isOccupied;

    Model() {
        particles = new Particle[10000];
        for (i = 0; i <= (particles.length - 1); i++) {
            particles[i] = new Particle();
        }
        booleanMatrix();
    }

    void moveAll() {
        for (Particle p : particles) {
            if (p.isMoving) {
                checkOutOfBounds(p);
                isOccupied = adjMatrix[p.x.intValue()][p.y.intValue()];

                if (isOccupied) {
                    checkIfOccupied(p);
                    p.isMoving = false;
                    adjMatrix[p.x.intValue()][p.y.intValue()] = true;
                } else if (checksAdjacentPos(p)) {
                    p.isMoving = false;
                    adjMatrix[p.x.intValue()][p.y.intValue()] = true;

                } else {
                    p.randomMove();
                }
            }
        }
    }

    private void booleanMatrix() {
        adjMatrix = new boolean[View.boardWidth][View.boardHeight];

        java.util.Arrays.fill(adjMatrix[0], true);
        java.util.Arrays.fill(adjMatrix[View.boardWidth - 1], true);

        for (int row = 0; row <= View.boardWidth - 1; row++) {
            adjMatrix[row][0] = true;
            adjMatrix[row][View.boardWidth - 1] = true;
        }

    }

    private void checkOutOfBounds(Particle p) {
        if (p.x.intValue() <= 0) {
            p.x = (double) 1;
        }
        if (p.y.intValue() <= 0) {
            p.y = (double) 1;
        }
        if (p.y.intValue() >= (View.boardWidth - 2)) {
            p.y = (double) (View.boardWidth - 2);
        }
        if (p.x.intValue() >= (View.boardHeight - 2)) {
            p.x = (double) (View.boardHeight - 2);
        }
    }

    private boolean checksAdjacentPos(Particle p) {
        getSurroundings(p);

        if (stuckRight) {
            return true;
        } else if (stuckLeft) {
            return true;
        } else if (stuckUp) {
            return true;
        } else if (stuckDown) {
            return true;
        } else if (stuckLeftDown) {
            return true;
        } else if (stuckLeftUp) {
            return true;
        } else if (stuckRightDown) {
            return true;
        } else if (stuckRightUp) {
            return true;
        } else {
            return false;
        }
    }

    private void checkIfOccupied(Particle p) {
        getSurroundings(p);

        if (!stuckRight) {
            p.y = p.y - 1;

        } else if (!stuckLeft) {
            p.y = p.y + 1;

        } else if (!stuckUp) {
            p.x = p.x + 1;

        } else if (!stuckDown) {
            p.x = p.x - 1;

        } else if (!stuckLeftDown) {
            p.x = p.x - 1;
            p.y = p.y - 1;

        } else if (!stuckLeftUp) {
            p.x = p.x + 1;
            p.y = p.y - 1;

        } else if (!stuckRightDown) {
            p.x = p.x - 1;
            p.y = p.y + 1;

        } else if (!stuckRightUp) {
            p.x = p.x + 1;
            p.y = p.y + 1;
        }
    }

    private void getSurroundings(Particle p) {
        stuckRight = adjMatrix[p.x.intValue()][p.y.intValue() - 1];
        stuckLeft = adjMatrix[p.x.intValue()][p.y.intValue() + 1];
        stuckUp = adjMatrix[p.x.intValue() + 1][p.y.intValue()];
        stuckDown = adjMatrix[p.x.intValue() - 1][p.y.intValue()];
        stuckLeftUp = adjMatrix[p.x.intValue() - 1][p.y.intValue() - 1];
        stuckLeftDown = adjMatrix[p.x.intValue() + 1][p.y.intValue() - 1];
        stuckRightUp = adjMatrix[p.x.intValue() - 1][p.y.intValue() + 1];
        stuckRightDown = adjMatrix[p.x.intValue() + 1][p.y.intValue() + 1];
    }

    void setL(int value) {
        for (Particle p : particles) {
            p.L = value;
        }
    }

    int getL() {
        return particles[0].L;
    }

    int getXPos(int n) {
        return particles[n].x.intValue();
    }

    int getYPos(int n) {
        return particles[n].y.intValue();
    }

}




