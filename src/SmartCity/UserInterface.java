package SmartCity;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * User Interface Class,
 * This class is where the user interface is created
 * and user actions are handled
 * 
 * @author Liam
 * @version 1.00, 10 March 2018
 */
public class UserInterface extends javax.swing.JFrame {

    private Mothership motherShip = new Mothership();
    private ArrayList<SensorStation> sensorStations;
    private ArrayList<SensorMonitor> sensorMonitors;
    private SensorStation currentSensorStation; 
    private SensorMonitor currentSensorMonitor;
    static int mID = 101;
       
     /**
     *
     * User Interface Constructor,
     * 
     * This method starts the User Interface, and Initializes the components 
     *
     * @see UserInterface
     */
    public UserInterface() {
        initComponents();
        getConnection();
        start();
        Clock clock = Clock.getInstance();
        
    }
     
    /**
     *
     * Start method,
     * 
     * This method begins the Data Gathering of the system
     */
    public void start()
    {
        //Hide the ID column from view
        TableColumnModel tcm = sensorMonitorTable.getColumnModel();
        sensorMonitorTable.removeColumn(tcm.getColumn(4));
        
        tcm = sensorStationTable.getColumnModel();
        tcm.removeColumn(tcm.getColumn(3));
        
        sensorStations = motherShip.getSensorStations();
        populateSensorStationList();
        sort();
    }
    
    /**
     * Connects to the Java DB
     * @return
     */
    public static Connection getConnection()
    {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCity", "dran", "dran");
            return con;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    
     /**
     * For executing SQL queries
     */
    public void executeSQLQuery(String query) {
        Connection con = getConnection();
        Statement st;
        
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     *
     * Switch Screens Method,
     * 
     * This method returns the user to the home screen
     */
    public void switchScreens()
    {
        if(currentSensorMonitor == null && currentSensorStation == null) {
            this.setVisible(true);
            sensorMonitorSelectFrame.show(false);
        }
    }
    
    /**
      *
     * Switch Screen Method,
     * 
     * This method handles the selection of the screen the
     * user wishes to view
     *
     * @param selectedScreen the selected screen to view
     */
    public void switchScreen(String selectedScreen){
        if (null != selectedScreen) switch (selectedScreen) {
            case "addSensor":
                sensorMonitorAddFrame.setVisible(true);
                break;
            case "SensorStation":
                this.setVisible(false);
                sensorMonitorSelectFrame.setVisible(true);
                break;
            case "updateSensor":
                sensorMonitorUpdateFrame.setVisible(true);
                break;
            case "addStation":
                addSensorStationFrame.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    /**
     * Select the Sensor Station Method,
     * 
     * This method handles the selection of sensor station the
     * user wishes to view
     */
    public void selectSensorStation()
    {
        int column = 3;
        int row = sensorStationTable.getSelectedRow();
        
        String sensorStationID = sensorStationTable.getModel().getValueAt(row, column).toString();
        currentSensorStation = motherShip.getSensorStation(sensorStationID);
    }
    
    /**
     * Select the Sensor Monitor Method,
     * 
     * This method handles the selection of sensor monitor the
     * user wishes to view
     */
    public void selectSensorMonitor()
    {
        int column = 4;
        int row = sensorMonitorTable.getSelectedRow();
        
        String sensorMonitorID = sensorMonitorTable.getModel().getValueAt(row, column).toString();
        currentSensorMonitor = currentSensorStation.getSensorMonitor(sensorMonitorID);
    }
    
    private void populateSensorMonitorList()
    {
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        model.setRowCount(0);
        
        sensorMonitors.forEach((thisMonitor) -> {
            String sensorName = thisMonitor.getSensor().getClass().getName().replace("SmartCity.", "");
            
            String status = "Active";
            
            if(thisMonitor.getIsActive() == false)
            {
                status = "Not-Active";
            }
            
            model.addRow(new Object[]{sensorName.replace("Sensor", " Sensor"), thisMonitor.getReading(), status, thisMonitor.getInterval(), thisMonitor.getSensorMonitorID()});
        });
    }
    
    private void populateSensorStationList()
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        model.setRowCount(0);
        
        for (SensorStation thisStation : sensorStations){ 
            int count = 0;
            ArrayList<SensorMonitor> monitors = new ArrayList();
            monitors = thisStation.getSensorMonitors();
            for (SensorMonitor thisMonitor : monitors){
                if(thisMonitor.getIsActive() == true)
                {
                    count++;
                }
            }
                                
            model.addRow(new Object[]{thisStation.getStationName(), thisStation.getCoords().toString(), count, thisStation.getStationID()});
        }
    }
    
    private void populateSensorDetails()
    {
        String sensorName = currentSensorMonitor.getSensor().getClass().getName().replace("SmartCity.", "");
            
        if(currentSensorMonitor.getIsActive() == false)
        {
            statusComboBox.setSelectedItem("Not-Active");
        }
        else
        {
            statusComboBox.setSelectedItem("Active");
        }
        
        updateDescriptionTextField.setText(sensorName.replace("Sensor", " Sensor"));
        
        frequencyUpdateField.setText(currentSensorMonitor.getInterval().toString());
            
    }
    
    //Added to the back of the UI Data Searching the sort functionality enables 
    //added freedom to manipulate the data toi easily find what the user wants. 
    private void sort() 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
        
        model = (DefaultTableModel)sensorMonitorTable.getModel();
        
        sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorMonitorTable.setRowSorter(sorter);
    }
    
    //This searches through the sensor stations, by column titles 
    private void searchStation(String query) 
    {
        DefaultTableModel model = (DefaultTableModel)sensorStationTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorStationTable.setRowSorter(sorter);
        
        if(stationCombo.getSelectedItem().toString().equals("Station Name"))
        {
            sorter.setRowFilter(RowFilter.regexFilter(query,0));
        }
        else {
            sorter.setRowFilter(RowFilter.regexFilter(query,1));
        }
    }
    
    //This searches through the sensor monitors, by column titles 
    private void searchMonitor(String query) 
    {
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        
        sensorMonitorTable.setRowSorter(sorter);
        
        if(monitorSearchCombo.getSelectedItem().toString().equals("Description"))
        {
            sorter.setRowFilter(RowFilter.regexFilter(query,0));
        }
        else if(monitorSearchCombo.getSelectedItem().toString().equals("Status")) {
            sorter.setRowFilter(RowFilter.regexFilter(query,2));
        }
        else {
            sorter.setRowFilter(RowFilter.regexFilter(query,3));
        }
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
        addSensorButton = new javax.swing.JButton();
        monitorSearchCombo = new javax.swing.JComboBox<>();
        removeSensorMonitor = new javax.swing.JButton();
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
        sensorMonitorAddFrame = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cancelAdd = new javax.swing.JButton();
        addSensor = new javax.swing.JButton();
        statusAddComboBox = new javax.swing.JComboBox<>();
        frequencyAddField = new javax.swing.JTextField();
        descriptionSensor = new javax.swing.JComboBox<>();
        addSensorStationFrame = new javax.swing.JFrame();
        jLabel9 = new javax.swing.JLabel();
        sensorStationNameJLabel = new javax.swing.JLabel();
        sensorStationLocationJLabel = new javax.swing.JLabel();
        cancelAdd1 = new javax.swing.JButton();
        addSensorStation = new javax.swing.JButton();
        sensorStationNameJTextField = new javax.swing.JTextField();
        sensorStationLatitudeJLabel = new javax.swing.JLabel();
        sensorStationLatitudeJTextField = new javax.swing.JTextField();
        sensorStationLongitudeJLabel = new javax.swing.JLabel();
        sensorStationLongitudeJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        sensorStationTable = new javax.swing.JTable();
        viewSensorStation = new javax.swing.JButton();
        addSensorStationJButton = new javax.swing.JButton();
        removeSensorStation = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        stationCombo = new javax.swing.JComboBox<>();

        sensorMonitorSelectFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        sensorMonitorSelectFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sensorMonitorSelectFrame.setMinimumSize(new java.awt.Dimension(600, 575));

        sensorMonitorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor Description", "Current Data", "Status", "Frequency", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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

        addSensorButton.setText("Add");
        addSensorButton.setToolTipText("");
        addSensorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSensorButtonMouseClicked(evt);
            }
        });

        monitorSearchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Description", "Status", "Frequency" }));

        removeSensorMonitor.setText("Remove");
        removeSensorMonitor.setToolTipText("");
        removeSensorMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeSensorMonitorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sensorMonitorSelectFrameLayout = new javax.swing.GroupLayout(sensorMonitorSelectFrame.getContentPane());
        sensorMonitorSelectFrame.getContentPane().setLayout(sensorMonitorSelectFrameLayout);
        sensorMonitorSelectFrameLayout.setHorizontalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                        .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchMonitorField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                                .addComponent(backToHomeScreen)
                                .addGap(49, 49, 49)
                                .addComponent(updateSensorButton)
                                .addGap(37, 37, 37)
                                .addComponent(addSensorButton)))
                        .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(monitorSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(removeSensorMonitor)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        sensorMonitorSelectFrameLayout.setVerticalGroup(
            sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorSelectFrameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToHomeScreen)
                    .addComponent(updateSensorButton)
                    .addComponent(addSensorButton)
                    .addComponent(removeSensorMonitor))
                .addGap(18, 18, 18)
                .addGroup(sensorMonitorSelectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchMonitorField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monitorSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        sensorMonitorUpdateFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        sensorMonitorUpdateFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sensorMonitorUpdateFrame.setMinimumSize(new java.awt.Dimension(593, 530));

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

        sensorMonitorAddFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        sensorMonitorAddFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sensorMonitorAddFrame.setMinimumSize(new java.awt.Dimension(593, 530));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setText("Add Sensor");
        jLabel5.setToolTipText("");

        jLabel6.setText("Description");

        jLabel7.setText("Status");

        jLabel8.setText("Frequency");

        cancelAdd.setText("Cancel");
        cancelAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelAddMouseClicked(evt);
            }
        });

        addSensor.setText("Add");
        addSensor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSensorMouseClicked(evt);
            }
        });

        statusAddComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Not-Active" }));

        descriptionSensor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bin Sensor", "Flood Sensor", "Traffic Sensor" }));

        javax.swing.GroupLayout sensorMonitorAddFrameLayout = new javax.swing.GroupLayout(sensorMonitorAddFrame.getContentPane());
        sensorMonitorAddFrame.getContentPane().setLayout(sensorMonitorAddFrameLayout);
        sensorMonitorAddFrameLayout.setHorizontalGroup(
            sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorAddFrameLayout.createSequentialGroup()
                .addGroup(sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sensorMonitorAddFrameLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(sensorMonitorAddFrameLayout.createSequentialGroup()
                                .addComponent(cancelAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addSensor))
                            .addComponent(jLabel5)))
                    .addGroup(sensorMonitorAddFrameLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(statusAddComboBox, 0, 217, Short.MAX_VALUE)
                            .addComponent(frequencyAddField)
                            .addComponent(descriptionSensor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        sensorMonitorAddFrameLayout.setVerticalGroup(
            sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorMonitorAddFrameLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(descriptionSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addComponent(statusAddComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(frequencyAddField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(sensorMonitorAddFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelAdd)
                    .addComponent(addSensor))
                .addGap(117, 117, 117))
        );

        addSensorStationFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addSensorStationFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addSensorStationFrame.setMinimumSize(new java.awt.Dimension(593, 530));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel9.setText("Add Sensor Station");
        jLabel9.setToolTipText("");

        sensorStationNameJLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sensorStationNameJLabel.setText("Name");

        sensorStationLocationJLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sensorStationLocationJLabel.setText("Location");

        cancelAdd1.setText("Cancel");
        cancelAdd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelAdd1MouseClicked(evt);
            }
        });

        addSensorStation.setText("Add");
        addSensorStation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSensorStationMouseClicked(evt);
            }
        });

        sensorStationLatitudeJLabel.setText("Latitude:");
        sensorStationLatitudeJLabel.setToolTipText("");

        sensorStationLongitudeJLabel.setText("Longitude:");
        sensorStationLongitudeJLabel.setToolTipText("");

        javax.swing.GroupLayout addSensorStationFrameLayout = new javax.swing.GroupLayout(addSensorStationFrame.getContentPane());
        addSensorStationFrame.getContentPane().setLayout(addSensorStationFrameLayout);
        addSensorStationFrameLayout.setHorizontalGroup(
            addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                .addGroup(addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sensorStationLocationJLabel)
                            .addComponent(sensorStationNameJLabel)
                            .addComponent(sensorStationNameJTextField)
                            .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                                .addComponent(sensorStationLatitudeJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sensorStationLatitudeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(sensorStationLongitudeJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sensorStationLongitudeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel9))
                    .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(cancelAdd1)
                        .addGap(20, 20, 20)
                        .addComponent(addSensorStation)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        addSensorStationFrameLayout.setVerticalGroup(
            addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSensorStationFrameLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel9)
                .addGap(42, 42, 42)
                .addComponent(sensorStationNameJLabel)
                .addGap(15, 15, 15)
                .addComponent(sensorStationNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(sensorStationLocationJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sensorStationLatitudeJLabel)
                    .addComponent(sensorStationLatitudeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensorStationLongitudeJLabel)
                    .addComponent(sensorStationLongitudeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(addSensorStationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelAdd1)
                    .addComponent(addSensorStation))
                .addContainerGap(58, Short.MAX_VALUE))
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
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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

        addSensorStationJButton.setText("Add Sensor Station");
        addSensorStationJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSensorStationJButtonMouseClicked(evt);
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

        stationCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Station Name", "Location" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viewSensorStation)
                                .addGap(36, 36, 36)
                                .addComponent(addSensorStationJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeSensorStation))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(stationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewSensorStation)
                    .addComponent(addSensorStationJButton)
                    .addComponent(removeSensorStation))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewSensorStationButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSensorStationButtonClicked
            int row = sensorStationTable.getSelectedRow();
        
            if(row == -1)
            {
                showMessageDialog(null, "Please select a Sensor Station");
            
            }
            else {
                selectSensorStation();
                switchScreen("SensorStation");
                sensorMonitors = currentSensorStation.getSensorMonitors();
                populateSensorMonitorList();
            }
    }//GEN-LAST:event_viewSensorStationButtonClicked

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String query = searchField.getText();
        searchStation(query);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchMonitorFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMonitorFieldKeyReleased
        String query = searchMonitorField.getText();
        searchMonitor(query);
    }//GEN-LAST:event_searchMonitorFieldKeyReleased

    private void backToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backToHomeScreenMouseClicked
        currentSensorStation = null;
        DefaultTableModel model = (DefaultTableModel)sensorMonitorTable.getModel();
        model.setRowCount(0);
        
        populateSensorStationList();
                
        switchScreens();
    }//GEN-LAST:event_backToHomeScreenMouseClicked

    private void updateSensorButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSensorButtonMouseClicked
        
        int row = sensorMonitorTable.getSelectedRow();
        
        if(row == -1)
        {
            showMessageDialog(null, "Please select a Sensor Monitor");
        }
        else {
            selectSensorMonitor();
            switchScreen("updateSensor");
            populateSensorDetails(); 
        }
    }//GEN-LAST:event_updateSensorButtonMouseClicked

    private void updateSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSensorMouseClicked
        currentSensorMonitor.setFrequency(frequencyUpdateField.getText());
        currentSensorMonitor = null;
        sensorMonitorUpdateFrame.dispose();
        switchScreen("SensorStation");
        populateSensorMonitorList();
    }//GEN-LAST:event_updateSensorMouseClicked

    private void cancelUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelUpdateMouseClicked
        sensorMonitorUpdateFrame.dispose();
    }//GEN-LAST:event_cancelUpdateMouseClicked

    private void cancelAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelAddMouseClicked
        sensorMonitorAddFrame.dispose();
    }//GEN-LAST:event_cancelAddMouseClicked

    private void addSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSensorMouseClicked
       SensorMonitor aSensorMonitor = new SensorMonitor(statusAddComboBox.getSelectedItem(), Double.valueOf(frequencyAddField.getText()), descriptionSensor.getSelectedItem());
       currentSensorStation.addSensorMonitor(aSensorMonitor);
       populateSensorMonitorList();
       sensorMonitorAddFrame.dispose();
    }//GEN-LAST:event_addSensorMouseClicked

    private void addSensorButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSensorButtonMouseClicked
        switchScreen("addSensor");
    }//GEN-LAST:event_addSensorButtonMouseClicked

    private void cancelAdd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelAdd1MouseClicked
        addSensorStationFrame.dispose();
    }//GEN-LAST:event_cancelAdd1MouseClicked

    private void addSensorStationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSensorStationMouseClicked
       SensorStation aSensorStation = new SensorStation(sensorStationNameJTextField.getText(), Double.valueOf(sensorStationLatitudeJTextField.getText()), Double.valueOf(sensorStationLongitudeJTextField.getText()));
       motherShip.addNewSensorStation(aSensorStation);
       populateSensorStationList();
       addSensorStationFrame.dispose();
    }//GEN-LAST:event_addSensorStationMouseClicked

    private void removeSensorStationButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeSensorStationButtonClicked
        int row = sensorStationTable.getSelectedRow();
        
        if(row == -1)
        {
            showMessageDialog(null, "Please select a Sensor Station");
        }
        else {
            selectSensorStation();
            motherShip.removeSensorStation(currentSensorStation);
            populateSensorStationList();
        }
    }//GEN-LAST:event_removeSensorStationButtonClicked

    private void addSensorStationJButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSensorStationJButtonMouseClicked
        switchScreen("addStation");
    }//GEN-LAST:event_addSensorStationJButtonMouseClicked

    private void removeSensorMonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeSensorMonitorMouseClicked
        int row = sensorMonitorTable.getSelectedRow();
        
        if(row == -1)
        {
            showMessageDialog(null, "Please select a Sensor Monitor");
        }
        else {
            selectSensorMonitor();
            currentSensorStation.removeSensorMonitor(currentSensorMonitor);
            populateSensorMonitorList();
            currentSensorMonitor = null;
        }
    }//GEN-LAST:event_removeSensorMonitorMouseClicked

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
    private javax.swing.JButton addSensor;
    private javax.swing.JButton addSensorButton;
    private javax.swing.JButton addSensorStation;
    private javax.swing.JFrame addSensorStationFrame;
    private javax.swing.JButton addSensorStationJButton;
    private javax.swing.JButton backToHomeScreen;
    private javax.swing.JButton cancelAdd;
    private javax.swing.JButton cancelAdd1;
    private javax.swing.JButton cancelUpdate;
    private javax.swing.JComboBox<String> descriptionSensor;
    private javax.swing.JTextField frequencyAddField;
    private javax.swing.JTextField frequencyUpdateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> monitorSearchCombo;
    private javax.swing.JButton removeSensorMonitor;
    private javax.swing.JButton removeSensorStation;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchMonitorField;
    private javax.swing.JFrame sensorMonitorAddFrame;
    private javax.swing.JFrame sensorMonitorSelectFrame;
    private javax.swing.JTable sensorMonitorTable;
    private javax.swing.JFrame sensorMonitorUpdateFrame;
    private javax.swing.JLabel sensorStationLatitudeJLabel;
    private javax.swing.JTextField sensorStationLatitudeJTextField;
    private javax.swing.JLabel sensorStationLocationJLabel;
    private javax.swing.JLabel sensorStationLongitudeJLabel;
    private javax.swing.JTextField sensorStationLongitudeJTextField;
    private javax.swing.JLabel sensorStationNameJLabel;
    private javax.swing.JTextField sensorStationNameJTextField;
    private javax.swing.JTable sensorStationTable;
    private javax.swing.JComboBox<String> stationCombo;
    private javax.swing.JComboBox<String> statusAddComboBox;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JTextField updateDescriptionTextField;
    private javax.swing.JButton updateSensor;
    private javax.swing.JButton updateSensorButton;
    private javax.swing.JButton viewSensorStation;
    // End of variables declaration//GEN-END:variables


}
