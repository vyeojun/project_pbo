/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Produksi;

import Controller.ControllerProduksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author zatri
 */
public class InputDataProduksi extends JFrame {
    private ControllerProduksi controller;
    
    private JLabel header;
    private JLabel labelKandangId = new JLabel("ID Kandang");
    private JLabel labelJumlahTelur = new JLabel("Jumlah Telur");
    private JLabel labelTanggal = new JLabel("Tanggal (dd MM yyyy)");
    private JTextField inputKandangId = new JTextField();
    private JTextField inputJumlahTelur = new JTextField();
    private JTextField inputTanggal = new JTextField();
    private JButton tombolTambah = new JButton("Tambah Produksi");
    private JButton tombolKembali = new JButton("Kembali");

    public InputDataProduksi() {
        setTitle("Input Produksi Telur");
        setSize(350, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        controller = new ControllerProduksi(this);
        header = new JLabel("Input Produksi Telur");

        // Set bounds for components
        header.setBounds(20, 20, 300, 30);
        labelKandangId.setBounds(20, 60, 150, 30);
        inputKandangId.setBounds(180, 60, 150, 30);
        labelJumlahTelur.setBounds(20, 100, 150, 30);
        inputJumlahTelur.setBounds(180, 100, 150, 30);
        labelTanggal.setBounds(20, 140, 200, 30);
        inputTanggal.setBounds(180, 140, 150, 30);
        tombolTambah.setBounds(20, 200, 150, 40);
        tombolKembali.setBounds(180, 200, 150, 40);

        // Add components
        add(header);
        add(labelKandangId);
        add(inputKandangId);
        add(labelJumlahTelur);
        add(inputJumlahTelur);
        add(labelTanggal);
        add(inputTanggal);
        add(tombolTambah);
        add(tombolKembali);

        // Action listeners
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.insertProduksi();
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewProduksi();
            }
        });

        setVisible(true);
    }

    public String getInputKandangId() {
        return inputKandangId.getText();
    }

    public String getInputJumlahTelur() {
        return inputJumlahTelur.getText();
    }

    public String getInputTanggal() {
        return inputTanggal.getText();
    }
}