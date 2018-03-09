package SmartCity;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class UserInterface extends javax.swing.JFrame {

    public UserInterface() {

        initComponents();
        start();
    }
    
    private Mothership motherShip = new Mothership();
    private ArrayList<SensorStation> sensorStations;
    private ArrayList<SensorMonitor> sensorMonitors;
    private SensorStation currentSensorStation; 
    private SensorMonitor currentSensorMonitor;

    
    public void start()
    {
        motherShip.addNewSensorStation();
        sensorStations = motherShip.getSensorStations();
        populateSensorStationList();
        sort();
    }
    
    public void switchScreens()
    {
        if(currentSensorMonitor == null && currentSensorStation == null) {
            this.setVisible(true);
            sensorMonitorSelectFrame.show(false);
        }
        else if (currentSensorMonitor == null) {
            this.setVisible(false);
            sensorMonitorSelectFrame.show(true);
            Insets insets = sensorMonitorSelectFrame.getInsets();
            sensorMonitorSelectFrame.setSize(new Dimension(insets.left + insets.right + 593,
             insets.top + insets.bottom + 530));
        }
        
    }
    
    public void selectSensorStation()
    {
        //currentSensorStation = motherShip.getSensorStation(sensorStationID);
    }
    
    public void selectSensorMonitor()
    {
        currentSensorMonitor = currentSensorStation.getSensorMonitor();
    }
    
    public void updateSensorButtonClicked()
    {
        //switchScreen(sensorDetail);
       // populateSensorDetails(sensorMonitorA);
    }
    
    private void populateSensorMonitorList()
    {
        //todo
    }
    
    private void populateSensorStationList()
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        for (SensorStation thisStation : sensorStations) {
            model.addRow(new Object[]{thisStation.getName(), "Column 2", "Column 3", thisStation.getID()});
        }
        
        TableColumnModel tcm = sensorStationTable.getColumnModel();
        tcm.removeColumn(tcm.getColumn(3));
    }
    
    private void populateSensorDetails()
    {
        //todo
    }
    
    private void sort() 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
    }
    
    private void search(String query) 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
        
        sorter.setRowFilter(RowFilter.regexFilter(query));
    }
    
    public void updateButtonClicked()
    {
        //sensorMonitors.setFrequency(frequency);
        //switchScreen(sensorStation);
    }
    
    public void changeFrequencyValue()
    {
        //todo
    }
    
    public void changeDescriptionValue()
    {
        //todo
    }
    
    public void changeStatusValue()
    {
        //todo
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sensorMonitorSelectFrame = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        sensorMonitorTable = new javax.swing.JTable();
        searchField1 = new javax.swing.JTextField();
        backToHomeScreen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sensorStationTable = new javax.swing.JTable();
        viewSensorStation = new javax.swing.JButton();
        addSensorStation = new javax.swing.JButton();
        removeSensorStation = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();

        sensorMonitorSelectFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        sensorMonitorSelectFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        sensorMonitorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor Description", "CurrentData", "Status", "Frequency", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(sensorMonitorTable);

        searchField1.setText("Search");
        searchField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchField1KeyReleased(evt);
            }
        });

        backToHomeScreen.setText("Back");
        backToHomeScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backToHomeScreenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sensorMonitorSelectFrameLayout = new javax.swing.GroupLayout(sensorMonitorSelectFrame.getContentPane());
        sensorMonitorSelectFrame.getContentPane().setLayout(sensorMonitorSelectFrameLayout);
        sensorMonitorSelectFrameLayout.setHorizontalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backToHomeScreen)
                    .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(searchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        sensorMonitorSelectFrameLayout.setVerticalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(backToHomeScreen)
                .addGap(18, 18, 18)
                .addComponent(searchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        sensorStationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor Station", "Location", "Number of Active Sensors ", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(sensorStationTable);
        sensorStationTable.getAccessibleContext().setAccessibleName("");

        viewSensorStation.setText("View Sensor Station");
        viewSensorStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewSensorStationButtonClicked(evt);
            }
        });

        addSensorStation.setText("Add Sensor Station");
        addSensorStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSensorButtonClicked(evt);
            }
        });

        removeSensorStation.setText("Remove Sensor Station");
        removeSensorStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeSensorStationButtonClicked(evt);
            }
        });

        searchField.setText("Search");
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewSensorStation)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addSensorStation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeSensorStation))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewSensorStation)
                    .addComponent(addSensorStation)
                    .addComponent(removeSensorStation))
                .addGap(18, 18, 18)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewSensorStationButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSensorStationButtonClicked
        int column = 3;
        int row = sensorStationTable.getSelectedRow();
        if(row == -1) {
            
        }
        else {
            String sensorStationID = sensorStationTable.getModel().getValueAt(row, column).toString();
            currentSensorStation = motherShip.getSensorStation(sensorStationID);
            switchScreens();
            //sensorMonitors = currentSensorStation.getSensorMonitors();
            populateSensorMonitorList();
        }
    }//GEN-LAST:event_viewSensorStationButtonClicked

    private void addSensorButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSensorButtonClicked
        //switchScreen(addSensor);
        
        //addSensorSequenceDiagram has same button doing different thing
        //no if condition is in diagram
        
        //currentSensorMonitor = currentSensorMonitor.SensorMonitor();
        //currentSensorStation.addSensorMonitor(currentSensorMonitor);
        //switchScreen(addSensor);
    }//GEN-LAST:event_addSensorButtonClicked

    private void removeSensorStationButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeSensorStationButtonClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_removeSensorStationButtonClicked

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String query = searchField.getText().toLowerCase();
        
        search(query);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchField1KeyReleased

    private void backToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToHomeScreenMouseClicked
        currentSensorStation = null;
        switchScreens();
    }//GEN-LAST:event_backToHomeScreenMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new UserInterface().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSensorStation;
    private javax.swing.JButton backToHomeScreen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeSensorStation;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchField1;
    private javax.swing.JFrame sensorMonitorSelectFrame;
    private javax.swing.JTable sensorMonitorTable;
    private javax.swing.JTable sensorStationTable;
    private javax.swing.JButton viewSensorStation;
    // End of variables declaration//GEN-END:variables


}
