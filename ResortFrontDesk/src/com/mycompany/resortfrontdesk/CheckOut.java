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
public class CheckOut extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private Rooms rooms;

    /**
     * Creates new form CheckOut
     */
    public CheckOut() {
        initComponents();
        Connection();
        LoadGuestCheckIn();  
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
            Logger.getLogger(ResortFrontDesk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ResortFrontDesk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public void LoadGuestCheckIn(){
        try {
            pst = con.prepareStatement("SELECT GuestID FROM guestcheckin");
            rs = pst.executeQuery();
            guestIDBox.removeAllItems();
            while(rs.next()){
                guestIDBox.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     private void Fetch(){
       try {
        int q;
        pst = con.prepareStatement("SELECT * FROM `guestcheckin`");
        rs = pst.executeQuery();
        ResultSetMetaData rss = rs.getMetaData();
        q = rss.getColumnCount();
        
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        df.setRowCount(0);
        while(rs.next()){
            Vector v2 = new Vector();
            for(int a=1; a<=q; a++)
            {
                v2.add(rs.getString("GuestID"));
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
        Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private void updateDatabase(int row, String columnName, Object updatedValue) {
        try {
            int guestID = Integer.parseInt(jTable1.getValueAt(row, 0).toString());

        String updateSql = "UPDATE guestcheckin SET " + "name = ?, phnum = ?, email = ?, room = ?, checkin = ?, checkout = ?, " + "checkinTime = ?, checkoutTime = ?, isDaytour = ?, isOvernight = ?, " + "isSeniorCitizen = ?, isPWD = ?, totalCost = ? WHERE GuestID = ?";
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
            updatePst.setInt(14, guestID);
            updatePst.executeUpdate();
        }

            

            JOptionPane.showMessageDialog(this, "Updated successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(CheckInLogs.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error updating, Please try again.");
        }
    }
     
 

    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        checkOutButton = new javax.swing.JButton();
        guestIDBox = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(48, 160, 224));

        jPanel6.setBackground(new java.awt.Color(0, 107, 187));

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Resort Front Desk System - Check Out");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(185, 185, 185))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(29, 29, 29))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "GuestID", "Name", "Phone Number", "Email", "Room", "Check-in", "Check-Out", "Check-In Time", "Check-out Time", "isDaytour", "isOvernight", "isSeniorCitizen", "isPWD", "Total Cost"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        checkOutButton.setText("Check-Out");
        checkOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutButtonActionPerformed(evt);
            }
        });

        guestIDBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(guestIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(checkOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(guestIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkOutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutButtonActionPerformed
        try {
            String pid = guestIDBox.getSelectedItem().toString();
            pst = con.prepareStatement("DELETE FROM guestcheckin WHERE GuestID=?");
            pst.setString(1, pid);
            int k = pst.executeUpdate();
            if (k==1){
                JOptionPane.showMessageDialog(this, "Check-out successfully.!");
                Fetch();
                LoadGuestCheckIn(); 
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Check-out, please try again later.!");
            }    
        } catch (SQLException ex) {
            Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_checkOutButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton checkOutButton;
    private javax.swing.JComboBox<String> guestIDBox;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
