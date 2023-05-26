package com.thientan.form.manager;

import com.thientan.model.Pack;
import com.thientan.model.PackDetail;
import com.thientan.model.Product;
import com.thientan.service.PackService;
import com.thientan.service.ProductService;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class Manager_Pack_Add extends javax.swing.JFrame {

    String id;

    public Manager_Pack_Add() {
        init();
        initComponents();
    }

    public Manager_Pack_Add(String id) {
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
//        txtUnit.setText(unit);
//        boxOccupancy.setValue(price);

        DefaultTableModel model = (DefaultTableModel) tblProd.getModel();
        List<Product> productList = ProductService.getAllProduct();
        String[] prodName = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            prodName[i] = (productList.get(i).getName());
        }
        JComboBox c = new JComboBox(prodName);

        tblProd.getColumn("Delete").setCellRenderer(new ButtonCellRenderer());
        tblProd.getColumn("Delete").setCellEditor(new ButtonCellEditor());
        tblProd.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(c));

        
        //Add jspinner in table cell
        tblProd.getColumnModel().getColumn(1).setCellEditor(new MySpinnerEditor());

        
        this.id = id;
//        this.name = name;
//        this.unit = unit;
//        this.price = price;
//        this.image = image;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        txtPack = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JSpinner();
        lblCapacity2 = new javax.swing.JLabel();
        txtDeadline = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        lblCapacity3 = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

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
        jLabel1.setText("ADD PACK DETAIL");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setText("Limited Quantity:");

        btnClose.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnClose.setText("X");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        txtPack.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtPack.setForeground(new java.awt.Color(255, 255, 255));
        txtPack.setText("Pack Name");
        txtPack.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtPack.setOpaque(false);
        txtPack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPackMouseClicked(evt);
            }
        });
        txtPack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pack Name:");

        txtQuantity.setOpaque(false);

        lblCapacity2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity2.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity2.setText("Date Expired:");

        tblProd.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProd);
        if (tblProd.getColumnModel().getColumnCount() > 0) {
            tblProd.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblProd.getColumnModel().getColumn(2).setMinWidth(80);
            tblProd.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblProd.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        lblCapacity3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblCapacity3.setForeground(new java.awt.Color(255, 255, 255));
        lblCapacity3.setText("Product List:");

        btnSave1.setBackground(new java.awt.Color(0, 255, 51));
        btnSave1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("SAVE");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Add Product");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pack ID:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setText("jTextField1");
        txtID.setBorder(null);
        txtID.setOpaque(false);
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCapacity3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID))
                            .addComponent(txtPack, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(230, 424, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(lblCapacity2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCapacity2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapacity3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        handleClosing();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        //Create pack 
        int price = 0;
        Pack pack = new Pack();
        pack.setId(txtID.getText());
        pack.setName(txtPack.getText());
        pack.setLimitQuantity((int) txtQuantity.getValue());
        Date deadline = txtDeadline.getDate();
         java.sql.Date sqlPackageDate
            = new java.sql.Date(deadline.getTime());
        pack.setDateExp(sqlPackageDate);
//        pack.setPrice(Integer.parseInt(txtPrice.getText()));
        
        //Get prod list 
        List<PackDetail> packList= new ArrayList<PackDetail>();
        
        for (int i = 0; i < tblProd.getRowCount(); i++) {
            PackDetail packDetail = new PackDetail();
            packDetail.setPackId(txtID.getText());
            
            Object prodObj = tblProd.getModel().getValueAt(i, 0);
            String prodName = prodObj.toString();
            if (prodName.equals("Click to choose product")) {
                JOptionPane.showConfirmDialog(null, "Please choose product name", "Warning", JOptionPane.CANCEL_OPTION);
            } else {
                //Find id product
                Product product = ProductService.getProductByName(prodName);
                Product prod = new Product();
                prod.setId(product.getId());
                packDetail.setProduct(prod);
                
                //Add quantity
               Object prodQuantity = tblProd.getModel().getValueAt(i, 1);
               int quantityProd = Integer.parseInt(prodQuantity.toString());
               price += product.getPrice() * quantityProd;
               packDetail.setQuantity(quantityProd);
            }
            packList.add(packDetail);
        }
      
        pack.setPackList(packList);
        pack.setPrice(price);

//        System.out.println(pack.toString());

        if (PackService.addPack(pack)) {
            //Update pack detail
            List<PackDetail> packDetail = pack.getProList();
            for (int i = 0; i < packDetail .size(); i++) {
                PackService.addPackDetail(packDetail.get(i));
            }
            
            JOptionPane.showConfirmDialog(null, "Pack is added succesfull", "Successful", JOptionPane.OK_OPTION);
            dispose();
        }
        else {
            JOptionPane.showConfirmDialog(null, "Please confirm there is no empty field", "Warning", JOptionPane.CANCEL_OPTION);
        }
        
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblProd.getModel();
        JLabel del = new JLabel();
        ImageIcon delIcon = new ImageIcon("U:\\Java\\TP\\management-covid\\src\\com\\kina\\icon\\delete.png");
        del.setIcon(delIcon);
        model.addRow(new Object[]{"Click to choose product", 1, del});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMouseClicked
    }//GEN-LAST:event_txtIDMouseClicked

    private void txtPackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPackMouseClicked
        txtPack.setText("");
    }//GEN-LAST:event_txtPackMouseClicked

    private void txtPackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPackActionPerformed

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
                new Manager_Pack_Add(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCapacity2;
    private javax.swing.JLabel lblCapacity3;
    private javax.swing.JTable tblProd;
    private javax.swing.JLabel txt;
    private com.toedter.calendar.JDateChooser txtDeadline;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPack;
    private javax.swing.JSpinner txtQuantity;
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
    
    public static class MySpinnerEditor extends DefaultCellEditor
    {
        JSpinner sp;
        DefaultEditor defaultEditor;
        JTextField text;
        // Initialize the spinner
        public MySpinnerEditor() {
            super(new JTextField());
            SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);

            sp = new JSpinner(model);
            
            defaultEditor = ((DefaultEditor)sp.getEditor());
            text = defaultEditor.getTextField();
        }
        // Prepare the spinner component and return it
        public Component getTableCellEditorComponent(JTable table, Object 
        value, boolean isSelected, int row, int column) 
        {
            sp.setValue(value);
            return sp;
        }
        // Returns the current value of the spinners
        public Object getCellEditorValue() {
            return sp.getValue();
        }
    }
    
}
