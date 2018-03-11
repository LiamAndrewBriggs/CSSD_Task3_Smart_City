package SmartCity;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class UserInterface extends javax.swing.JFrame {

    private Mothership motherShip = new Mothership();
    private ArrayList<SensorStation> sensorStations;
    private ArrayList<SensorMonitor> sensorMonitors;
    private SensorStation currentSensorStation; 
    private SensorMonitor currentSensorMonitor;
       
    
    public UserInterface() {
        initComponents();
        start();
    }
     
    public void start()
    {
        TableColumnModel tcm = sensorMonitorTable.getColumnModel();
        sensorMonitorTable.removeColumn(tcm.getColumn(4));
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
        else {
            sensorMonitorSelectFrame.show(false);
            sensorMonitorUpdateFrame.show(true);
            Insets insets = sensorMonitorUpdateFrame.getInsets();
            sensorMonitorUpdateFrame.setSize(new Dimension(insets.left + insets.right + 593,
             insets.top + insets.bottom + 530));
        }
        
    }
    
    public void selectSensorStation()
    {
        int column = 3;
        int row = sensorStationTable.getSelectedRow();
        
        if(row == -1)
        {
            //alert
        }
        else {
            String sensorStationID = sensorStationTable.getModel().getValueAt(row, column).toString();
            currentSensorStation = motherShip.getSensorStation(sensorStationID);
        }
    }
    
    public void selectSensorMonitor()
    {
        int column = 4;
        int row = sensorMonitorTable.getSelectedRow();
        
        if(row == -1)
        {
            //alert
        }
        else {
            String sensorMonitorID = sensorMonitorTable.getModel().getValueAt(row, column).toString();
            currentSensorMonitor = currentSensorStation.getSensorMonitor(sensorMonitorID);
        }
    }
    
    public void updateSensorButtonClicked()
    {
        //switchScreen(sensorDetail);
       // populateSensorDetails(sensorMonitorA);
    }
    
    private void populateSensorMonitorList()
    {
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        
        sensorMonitors.forEach((thisMonitor) -> {
            String sensorName = thisMonitor.getSensor().getClass().getName().replace("SmartCity.", "");
            
            String status = "Active";
            
            if(thisMonitor.getStatus() == false)
            {
                status = "Not-Active";
            }
            
            model.addRow(new Object[]{sensorName.replace("Sensor", " Sensor"), thisMonitor.reading, status, thisMonitor.getInterval(), thisMonitor.getID()});
        });
    }
    
    private void populateSensorStationList()
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        sensorStations.forEach((SensorStation thisStation) -> {
            thisStation.addNewSensorMonitor();
            model.addRow(new Object[]{thisStation.getName(), "Column 2", "Column 3", thisStation.getID()});
        });
        
        TableColumnModel tcm = sensorStationTable.getColumnModel();
        tcm.removeColumn(tcm.getColumn(3));
    }
    
    private void populateSensorDetails()
    {
        String sensorName = currentSensorMonitor.getSensor().getClass().getName().replace("SmartCity.", "");
            
        if(currentSensorMonitor.getStatus() == false)
        {
            statusComboBox.setSelectedItem("Active");
        }
        else
        {
            statusComboBox.setSelectedItem("Not-Active");
        }
        
        updateDescriptionTextField.setText(sensorName.replace("Sensor", " Sensor"));
        
        frequencyUpdateField.setText(currentSensorMonitor.getInterval().toString());
            
    }
    
    private void sort() 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
        
        model = (DefaultTableModel)sensorMonitorTable.getModel();
        
        sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorMonitorTable.setRowSorter(sorter);
    }
    
    private void searchStation(String query) 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
        
        sorter.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void searchMonitor(String query) 
    {
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorMonitorTable.setRowSorter(sorter);
        
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
        searchMonitorField = new javax.swing.JTextField();
        backToHomeScreen = new javax.swing.JButton();
        updateSensorButton = new javax.swing.JButton();
        sensorMonitorUpdateFrame = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cancelUpdate = new javax.swing.JButton();
        updateSensor = new javax.swing.JButton();
        updateDescriptionTextField = new javax.swing.JTextField();
        statusComboBox = new javax.swing.JComboBox<>();
        frequencyUpdateField = new javax.swing.JTextField();
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
                "Sensor Description", "Current Data", "Status", "Frequency", "ID"
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

        searchMonitorField.setText("Search");
        searchMonitorField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchMonitorFieldKeyReleased(evt);
            }
        });

        backToHomeScreen.setText("Back");
        backToHomeScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backToHomeScreenMouseClicked(evt);
            }
        });

        updateSensorButton.setText("Update");
        updateSensorButton.setToolTipText("");
        updateSensorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSensorButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sensorMonitorSelectFrameLayout = new javax.swing.GroupLayout(sensorMonitorSelectFrame.getContentPane());
        sensorMonitorSelectFrame.getContentPane().setLayout(sensorMonitorSelectFrameLayout);
        sensorMonitorSelectFrameLayout.setHorizontalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                        .addComponent(backToHomeScreen)
                        .addGap(49, 49, 49)
                        .addComponent(updateSensorButton))
                    .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(searchMonitorField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        sensorMonitorSelectFrameLayout.setVerticalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToHomeScreen)
                    .addComponent(updateSensorButton))
                .addGap(18, 18, 18)
                .addComponent(searchMonitorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        sensorMonitorUpdateFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        sensorMonitorUpdateFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Update Sensor");

        jLabel2.setText("Description");

        jLabel3.setText("Status");

        jLabel4.setText("Frequency");

        cancelUpdate.setText("Cancel");
        cancelUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelUpdateMouseClicked(evt);
            }
        });

        updateSensor.setText("Update");
        updateSensor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSensorMouseClicked(evt);
            }
        });
        updateSensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSensorActionPerformed(evt);
            }
        });

        updateDescriptionTextField.setEditable(false);

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Not-Active" }));

        javax.swing.GroupLayout sensorMonitorUpdateFrameLayout = new javax.swing.GroupLayout(sensorMonitorUpdateFrame.getContentPane());
        sensorMonitorUpdateFrame.getContentPane().setLayout(sensorMonitorUpdateFrameLayout);
        sensorMonitorUpdateFrameLayout.setHorizontalGroup(
            sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorUpdateFrameLayout.createSequentialGroup()
                .addGroup(sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sensorMonitorUpdateFrameLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(sensorMonitorUpdateFrameLayout.createSequentialGroup()
                                .addComponent(cancelUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateSensor))
                            .addComponent(jLabel1)))
                    .addGroup(sensorMonitorUpdateFrameLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(updateDescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(statusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(frequencyUpdateField))))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        sensorMonitorUpdateFrameLayout.setVerticalGroup(
            sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorUpdateFrameLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(updateDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(frequencyUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(sensorMonitorUpdateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelUpdate)
                    .addComponent(updateSensor))
                .addGap(117, 117, 117))
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
            selectSensorStation();
            switchScreens();
            sensorMonitors = currentSensorStation.getSensorMonitors();
            populateSensorMonitorList();
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
        
        searchStation(query);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchMonitorFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMonitorFieldKeyReleased
        String query = searchMonitorField.getText().toLowerCase();
        
        searchMonitor(query);
    }//GEN-LAST:event_searchMonitorFieldKeyReleased

    private void backToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToHomeScreenMouseClicked
        currentSensorStation = null;
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        model.setRowCount(0);
                
        switchScreens();
    }//GEN-LAST:event_backToHomeScreenMouseClicked

    private void updateSensorButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSensorButtonMouseClicked
        selectSensorMonitor();
        switchScreens();
        populateSensorDetails(); 
    }//GEN-LAST:event_updateSensorButtonMouseClicked

    private void updateSensorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSensorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateSensorActionPerformed

    private void updateSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSensorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_updateSensorMouseClicked

    private void cancelUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelUpdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelUpdateMouseClicked

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
    private javax.swing.JButton cancelUpdate;
    private javax.swing.JTextField frequencyUpdateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removeSensorStation;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchMonitorField;
    private javax.swing.JFrame sensorMonitorSelectFrame;
    private javax.swing.JTable sensorMonitorTable;
    private javax.swing.JFrame sensorMonitorUpdateFrame;
    private javax.swing.JTable sensorStationTable;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JTextField updateDescriptionTextField;
    private javax.swing.JButton updateSensor;
    private javax.swing.JButton updateSensorButton;
    private javax.swing.JButton viewSensorStation;
    // End of variables declaration//GEN-END:variables


}
