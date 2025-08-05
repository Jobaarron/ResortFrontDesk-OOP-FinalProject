/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.resortfrontdesk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author admin
 */
public class CancelReservation extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private Rooms rooms;
    

 
    public CancelReservation() {
        initComponents();
        Connection();
        LoadReservationNo();
        Fetch();
        this.rooms = rooms;
        
        
    jTable1.getModel().addTableModelListener(new TableModelListener() {
       @Override
      public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String columnName = model.getColumnName(column);
            Object updatedValue = model.getValueAt(row, column);

            updateDatabase(row, columnName, updatedValue);
    }
    });
  }
    
     public void Connection(){
          try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/resortfrontdesk","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
    private void updateDatabase(int row, String columnName, Object updatedValue) {
        try {
            int ReservationID = Integer.parseInt(jTable1.getValueAt(row, 0).toString());

        String updateSql = "UPDATE guestreservation SET " + "name = ?, phnum = ?, email = ?, room = ?, checkin = ?, checkout = ?, " + "checkinTime = ?, checkoutTime = ?, isDaytour = ?, isOvernight = ?, " + "isSeniorCitizen = ?, isPWD = ?, totalCost = ? WHERE ReservationID = ?";
        try (PreparedStatement updatePst = con.prepareStatement(updateSql)) {
            updatePst.setObject(1, jTable1.getValueAt(row, 1));
            updatePst.setObject(2, jTable1.getValueAt(row, 2));
            updatePst.setObject(3, jTable1.getValueAt(row, 3));
            updatePst.setObject(4, jTable1.getValueAt(row, 4));
            updatePst.setObject(5, jTable1.getValueAt(row, 5));
            updatePst.setObject(6, jTable1.getValueAt(row, 6));
            updatePst.setObject(7, jTable1.getValueAt(row, 7));
            updatePst.setObject(8, jTable1.getValueAt(row, 8));

            
            updatePst.setObject(9, Boolean.parseBoolean(jTable1.getValueAt(row, 9).toString()));
            updatePst.setObject(10, Boolean.parseBoolean(jTable1.getValueAt(row, 10).toString()));
            updatePst.setObject(11, Boolean.parseBoolean(jTable1.getValueAt(row, 11).toString()));
            updatePst.setObject(12, Boolean.parseBoolean(jTable1.getValueAt(row, 12).toString()));

            updatePst.setObject(13, jTable1.getValueAt(row, 13));
            updatePst.setInt(14, ReservationID);
            updatePst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Updated successfully!");
    } catch (SQLException ex) {
        Logger.getLogger(CheckInLogs.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error updating, Please try again.");
    }
}

  
       public void LoadReservationNo(){
        try {
            pst = con.prepareStatement("SELECT ReservationID FROM guestreservation");
            rs = pst.executeQuery();
            reservationIDBox.removeAllItems();
            while(rs.next()){
                reservationIDBox.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     
     
     
     
private void Fetch(){
    try {
        int q;
        pst = con.prepareStatement("SELECT * FROM `guestreservation`");
        rs = pst.executeQuery();
        ResultSetMetaData rss = rs.getMetaData();
        q = rss.getColumnCount();

        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        df.setRowCount(0);
        while(rs.next()){
            Vector v2 = new Vector();
            for(int a=1; a<=q; a++)
            {
                v2.add(rs.getString("ReservationID"));
                v2.add(rs.getString("name"));
                v2.add(rs.getString("phnum"));
                v2.add(rs.getString("email"));
                v2.add(rs.getString("room"));
                v2.add(rs.getDate("checkin"));
                v2.add(rs.getDate("checkout"));
                v2.add(rs.getTime("checkinTime"));  
                v2.add(rs.getTime("checkoutTime")); 
                v2.add(rs.getBoolean("isDaytour"));
                v2.add(rs.getBoolean("isOvernight"));
                v2.add(rs.getBoolean("isSeniorCitizen"));
                v2.add(rs.getBoolean("isPWD"));
                v2.add(rs.getInt("totalCost"));
            }
            df.addRow(v2);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cancelReservationButton = new javax.swing.JButton();
        reservationIDBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel9.setBackground(new java.awt.Color(0, 107, 187));

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Resort Front Desk System - Reservations");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(181, 181, 181))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(30, 30, 30))
        );

        cancelReservationButton.setText("CANCEL");
        cancelReservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelReservationButtonActionPerformed(evt);
            }
        });

        reservationIDBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReservationID", "Name", "Phone Number", "Email", "Room", "Check-In", "Check-Out", "Check-In Time", "Check-Out Time", "isDaytour", "isOvernight", "isSeniorCitizen", "isPWD", "Total Cost"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(reservationIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cancelReservationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(reservationIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelReservationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelReservationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelReservationButtonActionPerformed
        try {
            String pid = reservationIDBox.getSelectedItem().toString();
            pst = con.prepareStatement("DELETE FROM guestreservation WHERE ReservationID=?");
            pst.setString(1, pid);
            int k = pst.executeUpdate();
            if (k==1){
                JOptionPane.showMessageDialog(this, "Reservation canceled successfully!");
                Fetch();
                LoadReservationNo();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to cancel reservation, please try again later!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelReservationButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        FrontDeskManagement home2 = new FrontDeskManagement();
        dispose();
        home2.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CancelReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CancelReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CancelReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CancelReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CancelReservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelReservationButton;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> reservationIDBox;
    // End of variables declaration//GEN-END:variables
}
