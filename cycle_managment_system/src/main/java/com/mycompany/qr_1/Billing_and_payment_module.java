/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qr_1;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Billing_and_payment_module extends javax.swing.JFrame {

    static int order;
    public HashMap<String, String> persons_ = new HashMap<>();
    public HashMap<String, String> n_of_order = new HashMap<>();
    Qr_module ne = new Qr_module();
    public String string, time;
    public int k = 1;
    Admin_module n = new Admin_module();
    public int updated_deposit;
    public String path_for_cycles = n.path_for_cycles;
    public String path_for_cycles_temp = n.path_for_cycles_temp;
    public String bill = n.bill;
    public String bill_temp = n.bill_temp;
    public String database_for_User_Database = n.database_for_User_Database;
    public String database_for_User_Database_temp = n.database_for_User_Database_temp;
    public String cycle;

    /**
     * Creates new form Billing_and_payment_module
     */
    public Billing_and_payment_module() {
        initComponents();

        ne.setVisible(true);
        ne.submit.addActionListener((ActionEvent e) -> {
            if (ne.authorized) {
                string = ne.Username();
                time = Timepasser();
                System.out.println(string + " : " + time);
                if (!ne.Username().equals("null") && !time.equals("null")) {
                    boolean present = false;
                    File data = new File(bill);
                    File data2 = new File(bill_temp);
                    File data3 = new File(database_for_User_Database);
                    File data4 = new File(database_for_User_Database_temp);
                    BufferedReader reader;
                    reader = null;
                    BufferedReader reader_1 = null;
                    k = 1;
                    try {
                        reader_1 = new BufferedReader(new FileReader(data3));
                        reader = new BufferedReader(new FileReader(data));
                    } catch (IOException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter writer = null;
                    BufferedWriter writer_1 = null;
                    try {
                        writer_1 = new BufferedWriter(new FileWriter(data4));
                        writer = new BufferedWriter(new FileWriter(data2));
                    } catch (IOException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String line, line2;
                    try {
                        while ((line = reader.readLine()) != null) {
                            String[] data1 = line.split(",");
                            if (data1[0].equals(string) && data1.length == 2) {
                                line = line + "," + time;
                                present = true;
                                cycle = JOptionPane.showInputDialog(ne, "Enter the cycle model : ");
                                checker(cycle);
                            }
                            if (data1.length >= 3) {
                                System.out.println("Works2");
                                String[] yea = data1[1].split(":");
                                String[] yea1 = data1[2].split(":");
                                if (yea.length > 1 && data1.length == 3 && yea1.length > 1) {
                                    String[] z1 = yea[0].split("/");
                                    String[] z2 = yea[1].split("/");
                                    String[] z3 = yea1[0].split("/");
                                    String[] z4 = yea1[1].split("/");
                                    // day - month - year
                                    String da_En = z1[0];
                                    //System.out.println(da_En);
                                    String da_Ex = z3[0];
                                    //System.out.println(da_Ex);
                                    String mo_En = z1[1];
                                    //System.out.println(mo_En);
                                    String mo_Ex = z3[1];
                                    //System.out.println(mo_Ex);
                                    String ye_En = z1[2];
                                    //System.out.println(ye_En);
                                    String ye_Ex = z3[2];
                                    //System.out.println(ye_Ex);
                                    // hours - min - sec
                                    String ho_En = z2[0];
                                    //System.out.println(ho_En);
                                    String ho_Ex = z4[0];
                                    //System.out.println(ho_Ex);
                                    String mi_En = z2[1];
                                    //System.out.println(mi_En);
                                    String mi_Ex = z4[1];
                                    //System.out.println(mi_Ex);
                                    String se_En = z2[2];
                                    //System.out.println(se_En);
                                    String se_Ex = z4[2];
                                    //System.out.println(se_Ex);
                                    int da = Integer.parseInt(da_Ex) - Integer.parseInt(da_En);
                                    int mo = Integer.parseInt(mo_Ex) - Integer.parseInt(mo_En);
                                    int ye = Integer.parseInt(ye_Ex) - Integer.parseInt(ye_En);
                                    int ho = Integer.parseInt(ho_Ex) - Integer.parseInt(ho_En);
                                    int mi = Integer.parseInt(mi_Ex) - Integer.parseInt(mi_En);
                                    int se = Integer.parseInt(se_Ex) - Integer.parseInt(se_En);
                                    int result = (int) (10 * (da * 1440 + mo * 4380 + ye * 525600 + ho * 60 + mi * 1 + se * 0.016));
                                    line = line + "," + result;
                                    Exiter(cycle);
                                    //System.out.println(line);
                                    while ((line2 = reader_1.readLine()) != null) {
                                        String[] l_d = line2.split(",");
                                        if (data1[0].equals(l_d[0]) && !line.contains("Paid")) {
                                            updated_deposit = Integer.parseInt(l_d[4]) - result;
                                            if (updated_deposit < 0) {
                                                JOptionPane.showMessageDialog(rootPane, "Please Deposit Money in your account \n fare price is : " + result + "\nEnter the following amount which will be messaged to your registered mobile number to your deposit :" + updated_deposit);
                                                String inliine;
                                                try (BufferedReader reader_2 = new BufferedReader(new FileReader(data3))) {
                                                    while ((inliine = reader_2.readLine()) != null) {
                                                        String[] imp_data = inliine.split(",");
                                                        if (ne.Username().equals(imp_data[0])) {
                                                            robot_automatic imp = new robot_automatic("Please Deposit Money in your account \n fare price is : " + result + "\nEnter the following amount which will be messaged to your registered mobile number to your deposit :" + updated_deposit, imp_data[3]);

                                                            imp.messager();
                                                        }
                                                    }
                                                }
                                            } else {
                                                line = line + "," + "Paid";
                                                JOptionPane.showMessageDialog(ne.jLabel4, "Exit has been confirmed \nMoney has been suceessfully extracted\n Remaining Balance : " + updated_deposit);
                                                l_d[4] = Integer.toString(updated_deposit);
                                            }
                                        }
                                        for (int i = 0; i < l_d.length; i++) {
                                            System.out.println(l_d[i]);
                                        }
                                        writer_1.write(String.join(",", l_d));
                                        writer_1.newLine();
                                    }
                                }
                            }
                            System.out.println("--------------------------------------------------------");
                            System.out.println(line);
                            writer.write(line);
                            writer.newLine();
                        }

                        if (!present) {
                            writer.write(string + "," + time);
                            writer.newLine();
                        }
                        reader_1.close();
                        writer_1.close();
                        reader.close();
                        writer.close();
                        System.out.println(data4.length());
                        if (data4.length() != (long) 0) {
                            data3.delete();
                            data4.renameTo(data3);
                        }
                        data.delete();
                        data2.renameTo(data);
                    } catch (IOException | AWTException | InterruptedException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    string = ne.Username();
                    time = Timepasser();
                }
            }
        });
    }
    public String p1, p2;

    public Billing_and_payment_module(String p1, String p2) {
        initComponents();
        this.p1 = p1;
        this.p2 = p2;
        ne.setVisible(true);
        ne.submit.addActionListener((ActionEvent e) -> {
            if (ne.authorized) {
                string = ne.Username();
                time = Timepasser();
                System.out.println(string + " : " + time);
                if (!ne.Username().equals("null") && !time.equals("null")) {
                    boolean present = false;
                    File data = new File(bill);
                    File data2 = new File(bill_temp);
                    File data3 = new File(database_for_User_Database);
                    File data4 = new File(database_for_User_Database_temp);
                    BufferedReader reader;
                    reader = null;
                    BufferedReader reader_1 = null;
                    k = 1;
                    try {
                        reader_1 = new BufferedReader(new FileReader(data3));
                        reader = new BufferedReader(new FileReader(data));
                    } catch (IOException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter writer = null;
                    BufferedWriter writer_1 = null;
                    try {
                        writer_1 = new BufferedWriter(new FileWriter(data4));
                        writer = new BufferedWriter(new FileWriter(data2));
                    } catch (IOException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String line, line2;
                    try {
                        while ((line = reader.readLine()) != null) {
                            String[] data1 = line.split(",");
                            if (data1[0].equals(string) && data1.length == 2) {
                                line = line + "," + time;
                                present = true;
                            }

                            System.out.println("Works2");
                            String[] yea = data1[1].split(":");
                            String[] yea1 = time.split(":");
                            if (yea.length > 1 && data1.length == 3 && yea1.length > 1) {
                                String[] z1 = yea[0].split("/");
                                String[] z2 = yea[1].split("/");
                                String[] z3 = yea1[0].split("/");
                                String[] z4 = yea1[1].split("/");
                                // day - month - year
                                String da_En = z1[0];
                                //System.out.println(da_En);
                                String da_Ex = z3[0];
                                //System.out.println(da_Ex);
                                String mo_En = z1[1];
                                //System.out.println(mo_En);
                                String mo_Ex = z3[1];
                                //System.out.println(mo_Ex);
                                String ye_En = z1[2];
                                //System.out.println(ye_En);
                                String ye_Ex = z3[2];
                                //System.out.println(ye_Ex);
                                // hours - min - sec
                                String ho_En = z2[0];
                                //System.out.println(ho_En);
                                String ho_Ex = z4[0];
                                //System.out.println(ho_Ex);
                                String mi_En = z2[1];
                                //System.out.println(mi_En);
                                String mi_Ex = z4[1];
                                //System.out.println(mi_Ex);
                                String se_En = z2[2];
                                //System.out.println(se_En);
                                String se_Ex = z4[2];
                                //System.out.println(se_Ex);
                                int da = Integer.parseInt(da_Ex) - Integer.parseInt(da_En);
                                int mo = Integer.parseInt(mo_Ex) - Integer.parseInt(mo_En);
                                int ye = Integer.parseInt(ye_Ex) - Integer.parseInt(ye_En);
                                int ho = Integer.parseInt(ho_Ex) - Integer.parseInt(ho_En);
                                int mi = Integer.parseInt(mi_Ex) - Integer.parseInt(mi_En);
                                int se = Integer.parseInt(se_Ex) - Integer.parseInt(se_En);
                                int result = (int) (10 * (da * 1440 + mo * 4380 + ye * 525600 + ho * 60 + mi * 1 + se * 0.016));
                                line = line + "," + result + ",Paid";
                                Exiter(n_of_order.get(ne.Username()));
                                n_of_order.remove(ne.Username());

                                //System.out.println(line);
                                while ((line2 = reader_1.readLine()) != null) {
                                    String[] l_d = line2.split(",");
                                    if (data1[0].equals(l_d[0])) {
                                        updated_deposit = Integer.parseInt(l_d[4]) - result;
                                        if (updated_deposit < 0) {
                                            JOptionPane.showMessageDialog(rootPane, "Please Deposit Money in your account \n fare price is : " + result + "\nEnter the following amount which will be messaged to your registered mobile number to your deposit :" + updated_deposit);
                                            String inliine;
                                            try (BufferedReader reader_2 = new BufferedReader(new FileReader(data3))) {
                                                while ((inliine = reader_2.readLine()) != null) {
                                                    String[] imp_data = inliine.split(",");
                                                    if (ne.Username().equals(imp_data[0])) {
                                                        robot_automatic imp = new robot_automatic("Please Deposit Money in your account \n fare price is : " + result + "\nEnter the following amount which will be messaged to your registered mobile number to your deposit :" + updated_deposit, imp_data[3]);
                                                        imp.messager();
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(ne.jLabel4, "Exit has been confirmed \nMoney has been suceessfully extracted\n Remaining Balance : " + updated_deposit);
                                            l_d[4] = Integer.toString(updated_deposit);
                                        }
                                    }
                                    for (int i = 0; i < l_d.length; i++) {
                                        System.out.println(l_d[i]);
                                    }
                                    writer_1.write(String.join(",", l_d));
                                    writer_1.newLine();
                                }
                            }

                            System.out.println("--------------------------------------------------------");
                            System.out.println(line);
                            writer.write(line);
                            writer.newLine();
                        }

                        if (!present) {
                            cycle = JOptionPane.showInputDialog(ne, "Enter the cycle model : ");
                            checker(cycle);
                            writer.write(string + "," + time);
                            writer.newLine();
                        }
                        reader_1.close();
                        writer_1.close();
                        reader.close();
                        writer.close();
                        System.out.println(data4.length());
                        if (data4.length() != (long) 0) {
                            data3.delete();
                            data4.renameTo(data3);
                        }
                        data.delete();
                        data2.renameTo(data);
                    } catch (IOException | AWTException | InterruptedException ex) {
                        Logger.getLogger(Billing_and_payment_module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    string = ne.Username();
                    time = Timepasser();
                }
            }
        });
    }

    public String Timepasser() {
        Date n1 = new Date();
        return n1.getDate() + "/" + Math.addExact(n1.getMonth(), 1) + "/" + Math.addExact(1900, n1.getYear()) + ":" + n1.getHours() + "/" + n1.getMinutes() + "/" + n1.getSeconds();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Valuable Feedback");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_START);

        setBounds(0, 0, 205, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Feedback i = new Feedback();
        i.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Billing_and_payment_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing_and_payment_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing_and_payment_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing_and_payment_module.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Billing_and_payment_module().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

    private void checker(String cycle) throws IOException {
        String line;
        boolean present = false;
        File nice_1 = new File(p1);
        File nice_2 = new File(p2);
        var n1 = new BufferedReader(new FileReader(nice_1));
        var n2 = new BufferedWriter(new FileWriter(nice_2));
        while ((line = n1.readLine()) != null) {
            String[] f1 = line.split(",");
            if (cycle.equals(f1[0]) && !cycle.equals("null")) {
                present = true;
                if (Integer.parseInt(f1[1]) != 0) {
                    int upd = Integer.parseInt(f1[1]) - 1;
                    f1[1] = Integer.toString(upd);
                } else {
                    n_of_order.put(ne.Username(), cycle);
                }
                f1[2] = Integer.toString(Integer.parseInt(f1[2]) + 1);
            }
            n2.write(String.join(",", f1));
            n2.newLine();
        }
        n1.close();
        n2.close();
        nice_1.delete();
        nice_2.renameTo(nice_1);
        if (!present) {
            var msg = JOptionPane.showInputDialog(ne, "Please Enter the correct the name \n As it's not registered in our database : ");
            checker(msg);
        }
    }

    private void Exiter(String cycle) throws FileNotFoundException, IOException {
        File nice_1 = new File(p1);
        File nice_2 = new File(p2);
        BufferedReader n1 = new BufferedReader(new FileReader(nice_1));
        BufferedWriter n2 = new BufferedWriter(new FileWriter(nice_2));
        String line;
        while ((line = n1.readLine()) != null) {
            String[] f1 = line.split(",");
            if (cycle != null) {
                if (cycle.equals(f1[0])) {
                    int upd = Integer.parseInt(f1[1]) + 1;
                    f1[1] = Integer.toString(upd);
                }
            }
            n2.write(String.join(",", f1));
            n2.newLine();
        }
        for (String username : n_of_order.values()) {
            if (cycle.equals(username)) {
                JOptionPane.showMessageDialog(ne, "User for cycle " + cycle + " is selected.");
            }
        }
        n1.close();
        n2.close();
        nice_1.delete();
        nice_2.renameTo(nice_1);
    }
}
