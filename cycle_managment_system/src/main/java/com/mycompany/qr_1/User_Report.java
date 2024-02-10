/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qr_1;

import java.awt.event.ActionEvent;
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


class User_Report {

    public static String path;
    public static String p1;

    public User_Report() {
    }

    public User_Report(String p1) {
        this.p1 = p1;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Admin_module n1 = new Admin_module();
        Qr_module no = new Qr_module();
        no.setVisible(true);
        no.submit.addActionListener((ActionEvent e) -> {
            try {
                if (no.password_matcher()) {

                    var bill = n1.bill;
                    File file = new File(bill);
                    FileReader n2 = new FileReader(file);
                    BufferedReader n3 = new BufferedReader(n2);
                    String line;
                    String Username = no.Username();
                    String phone = no.phone();
                    User_Report n = new User_Report(phone);
                    System.out.println(Username);
                    path = "C:/Users/eshwa/OneDrive/Desktop/User_reports/" + Username + ".csv";
                    File check = new File(path);
                    BufferedWriter check_1;
                    check_1 = new BufferedWriter(new FileWriter(check));
                    int k = 0;

                    while ((line = n3.readLine()) != null) {
                        String[] data = line.split(",");

                        if (k == 0) {
                            check_1.write(String.join(",", data));
                            check_1.newLine();
                        }
                        k = 1;
                        if (data[0].equals(Username) && k == 1) {
                            check_1.write(String.join(",", data));
                            check_1.newLine();
                        }
                    }
                    n3.close();
                    check_1.close();
                    no.setVisible(false);
                    file_sender(path, no);
                }
                

            } catch (IOException ex) {
                Logger.getLogger(User_Report.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private static void file_sender(String path, Qr_module no) throws IOException {
        file_modifier(path);
        sender(no);
        no.setVisible(true);
    }

    private static void file_modifier(String path) throws FileNotFoundException, IOException {
        String path1 = "C:/Users/eshwa/OneDrive/Desktop/Sem-2/java/Project/file_sender.py";
        String path3 = "C:/Users/eshwa/OneDrive/Desktop/Sem-2/java/Project/file_sender_temp.py";
        String path2 = path;
        File n1 = new File(path1);
        BufferedReader py_file = new BufferedReader(new FileReader(n1));
        File n2 = new File(path3);
        BufferedWriter py_file_temp = new BufferedWriter(new FileWriter(n2));
        String lin;
        while ((lin = py_file.readLine()) != null) {
            if (lin.contains("file_path = ")) {
                lin = "file_path = " + "\"" + path + "\"";
            }
            if (lin.contains("phone_number = ")) {
                lin = "phone_number = " + "\"" + p1 + "\"";
            }
            py_file_temp.write(lin);
            py_file_temp.newLine();
        }
        py_file_temp.close();
        py_file.close();
        n1.delete();
        n2.renameTo(n1);
    }

    private static void sender(Qr_module no) {
        String pythonScriptPath = "C:\\Users\\eshwa\\OneDrive\\Desktop\\Sem-2\\java\\Project\\file_sender.py";
        String number = p1;
        String msg = "Here is your past hitsory";
        String[] command = {"python", pythonScriptPath, number, msg};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File("C:\\Users\\eshwa\\OneDrive\\Desktop\\Sem-2\\java\\Project")); // Set the working directory if needed

        try {
            Process process = processBuilder.start();
            JOptionPane.showMessageDialog(no, "Please wait as it's being processed");
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");
            } else {
                System.out.println("Error executing Python script. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
        }
    }
}
