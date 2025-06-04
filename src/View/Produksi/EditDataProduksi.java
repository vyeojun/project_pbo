/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Produksi;

import Controller.ControllerProduksi;
import Model.Produksi.ModelProduksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class EditDataProduksi extends JFrame {
    private ControllerProduksi controller;
    private ModelProduksi produksi;

    private JLabel header;
    private JLabel labelKandangId = new JLabel("ID Kandang");
    private JLabel labelJumlahTelur = new JLabel("Jumlah Telur");
    private JLabel labelTanggal = new JLabel("Tanggal (dd MM yyyy)");
    private JTextField inputKandangId = new JTextField();
    private JTextField inputJumlahTelur = new JTextField();
    private JTextField inputTanggal = new JTextField();
    private JButton tombolSimpan = new JButton("Simpan Perubahan");
    private JButton tombolKembali = new JButton("Kembali");

    public EditDataProduksi(ModelProduksi produksi) {
        this.produksi = produksi;
        this.controller = new ControllerProduksi(this);

        setTitle("Edit Produksi Telur");
        setSize(350, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        header = new JLabel("Edit Produksi Telur");

        header.setBounds(20, 20, 300, 30);
        labelKandangId.setBounds(20, 60, 150, 30);
        inputKandangId.setBounds(180, 60, 150, 30);
        labelJumlahTelur.setBounds(20, 100, 150, 30);
        inputJumlahTelur.setBounds(180, 100, 150, 30);
        labelTanggal.setBounds(20, 140, 200, 30);
        inputTanggal.setBounds(180, 140, 150, 30);
        tombolSimpan.setBounds(20, 200, 150, 40);
        tombolKembali.setBounds(180, 200, 150, 40);

        add(header);
        add(labelKandangId);
        add(inputKandangId);
        add(labelJumlahTelur);
        add(inputJumlahTelur);
        add(labelTanggal);
        add(inputTanggal);
        add(tombolSimpan);
        add(tombolKembali);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        inputKandangId.setText(String.valueOf(produksi.getKandangId()));
        inputJumlahTelur.setText(String.valueOf(produksi.getJumlahTelur()));
        inputTanggal.setText(produksi.getTanggal().format(formatter));

        tombolSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.updateProduksi(produksi.getId());
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