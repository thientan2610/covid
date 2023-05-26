package com.thientan.form.manager;

import Networking.TCPClient;
import com.thientan.main.User_Main;
import com.thientan.model.Account;
import com.thientan.model.Location;
import com.thientan.model.Pack;
import com.thientan.model.PackDetail;
import com.thientan.model.Product;
import com.thientan.model.TreatmentLocation;
import com.thientan.model.TreatmentRecord;
import com.thientan.model.User;
import com.thientan.service.AccountService;
import com.thientan.service.LocationService;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Manager_User_Add extends javax.swing.JFrame {

    String id;

    public Manager_User_Add() {
        init();
        initComponents();
    }

    public Manager_User_Add(String id) {
        init();
        initComponents();
        initComboBox();
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

        //Init city
        List<String> city = new ArrayList<String>();
        city = LocationService.getAllCity();
        city.add(0, "--City--");
        txtCity.setModel(new DefaultComboBoxModel<String>(city.toArray(new String[0])));

        //Init ward
        List<String> ward = new ArrayList<String>();
        ward = LocationService.getAllWard();
        ward.add(0, "--Ward--");
        txtWard.setModel(new DefaultComboBoxModel<String>(ward.toArray(new String[0])));

        //Init district
        List<String> district = new ArrayList<String>();
        district = LocationService.getAllDistrict();
        district.add(0, "--District--");
        txtDistrict.setModel(new DefaultComboBoxModel<String>(district.toArray(new String[0])));

        //Init treatment location
        List<TreatmentLocation> treatment = TreatmentLocationService.getAll();
        List<String> treatmentName = new ArrayList<String>();
        for (int i = 0; i < treatment.size(); i++) {
            treatmentName.add((treatment.get(i).getName()));
        }

        treatmentName.add(0, "--Treament Location--");
        txtTreatment.setModel(new DefaultComboBoxModel<String>(treatmentName.toArray(new String[0])));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);

    }

    public void initData(String id) {
        //Init list user
        List<User> userList = UserService.getAllUser();
        DefaultTableModel model = (DefaultTableModel) relatedList.getModel();
        String[] userName = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            userName[i] = (userList.get(i).getName());
        }
        JComboBox c = new JComboBox(userName);
        relatedList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(c));

        String[] status = {"F0", "F1", "F2"};
        JComboBox c1 = new JComboBox(status);
        relatedList.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(c1));

        relatedList.getColumn("Delete").setCellRenderer(new ButtonCellRenderer());
        relatedList.getColumn("Delete").setCellEditor(new ButtonCellEditor());
        this.id = id;
        txtID.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = txtID.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    txtID.setEditable(true);
                } else {
                    txtID.setEditable(false);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        relatedList = new javax.swing.JTable();
        lblCapacity3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        txtCity = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDistrict = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtWard = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTreatment = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtAvailable = new javax.swing.JLabel();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        radio3 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

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
        jLabel1.setText("ADD NEW USER");
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

        relatedList.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        relatedList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "Delete"
            }
        ));
        jScrollPane2.setViewportView(relatedList);
        if (relatedList.getColumnModel().getColumnCount() > 0) {
            relatedList.getColumnModel().getColumn(1).setMinWidth(80);
            relatedList.getColumnModel().getColumn(1).setPreferredWidth(80);
            relatedList.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        lblCapacity3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity3.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity3.setText("Related User");

        btnSave.setBackground(new java.awt.Color(0, 255, 51));
        btnSave.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("City:");

        txtID.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtID.setText("No ID");
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIDMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Full Name:");

        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtName.setText("Full Name");
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });

        txtCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtCity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtCityItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("District");

        txtDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtDistrict.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtDistrictItemStateChanged(evt);
            }
        });
        txtDistrict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDistrictActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Ward");

        txtWard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtWard.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtWardItemStateChanged(evt);
            }
        });

        txtAddress.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAddress.setText("Address");
        txtAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAddressMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Treatment Location");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No ID:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status:");

        txtTreatment.setEditable(true);
        txtTreatment.setEnabled(false);
        txtTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtTreatment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtTreatmentItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Slot available:");

        txtAvailable.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtAvailable.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailable.setText("0");

        radio1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio1.setForeground(new java.awt.Color(255, 255, 255));
        radio1.setText("F0");
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });

        radio2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio2.setForeground(new java.awt.Color(255, 255, 255));
        radio2.setText("F1");
        radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio2ActionPerformed(evt);
            }
        });

        radio3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        radio3.setForeground(new java.awt.Color(255, 255, 255));
        radio3.setText("F2");
        radio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Add People");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radio1)
                        .addGap(14, 14, 14)
                        .addComponent(radio2)
                        .addGap(14, 14, 14)
                        .addComponent(radio3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCapacity3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCity, txtDistrict, txtWard});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {radio1, radio2, radio3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtWard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radio1)
                            .addComponent(radio2)
                            .addComponent(radio3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCapacity3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCity, txtDistrict, txtWard});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {radio1, radio2, radio3});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        handleClosing();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        //Init data
        String name = txtName.getText();
        String idno = txtID.getText();
        String addr = txtAddress.getText();
        String year = txtYear.getSelectedItem().toString();
        if(!name.isEmpty() && !name.equals("Full Name")&& !idno.isEmpty() && !idno.equals("No ID") && !addr.isEmpty() && !addr.equals("Address") && !year.equals("--Year--")){
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setNoID(idno);
            user.setBirthYear((Integer.parseInt(year)));
            user.setDebit(0);
            int status;
            if (radio1.isSelected()) {
                status = 0;
                TreatmentLocation trtmentLocation = TreatmentLocationService.getByName(txtTreatment.getSelectedItem().toString());
                user.setTrmtLoca(trtmentLocation);
            } else if (radio2.isSelected()) {
                status = 1;
                user.setTrmtLoca(null);
            } else {
                status = 2;
                user.setTrmtLoca(null);
            }
            user.setStatus(status);
            Location location = new Location();
            int numLocation = LocationService.countAddress() + 1;
            String idLocation = "DC" + String.format("%04d", numLocation);
            location.setId(idLocation);
            location.setAddress(addr);
            location.setWard(txtWard.getSelectedItem().toString());
            location.setDistrict(txtDistrict.getSelectedItem().toString());
            location.setCity(txtCity.getSelectedItem().toString());
            user.setAddress(location);

            //List user related
            List<User> relatedUser = new ArrayList<User>();
            for (int i = 0; i < relatedList.getRowCount(); i++) {
                User userRelated = new User();
                Object relatedObj = relatedList.getModel().getValueAt(i, 0);
                String relatedName = relatedObj.toString();
                if (relatedName.equals("Click to choose user")) {
                    JOptionPane.showConfirmDialog(null, "Please choose user", "Warning", JOptionPane.CANCEL_OPTION);
                } else {
                    userRelated = UserService.getUserByName(relatedName);
                }
                relatedUser.add(userRelated);
            }
            Set<User> dataset = new HashSet<>(relatedUser);
            relatedUser = new ArrayList<User>(dataset);
            user.setRelatedList(relatedUser);

            try {
                Boolean check = UserService.addOne(user);            
                if(check){
                    TCPClient tcpClient;      
                    tcpClient = new TCPClient(id + "@");
                    tcpClient.connectServer();
                    tcpClient.start();
                } else
                    JOptionPane.showMessageDialog(this, "Add user failed", "Error", 1);
            } catch (SQLException ex) {            
                Logger.getLogger(Manager_User_Add.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Add user failed", "Error", 1); 
            }
        }else
            JOptionPane.showMessageDialog(this, "Fill all fields", "Error", 1); 
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtDistrictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDistrictActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDistrictActionPerformed

    private void txtCityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtCityItemStateChanged
        //Init district
        String cityName = txtCity.getSelectedItem().toString();
        List<String> district = new ArrayList<String>();

        if (cityName.equals("--City--")) {
            district = LocationService.getAllDistrict();

        } else {
            district = LocationService.getDistrictByCityName(cityName);

        }
        district.add(0, "--District--");
        txtDistrict.setModel(new DefaultComboBoxModel<String>(district.toArray(new String[0])));

        //Init ward
        List<String> ward = new ArrayList<String>();

        if (cityName.equals("--City--")) {
            ward = LocationService.getAllWard();

        } else {
            ward = LocationService.getWardByCityName(cityName);

        }
        ward.add(0, "--Ward--");
        txtWard.setModel(new DefaultComboBoxModel<String>(ward.toArray(new String[0])));
    }//GEN-LAST:event_txtCityItemStateChanged

    private void txtDistrictItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtDistrictItemStateChanged
        //Init ward
        String districtName = txtDistrict.getSelectedItem().toString();
        List<String> ward = new ArrayList<String>();

        if (districtName.equals("--District--")) {
            ward = LocationService.getAllWard();
        } else {
            ward = LocationService.getWardByDistrictName(districtName);
        }
        ward.add(0, "--Ward--");
        txtWard.setModel(new DefaultComboBoxModel<String>(ward.toArray(new String[0])));

        //Set city
        String city = LocationService.getCityByDistrictName(districtName);
        txtCity.setSelectedItem(city);
    }//GEN-LAST:event_txtDistrictItemStateChanged

    private void txtWardItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtWardItemStateChanged
        String wardName = txtWard.getSelectedItem().toString();
        //Set district
        List<String> name = new ArrayList<String>();

        if (wardName.equals("--Ward--")) {
            //Init city
            List<String> city = new ArrayList<String>();
            city = LocationService.getAllCity();
            city.add(0, "--City--");
            txtCity.setModel(new DefaultComboBoxModel<String>(city.toArray(new String[0])));

            //Init district
            List<String> district = new ArrayList<String>();
            district = LocationService.getAllDistrict();
            district.add(0, "--District--");
            txtDistrict.setModel(new DefaultComboBoxModel<String>(district.toArray(new String[0])));
        } else {
            name = LocationService.getCityDistrictByWardName(wardName);
            txtCity.setSelectedItem(name.get(0));
            txtDistrict.setSelectedItem(name.get(1));
        }

    }//GEN-LAST:event_txtWardItemStateChanged

    private void txtTreatmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtTreatmentItemStateChanged
        String location = txtTreatment.getSelectedItem().toString();

        TreatmentLocation treatmentlocation = new TreatmentLocation();
        treatmentlocation = TreatmentLocationService.getByName(location);

        int available = treatmentlocation.getCapacity() - treatmentlocation.getOccupancy();
        txtAvailable.setText(Integer.toString(available));
    }//GEN-LAST:event_txtTreatmentItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) relatedList.getModel();
        JLabel del = new JLabel();
        ImageIcon delIcon = new ImageIcon("U:\\Java\\TP\\management-covid\\src\\com\\kina\\icon\\delete.png");
        del.setIcon(delIcon);
        model.addRow(new Object[]{"Click to choose user", "F0", del});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMouseClicked
        txtID.setText("");
    }//GEN-LAST:event_txtIDMouseClicked

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNameMouseClicked
        txtName.setText("");
    }//GEN-LAST:event_txtNameMouseClicked

    private void txtAddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAddressMouseClicked
        txtAddress.setText("");
    }//GEN-LAST:event_txtAddressMouseClicked

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        // TODO add your handling code here:
        if (radio1.isSelected()) {
            txtTreatment.setEnabled(true);
        }
    }//GEN-LAST:event_radio1ActionPerformed

    private void radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio2ActionPerformed
        // TODO add your handling code here:
        if (radio2.isSelected()) {
            txtTreatment.setEnabled(false);
        }
    }//GEN-LAST:event_radio2ActionPerformed

    private void radio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio3ActionPerformed
        // TODO add your handling code here:
        if (radio3.isSelected()) {
            txtTreatment.setEnabled(false);
        }
    }//GEN-LAST:event_radio3ActionPerformed

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
////        String na = txtTreamentName.getText();
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
                new Manager_User_Add(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCapacity3;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio3;
    private javax.swing.JTable relatedList;
    private javax.swing.JLabel txt;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JLabel txtAvailable;
    private javax.swing.JComboBox<String> txtCity;
    private javax.swing.JComboBox<String> txtDistrict;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JComboBox<String> txtTreatment;
    private javax.swing.JComboBox<String> txtWard;
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
