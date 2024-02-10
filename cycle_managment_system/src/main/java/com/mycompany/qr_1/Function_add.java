/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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


class Function_add {

    String link;

    public Function_add(String link) {
        this.link = link;
    }

    public void add_user() {
        File data = new File("C:\\Users\\eshwa\\OneDrive\\Desktop\\Sem-2\\java\\Project\\Database.csv");
        String path_2 = "C:\\Users\\eshwa\\OneDrive\\Desktop\\Sem-2\\java\\Project\\Database_temp.csv";
        BufferedReader file = null;
        File n1 = new File(path_2);
        try {
            file = new BufferedReader(new FileReader(data));
        } catch (FileNotFoundException ex) {

        }
        BufferedWriter file_temp = null;
        try {
            file_temp = new BufferedWriter(new FileWriter(n1));
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        String[] data1;
        try {
            while ((line = file.readLine()) != null) {
                data1 = line.split(",");
                file_temp.write(String.join(",", data1));
                file_temp.newLine();
            }
            file_temp.write(link);
            file_temp.newLine();
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.close();
            file_temp.close();
        } catch (IOException ex) {
            Logger.getLogger(Remove_Add_cycle.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.delete(); // Delete the original file
        n1.renameTo(data); // Rename the temporary file to the original file name
    }
    
    public static void main(String[] args) {
        Function_add i = new Function_add("HI,ewfueew,fwfwf,ewf,ew,few,f,ewf,ewf");
        i.add_user();
    }
}
