/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandang;

import Controller.ControllerKandang;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author HP
 */

public class InputDataKandang extends JFrame {
    ControllerKandang controller;
    
    JLabel header;
    JLabel labelNomorKandang = new JLabel("Nomor Kandang");
    JLabel labelJumlahBebek = new JLabel("Jumlah Bebek");
    JTextField inputNomorKandang = new JTextField();
    JTextField inputJumlahBebek = new JTextField();
    JButton tombolTambah = new JButton("Tambah Kandang");
    JButton tombolKembali = new JButton("Kembali");

    public InputDataKandang() {
        setTitle("Input Kandang");
        setSize(350, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#f69ae5"));

        controller = new ControllerKandang(this);
        header = new JLabel("Input Kandang");

        // Set bounds for components
        header.setBounds(20, 20, 300, 30);
        labelNomorKandang.setBounds(20, 60, 150, 30);
        inputNomorKandang.setBounds(180, 60, 150, 30);
        labelJumlahBebek.setBounds(20, 100, 150, 30);
        inputJumlahBebek.setBounds(180, 100, 150, 30);
        tombolTambah.setBounds(20, 200, 150, 40);
        tombolKembali.setBounds(180, 200, 150, 40);

        // Add components
        add(header);
        add(labelNomorKandang);
        add(inputNomorKandang);
        add(labelJumlahBebek);
        add(inputJumlahBebek);
        add(tombolTambah);
        add(tombolKembali);

        // Action listeners
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.insertKandang();
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Kandang.ViewKandang();
            }
        });

        setVisible(true);
    }

    public String getInputNomorKandang() {
        return inputNomorKandang.getText();
    }

    public String getInputJumlahBebek() {
        return inputJumlahBebek.getText();
    }
}