package com.thientan.form.manager;

import com.thientan.model.Location;
import com.thientan.model.Product;
import com.thientan.model.TreatmentLocation;
import com.thientan.service.ProductService;
import com.thientan.service.TreatmentLocationService;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Manager_Product_Add extends javax.swing.JFrame {

    String id;
    String name;
    String unit;
    int price;
    String image;
    String imgPath = null;

    public Manager_Product_Add() {
        init();
        initComponents();
    }

    public Manager_Product_Add(String id) {
        init();
        initComponents();
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

    public void initData(String id) {
        txtID.setText(id);
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblCapacity = new javax.swing.JLabel();
        lblOccupancy = new javax.swing.JLabel();
        boxOccupancy = new javax.swing.JSpinner();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnChoose = new javax.swing.JButton();
        fieldImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setFocusTraversalPolicyProvider(true);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("ADD PRODUCT");
        lblTitle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCapacity.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity.setText("Unit:");

        lblOccupancy.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblOccupancy.setForeground(new java.awt.Color(255, 255, 255));
        lblOccupancy.setText("Price:");

        boxOccupancy.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        boxOccupancy.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        boxOccupancy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boxOccupancyMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boxOccupancyMousePressed(evt);
            }
        });
        boxOccupancy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxOccupancyKeyPressed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 255, 51));
        btnSave.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnClose.setText("X");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(204, 204, 204));
        txtID.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setText("Product Name");
        txtID.setBorder(null);
        txtID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtID.setEnabled(false);
        txtID.setFocusable(false);
        txtID.setOpaque(false);

        txtUnit.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUnit.setForeground(new java.awt.Color(255, 255, 255));
        txtUnit.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        txtUnit.setOpaque(false);
        txtUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUnitMouseClicked(evt);
            }
        });
        txtUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Product ID:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Product Name:");

        txtName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(12, 91, 160)));
        txtName.setOpaque(false);
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        btnChoose.setText("Chose image...");
        btnChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChooseMouseClicked(evt);
            }
        });
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        fieldImage.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        fieldImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(fieldImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOccupancy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUnit)
                            .addComponent(boxOccupancy)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCapacity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fieldImage, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChoose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOccupancy)
                    .addComponent(boxOccupancy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        handleClosing();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        //if has unsave data: save
        int price = (int) boxOccupancy.getValue();
        String na = txtName.getText();
        String unit = txtUnit.getText();

        System.out.println(na);
        System.out.println(unit);
        System.out.println(price);

        Product prod = new Product(this.id, na, price, unit, imgPath);
        if (!hasUnsaveData()) {

            ProductService.addOne(prod);
            dispose();
        } else {
            if (na.isBlank()) {
                txtName.setBorder(BorderFactory.createLineBorder(Color.red));
            }
            if (unit.isBlank()) {
                txtUnit.setBorder(BorderFactory.createLineBorder(Color.red));
            }
            if (price == 0) {
                boxOccupancy.setBorder(BorderFactory.createLineBorder(Color.red));
            }
            if (imgPath == null) {
                fieldImage.setBorder(BorderFactory.createLineBorder(Color.red));
            }

        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        //filter the file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);

        //User click on save in Jfile
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            fieldImage.setIcon(ResizeImage(path, null));

            imgPath = path;
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNameMouseClicked
        txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12,91,160)));
    }//GEN-LAST:event_txtNameMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitActionPerformed

    private void txtUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUnitMouseClicked
        txtUnit.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12,91,160)));

    }//GEN-LAST:event_txtUnitMouseClicked

    private void boxOccupancyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxOccupancyMouseClicked
        boxOccupancy.setBorder(BorderFactory.createEmptyBorder());

    }//GEN-LAST:event_boxOccupancyMouseClicked

    private void btnChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChooseMouseClicked
        fieldImage.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_btnChooseMouseClicked

    private void boxOccupancyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxOccupancyMousePressed
        boxOccupancy.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_boxOccupancyMousePressed

    private void boxOccupancyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxOccupancyKeyPressed
//        boxOccupancy.setBorder(BorderFactory.());
    }//GEN-LAST:event_boxOccupancyKeyPressed

    public ImageIcon ResizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(fieldImage.getWidth(), fieldImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImg);

        return icon;
    }

    private void handleClosing() {
        if (!hasUnsaveData()) {
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
        int price = (int) boxOccupancy.getValue();
        String na = txtName.getText();
        String unit = txtUnit.getText();

        if (price == 0 || unit.isBlank() || na.isBlank() || imgPath == null) {
            return true;
        }
        return false;
    }

    public static void main(String id) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_Product_Add(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner boxOccupancy;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel fieldImage;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblOccupancy;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
