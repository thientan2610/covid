package com.thientan.form.manager;

import com.thientan.model.Receipt;
import com.thientan.model.ReceiptDetail;
import com.thientan.model.User;
import com.thientan.service.ReceiptService;
import com.thientan.service.TreatmentRecordService;
import com.thientan.service.UserService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Manager_Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Manager_Dashboard
     */
    public Manager_Dashboard() {
        initComponents();
        setOpaque(false);
        initStatus();
        initBalance();
        initRecover();
        initPack();
    }

    private void initStatus() {
        //Status
        List<User> user = UserService.getAllUser();
        int f0 = 0, f1 = 0, f2 = 0;
        for (int i = 0; i < user.size(); i++) {
            switch (user.get(i).getStatus()) {
                case 0:
                    f0 += 1;
                    break;
                case 1:
                    f1 += 1;
                    break;
                case 2:
                    f2 += 1;
                    break;
            }
        }

        int numberF0 = 100;
        int percent = (int) ((f1 * 100.0f) / user.size());
        numberF0 -= percent;
        prgF1.setValue(percent);
        numF1.setText(Integer.toString(f1));
        prgF1.start();

        percent = (int) ((f2 * 100.0f) / user.size());
        numberF0 -= percent;
        prgF2.setValue(percent);
        numF2.setText(Integer.toString(f2));
        prgF2.start();

        prgF0.setValue(numberF0);
        numF0.setText(Integer.toString(f0));
        prgF0.start();

        //Pie chart
    }

    private void initBalance() {
        //Get money 
        JFreeChart chart = createChart(createDataset());
        chart.getTitle().setFont(new java.awt.Font( "Century Gothic", java.awt.Font.BOLD, 14));
        ChartPanel chartpanel = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pieChart.getWidth(), pieChart.getHeight());
            }
        };
        chartpanel.setFont(new java.awt.Font( "Century Gothic", java.awt.Font.PLAIN, 10 ));
        
        pieChart.setLayout(new BorderLayout());
        pieChart.add(chartpanel, BorderLayout.CENTER);
    }

    private static PieDataset createDataset() {
        int paid = ReceiptService.countTotalPayment();
        int debit = ReceiptService.countTotalDebit();
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Total amount debit", new Double(debit));
        dataset.setValue("Total amount paid", new Double(paid));

        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Statistique of debt balance",
                dataset,
                true,
                true,
                false);

        return chart;
    }

    private void initRecover() {
        JFreeChart chartPanel = createChartPanel();
        ChartPanel chartpanel = new ChartPanel(chartPanel) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pieChart.getWidth(), pieChart.getHeight());
            }
        };
        pieChart1.setLayout(new BorderLayout());
        pieChart1.add(chartpanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "Number of transitions";
        String series2 = "Number of recover";
 
        
        dataset.addValue(5.0, series1, "Mar");
        dataset.addValue(4.8, series1, "April");
        dataset.addValue(4.5, series1, "May");

        dataset.addValue(4.0, series2, "Mar");
        dataset.addValue(4.2, series2, "April");
        dataset.addValue(3.8, series2, "May");

        return dataset;
    }

    private JFreeChart createChartPanel() {
        String chartTitle = "Statistics on the status of covid-19 disease";
        String categoryAxisLabel = "Time";
        String valueAxisLabel = "Popularity";

        CategoryDataset dataset = createDataset1();

        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset);

        return (chart);
    }

    private void initPack() {
        JFreeChart chartPanel = createChartPanel1();
        ChartPanel chartpanel = new ChartPanel(chartPanel) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pieChart.getWidth(), pieChart.getHeight());
            }
        };
        pieChart2.setLayout(new BorderLayout());
        pieChart2.add(chartpanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "Pack";
        dataset.addValue(10.0, series1, "01/05");

        dataset.addValue(9.0, series1, "02/05");

        dataset.addValue(2.0, series1, "03/05");

        dataset.addValue(4.0, series1, "04/05");

        return dataset;
    }

    private JFreeChart createChartPanel1() {
        String chartTitle = "Number of purchases per day";
        String categoryAxisLabel = "By Time";
        String valueAxisLabel = "Pack Quantity";

        CategoryDataset dataset = createDataset2();

        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset);

        return (chart);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelState = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelF0 = new javax.swing.JPanel();
        prgF0 = new com.thienan.swing.progress.Progress();
        numF0 = new javax.swing.JLabel();
        lblF0 = new javax.swing.JLabel();
        panelF4 = new javax.swing.JPanel();
        prgF1 = new com.thienan.swing.progress.Progress();
        numF1 = new javax.swing.JLabel();
        lblF7 = new javax.swing.JLabel();
        panelF5 = new javax.swing.JPanel();
        prgF2 = new com.thienan.swing.progress.Progress();
        numF2 = new javax.swing.JLabel();
        lblF9 = new javax.swing.JLabel();
        PanelPeople = new javax.swing.JPanel();
        pieChart = new javax.swing.JPanel();
        panelRecover = new javax.swing.JPanel();
        pieChart1 = new javax.swing.JPanel();
        pieChart2 = new javax.swing.JPanel();

        PanelState.setBackground(new java.awt.Color(255, 255, 255));
        PanelState.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Number of people in each state");

        panelF0.setOpaque(false);

        prgF0.setBackground(new java.awt.Color(0, 255, 51));
        prgF0.setForeground(new java.awt.Color(0, 255, 51));
        prgF0.setValue(50);

        numF0.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        numF0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numF0.setText("STATE F0");

        lblF0.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblF0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblF0.setText("STATE F0");

        javax.swing.GroupLayout panelF0Layout = new javax.swing.GroupLayout(panelF0);
        panelF0.setLayout(panelF0Layout);
        panelF0Layout.setHorizontalGroup(
            panelF0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblF0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelF0Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prgF0, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(numF0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelF0Layout.setVerticalGroup(
            panelF0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelF0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblF0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prgF0, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numF0)
                .addGap(45, 45, 45))
        );

        panelF4.setOpaque(false);

        prgF1.setBackground(new java.awt.Color(0, 255, 51));
        prgF1.setForeground(new java.awt.Color(0, 255, 51));
        prgF1.setValue(50);

        numF1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        numF1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numF1.setText("STATE F0");

        lblF7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblF7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblF7.setText("STATE F1");

        javax.swing.GroupLayout panelF4Layout = new javax.swing.GroupLayout(panelF4);
        panelF4.setLayout(panelF4Layout);
        panelF4Layout.setHorizontalGroup(
            panelF4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblF7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelF4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prgF1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(numF1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelF4Layout.setVerticalGroup(
            panelF4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelF4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblF7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prgF1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numF1)
                .addGap(45, 45, 45))
        );

        panelF5.setOpaque(false);

        prgF2.setBackground(new java.awt.Color(0, 255, 51));
        prgF2.setForeground(new java.awt.Color(0, 255, 51));
        prgF2.setValue(50);

        numF2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        numF2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numF2.setText("STATE F0");

        lblF9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblF9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblF9.setText("STATE F2");

        javax.swing.GroupLayout panelF5Layout = new javax.swing.GroupLayout(panelF5);
        panelF5.setLayout(panelF5Layout);
        panelF5Layout.setHorizontalGroup(
            panelF5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblF9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelF5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prgF2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(numF2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelF5Layout.setVerticalGroup(
            panelF5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelF5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblF9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prgF2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numF2)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout PanelStateLayout = new javax.swing.GroupLayout(PanelState);
        PanelState.setLayout(PanelStateLayout);
        PanelStateLayout.setHorizontalGroup(
            PanelStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelF0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelStateLayout.setVerticalGroup(
            PanelStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelF0, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelF4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelF5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        PanelPeople.setBackground(new java.awt.Color(255, 255, 255));
        PanelPeople.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        javax.swing.GroupLayout pieChartLayout = new javax.swing.GroupLayout(pieChart);
        pieChart.setLayout(pieChartLayout);
        pieChartLayout.setHorizontalGroup(
            pieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        pieChartLayout.setVerticalGroup(
            pieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPeopleLayout = new javax.swing.GroupLayout(PanelPeople);
        PanelPeople.setLayout(PanelPeopleLayout);
        PanelPeopleLayout.setHorizontalGroup(
            PanelPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPeopleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelPeopleLayout.setVerticalGroup(
            PanelPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPeopleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRecover.setBackground(new java.awt.Color(255, 255, 255));
        panelRecover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        javax.swing.GroupLayout pieChart1Layout = new javax.swing.GroupLayout(pieChart1);
        pieChart1.setLayout(pieChart1Layout);
        pieChart1Layout.setHorizontalGroup(
            pieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        pieChart1Layout.setVerticalGroup(
            pieChart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pieChart2Layout = new javax.swing.GroupLayout(pieChart2);
        pieChart2.setLayout(pieChart2Layout);
        pieChart2Layout.setHorizontalGroup(
            pieChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pieChart2Layout.setVerticalGroup(
            pieChart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRecoverLayout = new javax.swing.GroupLayout(panelRecover);
        panelRecover.setLayout(panelRecoverLayout);
        panelRecoverLayout.setHorizontalGroup(
            panelRecoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecoverLayout.createSequentialGroup()
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pieChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRecoverLayout.setVerticalGroup(
            panelRecoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecoverLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelRecoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPeople, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelRecover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelPeople, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRecover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPeople;
    private javax.swing.JPanel PanelState;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblF0;
    private javax.swing.JLabel lblF7;
    private javax.swing.JLabel lblF9;
    private javax.swing.JLabel numF0;
    private javax.swing.JLabel numF1;
    private javax.swing.JLabel numF2;
    private javax.swing.JPanel panelF0;
    private javax.swing.JPanel panelF4;
    private javax.swing.JPanel panelF5;
    private javax.swing.JPanel panelRecover;
    private javax.swing.JPanel pieChart;
    private javax.swing.JPanel pieChart1;
    private javax.swing.JPanel pieChart2;
    private com.thienan.swing.progress.Progress prgF0;
    private com.thienan.swing.progress.Progress prgF1;
    private com.thienan.swing.progress.Progress prgF2;
    // End of variables declaration//GEN-END:variables
}
