package com.thientan.form.manager;

import com.thientan.model.Location;
import com.thientan.model.LogIn;
import com.thientan.model.Pack;
import com.thientan.model.PackDetail;
import com.thientan.model.Product;
import com.thientan.model.TreatmentLocation;
import com.thientan.model.TreatmentRecord;
import com.thientan.model.User;
import com.thientan.service.LocationService;
import com.thientan.service.LogInService;
import com.thientan.service.PackService;
import com.thientan.service.ProductService;
import com.thientan.service.TreatmentLocationService;
import com.thientan.service.TreatmentRecordService;
import com.thientan.service.UserService;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Manager_User_Detail extends javax.swing.JFrame {

    String id;
    String treatment;
    User user;

    public Manager_User_Detail() {
        init();
        initComponents();
    }

    public Manager_User_Detail(String id) {
        init();
        initComponents();
        initComboBox();
        initTable();
        initData(id);
    }

    public void init() {
        JPanel panel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
                g2.setPaint(gra);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setContentPane(panel);
    }

    public void initComboBox() {
        //Init birth year
        List<String> years = new ArrayList<String>();
        years.add("--Year--");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1960; i <= year; i++) {
            years.add(Integer.toString(i));
        }
        txtYear.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));

        List<TreatmentLocation> treatment = TreatmentLocationService.getAll();
        List<String> treatmentName = new ArrayList<String>();
        for (int i = 0; i < treatment.size(); i++) {
            treatmentName.add((treatment.get(i).getName()));
        }

        treatmentName.add(0, "--Treament Location--");
        txtTreatment.setModel(new DefaultComboBoxModel<String>(treatmentName.toArray(new String[0])));
    }

    public void initTable() {
        relatedTable.getTableHeader().setOpaque(false);
        relatedTable.getTableHeader().setBackground(new Color(33, 105, 249));
        relatedTable.getTableHeader().setForeground(new Color(255, 255, 255));
        relatedTable.setForeground(Color.BLUE);
        relatedTable.setAutoCreateRowSorter(true);
        relatedTable.setCellSelectionEnabled(false);
        jScrollPane2.setBorder(new EmptyBorder(1, 1, 1, 1));
        
        recordTable.getTableHeader().setOpaque(false);
        recordTable.getTableHeader().setBackground(new Color(33, 105, 249));
        recordTable.getTableHeader().setForeground(new Color(255, 255, 255));
        recordTable.setForeground(Color.BLUE);
        recordTable.setAutoCreateRowSorter(true);
        recordTable.setCellSelectionEnabled(false);
        jScrollPane1.setBorder(new EmptyBorder(1, 1, 1, 1));

    }

    public void initData(String id) {
        this.id = id;
        //Init data
        user = UserService.getUserById(id);
        System.out.println(user.toString());
        txtID.setText(user.getNoID());
        txtName.setText(user.getName());
        txtYear.setSelectedItem(Integer.toString(user.getBirthYear()));
        txtAddress.setText(user.getAddress().toString());

        if (user.getTrmtLoca() != null) {
            TreatmentLocation location = user.getTrmtLoca();
            txtTreatment.setSelectedItem(location.getName());
            int available = location.getCapacity() - location.getOccupancy();
            txtAvailable.setText(Integer.toString(available));
        }

        //Init related table
        List<User> userList = new ArrayList<User>();
        userList = UserService.getAllRelatedUser(id);
        DefaultTableModel model = (DefaultTableModel) relatedTable.getModel();
        Object[] row = new Object[2];

        for (int i = 0; i < userList.size(); i++) {
            row[0] = userList.get(i).getName();
            String status = String.valueOf(userList.get(i).getStatus());
            row[1] = "F" + status;
            model.addRow(row);
        }

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);
        buttonGroup.add(radio4);

        status = user.getStatus();
        switch (status) {
            case 0:
                radio1.setSelected(true);
                radio2.setEnabled(false);
                radio3.setEnabled(false);
                break;
            case 1:
                radio2.setSelected(true);
                radio3.setEnabled(false);
                break;
            case 2:
                radio3.setSelected(true);
                break;
            case 4:
                radio4.setSelected(true);
                break;
        }

        //Init record table
        List<TreatmentRecord> logList = new ArrayList<TreatmentRecord>();
        logList = TreatmentRecordService.getAll(id);
        DefaultTableModel model1 = (DefaultTableModel) recordTable.getModel();
        Object[] row1 = new Object[3];

        for (int i = 0; i < logList.size(); i++) {
            row1[0] = logList.get(i).getRecTimestamp();
            if (logList.get(i).getTrmtLoca() == null) {
                row1[1] = "Not go to treatment location";

            } else {
                row1[1] = logList.get(i).getTrmtLoca().getName();

            }

            String status1 = String.valueOf(logList.get(i).getStatus());
            if (logList.get(i).getStatus() == 4) {
                row1[2] = "No covid";
            } else {
                row1[2] = "F" + status1;

            }
            model1.addRow(row1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtTreatment = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCapacity4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        radio3 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtAvailable = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        relatedTable = new javax.swing.JTable();
        lblCapacity3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordTable = new javax.swing.JTable();
        radio4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("USER INFORMATION");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setText("Birth Year");

        btnClose.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnClose.setText("X");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Address:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setText("jTextField1");
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Full Name:");

        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtYear.setEnabled(false);

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setText("txtName");
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        txtAddress.setEditable(false);
        txtAddress.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(255, 255, 255));
        txtAddress.setText("txtAddress");
        txtAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        txtTreatment.setEditable(true);
        txtTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtTreatment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtTreatmentItemStateChanged(evt);
            }
        });
        txtTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTreatmentActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Treatment Location");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No ID:");

        lblCapacity4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity4.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity4.setText("Treament Record");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status:");

        radio1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio1.setForeground(new java.awt.Color(255, 255, 255));
        radio1.setText("F0");

        radio2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio2.setForeground(new java.awt.Color(255, 255, 255));
        radio2.setText("F1");

        radio3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio3.setForeground(new java.awt.Color(255, 255, 255));
        radio3.setText("F2");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Slot available:");

        txtAvailable.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtAvailable.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailable.setText("0");

        btnSave1.setBackground(new java.awt.Color(0, 255, 51));
        btnSave1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("SAVE");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);

        relatedTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        relatedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        relatedTable.setEnabled(false);
        jScrollPane2.setViewportView(relatedTable);
        if (relatedTable.getColumnModel().getColumnCount() > 0) {
            relatedTable.getColumnModel().getColumn(1).setMinWidth(80);
            relatedTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            relatedTable.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        lblCapacity3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity3.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity3.setText("Related User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCapacity3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCapacity3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        recordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Timestamp", "Treatment Location", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(recordTable);
        if (recordTable.getColumnModel().getColumnCount() > 0) {
            recordTable.getColumnModel().getColumn(2).setMinWidth(80);
            recordTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            recordTable.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        radio4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio4.setForeground(new java.awt.Color(255, 255, 255));
        radio4.setText("No covid");
        radio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAddress))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtName)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtID)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCapacity4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(radio1)
                                        .addGap(14, 14, 14)
                                        .addComponent(radio2)
                                        .addGap(14, 14, 14)
                                        .addComponent(radio3)
                                        .addGap(10, 10, 10)
                                        .addComponent(radio4)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {radio1, radio2, radio3, radio4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txt)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radio1)
                            .addComponent(radio2)
                            .addComponent(radio3)
                            .addComponent(radio4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCapacity4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {radio1, radio2, radio3, radio4});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        handleClosing();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtTreatmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtTreatmentItemStateChanged
        String location = txtTreatment.getSelectedItem().toString();

        TreatmentLocation treatmentlocation = new TreatmentLocation();
        treatmentlocation = TreatmentLocationService.getByName(location);

        int available = treatmentlocation.getCapacity() - treatmentlocation.getOccupancy();
        txtAvailable.setText(Integer.toString(available));
    }//GEN-LAST:event_txtTreatmentItemStateChanged

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed

//        String treatmentID = "DC0005";
//        Location location = new Location(addressId, "a", "a", "a", "a");
//        TreatmentLocation treatmentLocation = new TreatmentLocation(id, na, location, oc, ca);
        String treatmentName = txtTreatment.getSelectedItem().toString();
        int statuss;
        if (radio1.isSelected()) {
            statuss = 0;
        } else if (radio2.isSelected()) {
            statuss = 1;
        } else if (radio3.isSelected()){
            statuss = 2;
        } else {
            statuss = 4;
        }
        
        if (status == 1 && statuss == 0){
            List<User> related = UserService.getAllRelatedUser(id); 
            for(User i : related){
                if(i.getStatus() == 2){
                    i.setStatus(1);
                    UserService.UpdStatus(i);
                }
            }
        }
        TreatmentLocation location = TreatmentLocationService.getByName(treatmentName);
        if (location != null) {
            int available = location.getCapacity() - location.getOccupancy();
            if (available == 0) {
                JOptionPane.showMessageDialog(null, "This treament location is full! Please try another");
            } else {
                user.setStatus(statuss);
                user.setTrmtLoca(location);
                if (UserService.UpdTreatmentLocation(user)) {
                    System.out.println("Update treament location id");
                }

            }
        }
       
        dispose();



    }//GEN-LAST:event_btnSave1ActionPerformed

    private void txtTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTreatmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTreatmentActionPerformed

    private void radio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio4ActionPerformed

    private void handleClosing() {
        if (hasUnsaveData()) {
            int answer = showWarningMessage();

            switch (answer) {
                case JOptionPane.YES_OPTION:
                    System.out.println("Save and Quit");
                    break;

                case JOptionPane.NO_OPTION:
                    System.out.println("Don't Save and Quit");
                    dispose();
                    break;

                case JOptionPane.CANCEL_OPTION:
                    System.out.println("Don't Quit");
                    dispose();
                    break;
            }
        } else {
            dispose();
        }
    }

    private int showWarningMessage() {
        String[] buttonLabels = new String[]{"Yes", "Cancel"};
        String defaultOption = buttonLabels[0];
        Icon icon = null;

        return JOptionPane.showOptionDialog(this,
                "There's still something unsaved.\n"
                + "Do you want to save before exiting?",
                "Warning",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                buttonLabels,
                defaultOption);
    }

    private boolean hasUnsaveData() {
////        int ca = (int) boxCapacity.getValue();
////        int oc = (int) boxOccupancy.getValue();
//        String na = txtTreatment.getSelectedItem().toString();
//
//        if (ca != this.capacity || oc != this.occupancy || !na.equals(this.name)) {
//                    System.out.println(ca);
//
//            return true;
//        }         
        return false;
    }

    public static void main(String id) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_User_Detail(id).setVisible(true);
            }
        });
    }
    private int status;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCapacity3;
    private javax.swing.JLabel lblCapacity4;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio3;
    private javax.swing.JRadioButton radio4;
    private javax.swing.JTable recordTable;
    private javax.swing.JTable relatedTable;
    private javax.swing.JLabel txt;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JLabel txtAvailable;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JComboBox<String> txtTreatment;
    private javax.swing.JComboBox<String> txtYear;
    // End of variables declaration//GEN-END:variables

    public static class ButtonCellRenderer extends JButton implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            if (value != null) {
//                setText("Delete");
//            } else {
//                setText("Delete Me");
//            }
//            if (isSelected) {
//                setForeground(table.getSelectionForeground());
//                setBackground(table.getSelectionBackground());
//            } else {
//                setForeground(table.getForeground());
//                setBackground(UIManager.getColor("Button.background"));
//            }
            setText("Delete");
            setForeground(Color.white);
            setBackground(Color.red);

            return this;
        }
    }

    public static class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {

        private JButton editor;
        private Object value;
        private int row;
        private JTable table;

        public ButtonCellEditor() {
            editor = new JButton();
            editor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (table != null) {
                        fireEditingStopped();
                        TableModel model = table.getModel();
                        if (model instanceof DefaultTableModel) {
                            ((DefaultTableModel) model).removeRow(row);
                        }
                    }
                }
            });
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }

        @Override
        public Object getCellEditorValue() {
            return value;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.value = value;
//            editor.setBackground(Color.red);
//            if (value != null) {
//                editor.setText("Delete row " + value.toString());
//            } else {
//                editor.setText("Delete Me");
//            }
            if (isSelected) {
                editor.setForeground(table.getSelectionForeground());
                editor.setBackground(table.getSelectionBackground());
            } else {
                editor.setForeground(table.getForeground());
                editor.setBackground(UIManager.getColor("Button.background"));
            }

            return editor;
        }
    }
}
