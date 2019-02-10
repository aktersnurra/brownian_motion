package brownian_motion;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Gustaf on 2016-02-22.
 */
public class Tracking extends JPanel {
    Model theModel;
    JTable theTable;
    DefaultTableModel theContent;
    Random rand;
    List<Integer> randNumbersList = new ArrayList<Integer>();
    HashMap<Integer, Integer> trackingMap = new HashMap<Integer, Integer>();
    String[] columnNames = {"Particle", "Coordinates", "State", "Tracking"};
    Object[][] data;

    Tracking(Model m) {
        theModel = m;
        getRandomInts();
        initializeTable();
        setHashMap();
        Timer timer = new Timer(20, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel theTableModel = (DefaultTableModel) theTable.getModel();
            for (int i = 0; i <= theTableModel.getRowCount() - 1; i++) {
                if (theModel.particles[randNumbersList.get(i)].isTracked) {
                    Object[] row = updateRow(i);
                    theTableModel.setValueAt(row[0], i, 1);
                    theTableModel.setValueAt(row[1], i, 2);
                }

            }
        }
    }

    void getRandomInts() {
        rand = new Random();
        while (!(randNumbersList.size() == 10)) {
            int randNumber = rand.nextInt(theModel.particles.length);
            if (randNumbersList.contains(randNumber)) {
                return;
            } else {
                randNumbersList.add(randNumber);
            }
        }

    }
    void setHashMap() {
        int index = 1;
        for (Integer k : randNumbersList) {
            trackingMap.put(k,index);
            index++;
        }
    }

    HashMap<Integer, Integer> getHashMap() {
        return trackingMap;
    }

    void initializeTable() {
        Object[] tempList;
        data = new Object[10][1];

        for (int i = 0; i <= 9; i++) {
            tempList = new Object[]{i + 1,
                    "Not tracking",
                    "N/A",
                    theModel.particles[i].isTracked};
            data[i] = tempList;

        }

        /*In order to get checkboxes in the table the following was copied from:
        *http://stackoverflow.com/questions/7391877/how-to-add-checkboxes-to-jtable-swing
        * _______________________________________________________________________________
        */
        theContent = new DefaultTableModel(data, columnNames);
        theTable = new JTable(theContent) {

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        theTable.setPreferredScrollableViewportSize(theTable.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(theTable);

        //_______________________________________________________________________

        /*Code from: https://community.oracle.com/thread/1356122?start=0&tstart=0
        * ________________________________________________________________________
        */
        theTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent t) {

                int row = t.getFirstRow();
                int column = t.getColumn();
                TableModel tableModel = (TableModel) t.getSource();
                String changedValue = tableModel.getValueAt(row, column).toString();
                //_________________________________________________________________________

                int actionColumn = (Integer) tableModel.getValueAt(row, 0);
                if (column == 3) {
                    if ((Boolean) tableModel.getValueAt(row, column) == true ||
                            (Boolean) tableModel.getValueAt(row, column) == false) {
                        setTracking(actionColumn, changedValue);
                    }
                }

            }
        });
        this.add(scrollPane);
    }

    Object[] updateRow(int i) {
        Object[] tempList;

        tempList = new Object[]{
                theModel.particles[randNumbersList.get(i)].x.intValue() +
                        " , " + theModel.particles[randNumbersList.get(i)].y.intValue(),
                getState(randNumbersList.get(i))};
        return tempList;
    }

    void setTracking(int n, String tracking) {
        int index = n - 1;
        boolean isTracking = Boolean.parseBoolean(tracking);
        int particleNumb = randNumbersList.get(index);
        theModel.particles[particleNumb].isTracked = isTracking;
    }

    String getState(int n) {
        String state;

        if (theModel.particles[n].isMoving) {
            state = "Moving";
            return state;
        } else {
            state = "Stuck";
            return state;
        }
    }


}
