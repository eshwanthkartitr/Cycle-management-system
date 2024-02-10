/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qr_1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class robot_automatic {

    String msg;
    String number;

    public robot_automatic(String msg, String number) {
        this.msg = msg;
        this.number = number;
    }
    
    public void py_file_modifier() throws FileNotFoundException, IOException{
        String path = "C:\\Users\\eshwa\\sms.py";
        String path1 = "C:\\Users\\eshwa\\sms_temp.py";
        File fiel = new File(path);
        File fiel1 = new File(path1);
        BufferedReader n = new BufferedReader(new FileReader(fiel));
        BufferedWriter n1 = new BufferedWriter(new FileWriter(fiel1));
        String line;
        while((line = n.readLine()) != null){
            if (line.contains("phone_number = ")){
                if(number.startsWith("+")){
                    line = "phone_number = \"" + number + '\"'; 
                }else{
                    line = "phone_number = \"+" + number + '\"'; 
                }
                
            }
            if(line.contains("message = ")){
                line = "message = \"" + msg + "\"";
            }
            n1.write(line);
            n1.newLine();
        }
        n.close();
        n1.close();
        fiel.delete();
        fiel1.renameTo(fiel);
    }
    public void messager() throws FileNotFoundException, AWTException, InterruptedException, IOException {
        py_file_modifier();
        runPythonScript();
    }
    public void runPythonScript() throws IOException {
        String pythonScriptPath = "C:\\Users\\eshwa\\sms.py";
        String[] command = {"python", pythonScriptPath, number, msg};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File("C:\\Users\\eshwa")); // Set the working directory if needed

        try {
            Process process = processBuilder.start();
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
