/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qr_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Remove_Add_cycle extends javax.swing.JFrame {

    /**
     * Creates new form Remove_cycle
     */
    public Remove_Add_cycle() {
        initComponents();
    }
    public String i1,i2;
    public Remove_Add_cycle(String p1,String p2) {
        i1 = p1;
        i2=p2;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Cycle model :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 42, 80, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 39, 150, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 73, 150, -1));

        jLabel1.setText("Number of :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 76, 80, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 107, 80, -1));

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 113, -1, -1));

        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 113, -1, -1));

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 113, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String path_1 = i1;
        String path_2 = i2;
        BufferedReader file = null;
        File n = new File(path_1);
        File n1 = new File(path_2);
        try {
            file = new BufferedReader(new FileReader(n));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter file_temp = null;
        try {
            file_temp = new BufferedWriter(new FileWriter(n1));
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }

        String line;
        boolean present = false;
        try {
            while ((line = file.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(jTextField1.getText())) {
                    present = true;
                    int num = Integer.parseInt(data[1]);
                    int decrement = Integer.parseInt(jTextField2.getText());
                    num =num- decrement;

                    if (num < 0) {
                        JOptionPane.showMessageDialog(rootPane, "Excess amount of cycle is being removed");
                        num = 0;
                    }

                    data[1] = String.valueOf(num);
                }

                file_temp.write(String.join(",", data));
                file_temp.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (present) {
            JOptionPane.showMessageDialog(rootPane, "Successfully removed from the database");

            try {
                file.close();
                file_temp.close();
            } catch (IOException ex) {
                Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
            }

            n.delete(); // Delete the original file
            n1.renameTo(n); // Rename the temporary file to the original file name
        } else {
            JOptionPane.showMessageDialog(rootPane, "There exists no such model name");
            n1.delete(); // Delete the temporary file
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String path_1 = i1;
        String path_2 = i2;
        BufferedReader file = null;
        File n = new File(path_1);
        File n1 = new File(path_2);
        try {
            file = new BufferedReader(new FileReader(n));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter file_temp = null;
        try {
            file_temp = new BufferedWriter(new FileWriter(n1));
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }

        String line;
        boolean present = false;
        try {
            while ((line = file.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(jTextField1.getText())) {
                    present = true;
                    int num = Integer.parseInt(data[1]);
                    int decrement = Integer.parseInt(jTextField2.getText());
                    num =num+ decrement;

                    if (num > 40) {
                        JOptionPane.showMessageDialog(rootPane, "Excess amount of cycle is being Added");
                    }

                    data[1] = String.valueOf(num);
                }

                file_temp.write(String.join(",", data));
                file_temp.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (present) {
            JOptionPane.showMessageDialog(rootPane, "Successfully removed from the database");

            try {
                file.close();
                file_temp.close();
            } catch (IOException ex) {
                Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
            }

            n.delete(); // Delete the original file
            n1.renameTo(n); // Rename the temporary file to the original file name
        } else {
            JOptionPane.showMessageDialog(rootPane, "There exists no such model name");
            n1.delete(); // Delete the temporary file
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Remove_Add_cycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Remove_Add_cycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Remove_Add_cycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Remove_Add_cycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Remove_Add_cycle().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}