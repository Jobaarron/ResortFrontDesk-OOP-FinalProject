/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resortfrontdesk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Reservation extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private Rooms rooms;

    /**
     * Creates new form Reservation
     */
    public Reservation() {
        initComponents();
        Connection();
        this.rooms = rooms;
    }
    
     public void Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/resortfrontdesk", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private int calculateTotalCost() {
    int baseCost = getRoomPrice(guestRoomCBox.getSelectedItem().toString());
    int additionalCosts = calculateAdditionalCosts(baseCost);

    int overnightCost = 0;
    if (OvernightCheckBox1.isSelected()) {
        overnightCost = calculateOvernightCost(baseCost);
    }

    int totalCostBeforeDiscounts = baseCost + additionalCosts + overnightCost;

    if (cbSeniorCitizen.isSelected()) {
        double discountPercentage = 0.3;
        int seniorCitizenDiscount = (int) (totalCostBeforeDiscounts * discountPercentage);
        totalCostBeforeDiscounts -= seniorCitizenDiscount;
    }

    if (cbPWD.isSelected()) {
        double discountPercentage = 0.3;
        int pwdDiscount = (int) (totalCostBeforeDiscounts * discountPercentage);
        totalCostBeforeDiscounts -= pwdDiscount;
    }

    return totalCostBeforeDiscounts;
}

private int calculateAdditionalCosts(int baseCost) {
    double discountPercentage = 0.3;
    int seniorCitizenCost = cbSeniorCitizen.isSelected() ? (int) (baseCost * discountPercentage) : 0;
    int pwdCost = cbPWD.isSelected() ? (int) (baseCost * discountPercentage) : 0;
    int dayTourCost = DaytourCheckBox1.isSelected() ? 700 : 0;
    int overnightCost = OvernightCheckBox1.isSelected() ? calculateOvernightCost(baseCost) : 0;

    return seniorCitizenCost + pwdCost + dayTourCost + overnightCost;
}

private int calculateOvernightCost(int baseCost) {
    int nights = 0;
    try {
        nights = Integer.parseInt(nightsTextField1.getText());
    } catch (NumberFormatException e) {
        return 0;
    }

    return nights * baseCost;
}

private int getRoomPrice(String selectedRoom) {
    int roomPrice = 0;

    switch (selectedRoom) {
        case "Room1":
            roomPrice = 2000;
            break;
        case "Room2":
            roomPrice = 2500;
            break;
        case "Room3":
            roomPrice = 3000;
            break;
        case "Cottage1":
            roomPrice = 750;
            break;
        case "Cottage2":
            roomPrice = 800;
            break;
        case "Cottage3":
            roomPrice = 920;
            break;
    }

    return roomPrice; 
              
    }

    private void updatePriceLabel() {
        String selectedRoom = guestRoomCBox.getSelectedItem().toString();
        int baseCost = getRoomPrice(selectedRoom);
        int additionalCosts = calculateAdditionalCosts(baseCost);

        int overnightCost = 0;
        if (OvernightCheckBox1.isSelected()) {
            overnightCost = calculateOvernightCost(baseCost);
            nightsJLabel1.setVisible(true);
            nightsTextField1.setVisible(true);
    } else {
        nightsJLabel1.setVisible(false);
        nightsTextField1.setVisible(false);
    }

        int totalCost = baseCost + additionalCosts + overnightCost;

        if (cbSeniorCitizen.isSelected()) {
            double discountPercentage = 0.3;
            int seniorCitizenDiscount = (int) (totalCost * discountPercentage);
            totalCost -= seniorCitizenDiscount;
    }

        if (cbPWD.isSelected()) {
            double discountPercentage = 0.3;
            int pwdDiscount = (int) (totalCost * discountPercentage);
            totalCost -= pwdDiscount;
    }

    priceJLabel.setText("PRICE: PHP " + totalCost);
}
     
    private boolean isRoomAlreadyOccupied(String room) throws SQLException {
    String sql = "SELECT COUNT(*) FROM (SELECT room FROM guestcheckin UNION SELECT room FROM guestreservation) AS combined WHERE room = ?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, room);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    }
    return false;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        reservationButton = new javax.swing.JButton();
        guestName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        guestPhNum = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        guestEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        guestRoomCBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        guestCheckInDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        cbPWD = new javax.swing.JCheckBox();
        cbSeniorCitizen = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        guestCheckOutDate = new com.toedter.calendar.JDateChooser();
        backButton = new javax.swing.JButton();
        priceJLabel = new javax.swing.JLabel();
        DaytourCheckBox1 = new javax.swing.JCheckBox();
        OvernightCheckBox1 = new javax.swing.JCheckBox();
        roomsButton = new javax.swing.JButton();
        nightsTextField1 = new javax.swing.JTextField();
        nightsJLabel1 = new javax.swing.JLabel();
        checkinTimePicker = new com.github.lgooddatepicker.components.TimePicker();
        checkoutTimePicker = new com.github.lgooddatepicker.components.TimePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(61, 92, 128));

        jLabel2.setText("Resort Front Desk System - Reservation");
        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        reservationButton.setText("SUBMIT");
        reservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel3.setText("Phone Number:");

        jLabel4.setText("Email:");

        jLabel5.setText("Room:");

        guestRoomCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Room1", "Room2", "Room3", "Cottage1", "Cottage2", "Cottage3" }));
        guestRoomCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestRoomCBoxActionPerformed(evt);
            }
        });

        jLabel6.setText("Check-in:");

        jLabel8.setText("Are you a:");

        cbPWD.setText("PWD");
        cbPWD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPWDActionPerformed(evt);
            }
        });

        cbSeniorCitizen.setText("Senior Citizen");
        cbSeniorCitizen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSeniorCitizenActionPerformed(evt);
            }
        });

        jLabel7.setText("Are you a:");

        jLabel9.setText("Check-out:");

        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        priceJLabel.setText("PRICE:");

        DaytourCheckBox1.setText("Day tour:");
        DaytourCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DaytourCheckBox1ActionPerformed(evt);
            }
        });

        OvernightCheckBox1.setText("Overnight");
        OvernightCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OvernightCheckBox1ActionPerformed(evt);
            }
        });

        roomsButton.setText("Rooms");
        roomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomsButtonActionPerformed(evt);
            }
        });

        nightsJLabel1.setText("Room cost (per night):");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reservationButton)
                .addGap(203, 203, 203)
                .addComponent(backButton)
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(guestName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPWD))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSeniorCitizen))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(DaytourCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OvernightCheckBox1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(guestRoomCBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(guestEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(priceJLabel))
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(guestCheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkinTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(59, 59, 59))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(guestCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkoutTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(guestPhNum, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(roomsButton)))
                        .addContainerGap(294, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nightsJLabel1)
                            .addComponent(nightsTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(guestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guestPhNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guestEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guestRoomCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomsButton)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guestCheckInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkinTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guestCheckOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkoutTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton)
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nightsJLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nightsTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(reservationButton)
                                .addContainerGap(22, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbSeniorCitizen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPWD)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DaytourCheckBox1)
                            .addComponent(OvernightCheckBox1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void reservationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationButtonActionPerformed
    java.util.Date selectedCheckInDate = guestCheckInDate.getDate();
    java.util.Date currentDate = new java.util.Date();

    if (selectedCheckInDate != null && selectedCheckInDate.compareTo(currentDate) <= 0) {
        JOptionPane.showMessageDialog(this, "Please select a check-in date after the current date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    java.util.Date selectedCheckOutDate = guestCheckOutDate.getDate();

    if (selectedCheckOutDate != null && selectedCheckOutDate.compareTo(currentDate) <= 0) {
        JOptionPane.showMessageDialog(this, "Please select a check-out date after the current date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    int totalCost = calculateTotalCost();

    try {
        String selectedRoom = guestRoomCBox.getSelectedItem().toString();

        if (isRoomAlreadyOccupied(selectedRoom)) {
            JOptionPane.showMessageDialog(this, selectedRoom + " is already occupied.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String reservationSql = "INSERT INTO guestreservation (name,phnum,email,room,checkin,checkout,checkinTime,checkoutTime,isDaytour,isOvernight,isSeniorCitizen,isPWD,totalCost) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement reservationPst = con.prepareStatement(reservationSql)) {
            reservationPst.setString(1, guestName.getText());
            reservationPst.setString(2, guestPhNum.getText());
            reservationPst.setString(3, guestEmail.getText());
            reservationPst.setString(4, guestRoomCBox.getSelectedItem().toString());
            java.sql.Date checkinDate = new java.sql.Date(selectedCheckInDate.getTime());
            java.sql.Date checkoutDate = new java.sql.Date(selectedCheckOutDate.getTime());
            reservationPst.setDate(5, checkinDate);
            reservationPst.setDate(6, checkoutDate);
            java.sql.Time checkinTime = java.sql.Time.valueOf(checkinTimePicker.getTime());
            java.sql.Time checkoutTime = java.sql.Time.valueOf(checkoutTimePicker.getTime());
            reservationPst.setTime(7, checkinTime);
            reservationPst.setTime(8, checkoutTime);
            reservationPst.setBoolean(9, DaytourCheckBox1.isSelected());
            reservationPst.setBoolean(10, OvernightCheckBox1.isSelected());
            reservationPst.setBoolean(11, cbSeniorCitizen.isSelected());
            reservationPst.setBoolean(12, cbPWD.isSelected());
            reservationPst.setInt(13, totalCost);
            reservationPst.executeUpdate();
        }

        
        String reservationLogsSql = "INSERT INTO guestreservationlogs (name,phnum,email,room,checkin,checkout,checkinTime,checkoutTime,isDaytour,isOvernight,isSeniorCitizen,isPWD,totalCost) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement reservationLogsPst = con.prepareStatement(reservationLogsSql)) {
            reservationLogsPst.setString(1, guestName.getText());
            reservationLogsPst.setString(2, guestPhNum.getText());
            reservationLogsPst.setString(3, guestEmail.getText());
            reservationLogsPst.setString(4, guestRoomCBox.getSelectedItem().toString());
            java.sql.Date checkinDate = new java.sql.Date(selectedCheckInDate.getTime());
            java.sql.Date checkoutDate = new java.sql.Date(selectedCheckOutDate.getTime());
            reservationLogsPst.setDate(5, checkinDate);
            reservationLogsPst.setDate(6, checkoutDate);
            java.sql.Time checkinTime = java.sql.Time.valueOf(checkinTimePicker.getTime());
            java.sql.Time checkoutTime = java.sql.Time.valueOf(checkoutTimePicker.getTime());
            reservationLogsPst.setTime(7, checkinTime);
            reservationLogsPst.setTime(8, checkoutTime);
            reservationLogsPst.setBoolean(9, DaytourCheckBox1.isSelected());
            reservationLogsPst.setBoolean(10, OvernightCheckBox1.isSelected());
            reservationLogsPst.setBoolean(11, cbSeniorCitizen.isSelected());
            reservationLogsPst.setBoolean(12, cbPWD.isSelected());
            reservationLogsPst.setInt(13, totalCost);
            reservationLogsPst.executeUpdate();
        }
        
        JOptionPane.showMessageDialog(this, "Reservation recorded successfully. Total Cost: PHP" + totalCost);
    } catch (SQLException ex) {
        Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error recording reservation. Please try again.");
    }
    dispose();
    ResortFrontDesk home1 = new ResortFrontDesk();
    home1.setVisible(true);        
    }//GEN-LAST:event_reservationButtonActionPerformed

    private void guestRoomCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestRoomCBoxActionPerformed
        String selectedRoom = guestRoomCBox.getSelectedItem().toString();
        int roomPrice = getRoomPrice(selectedRoom);
        priceJLabel.setText("PRICE: PHP " + roomPrice);
        updatePriceLabel();
    }//GEN-LAST:event_guestRoomCBoxActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
        ResortFrontDesk home2 = new ResortFrontDesk();
        home2.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void DaytourCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DaytourCheckBox1ActionPerformed
        if(DaytourCheckBox1.isSelected()){
            updatePriceLabel();
            nightsJLabel1.setVisible(false);
            nightsTextField1.setVisible(false);
            OvernightCheckBox1.setVisible(false);
        } else {
            updatePriceLabel();
            OvernightCheckBox1.setVisible(true);
        }

    }//GEN-LAST:event_DaytourCheckBox1ActionPerformed

    private void OvernightCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OvernightCheckBox1ActionPerformed
        if (OvernightCheckBox1.isSelected()) {
            updatePriceLabel();
            DaytourCheckBox1.setVisible(false);
            nightsJLabel1.setVisible(true);
            nightsTextField1.setVisible(true);
        } else {
            updatePriceLabel();
            DaytourCheckBox1.setVisible(true);
            nightsJLabel1.setVisible(false);
            nightsTextField1.setVisible(false);
        }

    }//GEN-LAST:event_OvernightCheckBox1ActionPerformed

    private void roomsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomsButtonActionPerformed
        Rooms rooms = new Rooms();
        rooms.setVisible(true);
    
    }//GEN-LAST:event_roomsButtonActionPerformed

    private void cbSeniorCitizenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSeniorCitizenActionPerformed
        updatePriceLabel();
    }//GEN-LAST:event_cbSeniorCitizenActionPerformed

    private void cbPWDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPWDActionPerformed
        updatePriceLabel();
    }//GEN-LAST:event_cbPWDActionPerformed

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
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox DaytourCheckBox1;
    private javax.swing.JCheckBox OvernightCheckBox1;
    private javax.swing.JButton backButton;
    private javax.swing.JCheckBox cbPWD;
    private javax.swing.JCheckBox cbSeniorCitizen;
    private com.github.lgooddatepicker.components.TimePicker checkinTimePicker;
    private com.github.lgooddatepicker.components.TimePicker checkoutTimePicker;
    private com.toedter.calendar.JDateChooser guestCheckInDate;
    private com.toedter.calendar.JDateChooser guestCheckOutDate;
    private javax.swing.JTextField guestEmail;
    private javax.swing.JTextField guestName;
    private javax.swing.JTextField guestPhNum;
    private javax.swing.JComboBox<String> guestRoomCBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nightsJLabel1;
    private javax.swing.JTextField nightsTextField1;
    private javax.swing.JLabel priceJLabel;
    private javax.swing.JButton reservationButton;
    private javax.swing.JButton roomsButton;
    // End of variables declaration//GEN-END:variables
}