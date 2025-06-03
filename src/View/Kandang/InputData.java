/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandang;
import Controller.ControllerKandang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author HP
 */

public class InputData extends JFrame {
    ControllerKandang controller;
    
    JLabel header;
    JLabel labelNomorKandang = new JLabel("Nomor Kandang");
    JLabel labelJumlahBebek = new JLabel("Jumlah Bebek");
    JLabel labelKandangId = new JLabel("ID Kandang");
    JLabel labelJumlahTelur = new JLabel("Jumlah Telur");
    JLabel labelTanggal = new JLabel("Tanggal (dd MM yyyy, e.g., 31 12 2025)");
    JTextField inputNomorKandang = new JTextField();
    JTextField inputJumlahBebek = new JTextField();
    JTextField inputKandangId = new JTextField();
    JTextField inputJumlahTelur = new JTextField();
    JTextField inputTanggal = new JTextField();
    JButton tombolTambah;
    JButton tombolKembali = new JButton("Kembali");

    public InputData(boolean isProduksi) {
        setTitle(isProduksi ? "Input Produksi Telur" : "Input Kandang");
        setSize(350, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        controller = new ControllerKandang(this);
        header = new JLabel(isProduksi ? "Input Produksi Telur" : "Input Kandang");
        tombolTambah = new JButton(isProduksi ? "Tambah Produksi" : "Tambah Kandang");

        // Set bounds for components
        header.setBounds(20, 20, 300, 30);
        if (!isProduksi) {
            labelNomorKandang.setBounds(20, 60, 150, 30);
            inputNomorKandang.setBounds(180, 60, 150, 30);
            labelJumlahBebek.setBounds(20, 100, 150, 30);
            inputJumlahBebek.setBounds(180, 100, 150, 30);
        } else {
            labelKandangId.setBounds(20, 60, 150, 30);
            inputKandangId.setBounds(180, 60, 150, 30);
            labelJumlahTelur.setBounds(20, 100, 150, 30);
            inputJumlahTelur.setBounds(180, 100, 150, 30);
            labelTanggal.setBounds(20, 140, 200, 30);
            inputTanggal.setBounds(180, 140, 150, 30);
        }
        tombolTambah.setBounds(20, 200, 150, 40);
        tombolKembali.setBounds(180, 200, 150, 40);

        // Add components
        add(header);
        if (!isProduksi) {
            add(labelNomorKandang);
            add(inputNomorKandang);
            add(labelJumlahBebek);
            add(inputJumlahBebek);
        } else {
            add(labelKandangId);
            add(inputKandangId);
            add(labelJumlahTelur);
            add(inputJumlahTelur);
            add(labelTanggal);
            add(inputTanggal);
        }
        add(tombolTambah);
        add(tombolKembali);

        // Action listeners
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isProduksi) {
                    controller.insertProduksi();
                } else {
                    controller.insertKandang();
                }
            }
        });

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewKandang();
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