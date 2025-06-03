/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Produksi;

import Controller.ControllerKandang;
import Model.Kandang.ModelProduksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class EditDataProduksi extends JFrame {
    ControllerKandang controller;
    
    JLabel header = new JLabel("Edit Produksi");
    JLabel labelKandangId = new JLabel("ID Kandang");
    JLabel labelJumlahTelur = new JLabel("Jumlah Telur");
    JLabel labelTanggal = new JLabel("Tanggal (dd MM yyyy, e.g., 31 12 2025)");
    JTextField inputKandangId = new JTextField();
    JTextField inputJumlahTelur = new JTextField();
    JTextField inputTanggal = new JTextField();
    JButton tombolEdit = new JButton("Edit Produksi");
    JButton tombolKembali = new JButton("Kembali");

    public EditDataProduksi(ModelProduksi produksi) {
        setTitle("Edit Produksi");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 300);

        add(header);
        add(labelKandangId);
        add(inputKandangId);
        add(labelJumlahTelur);
        add(inputJumlahTelur);
        add(labelTanggal);
        add(inputTanggal);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelKandangId.setBounds(20, 32, 440, 24);
        inputKandangId.setBounds(18, 56, 440, 36);
        labelJumlahTelur.setBounds(20, 96, 440, 24);
        inputJumlahTelur.setBounds(18, 120, 440, 36);
        labelTanggal.setBounds(20, 160, 440, 24);
        inputTanggal.setBounds(18, 184, 440, 36);
        tombolKembali.setBounds(20, 230, 215, 40);
        tombolEdit.setBounds(240, 230, 215, 40);

        inputKandangId.setText(String.valueOf(produksi.getKandangId()));
        inputJumlahTelur.setText(String.valueOf(produksi.getJumlahTelur()));
        inputTanggal.setText(String.format("%02d %02d %d", produksi.getTanggal().getDayOfMonth(),
                                           produksi.getTanggal().getMonthValue(),
                                           produksi.getTanggal().getYear()));

        controller = new ControllerKandang(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewProduksi();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.updateProduksi(produksi.getId());
            }
        });
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