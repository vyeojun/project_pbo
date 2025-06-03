/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandang;

import Controller.ControllerKandang;
import Model.Kandang.ModelKandang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author HP
 */
public class EditData extends JFrame {
    ControllerKandang controller;
    
    JLabel header = new JLabel("Edit Kandang");
    JLabel labelNomorKandang = new JLabel("Nomor Kandang");
    JLabel labelJumlahBebek = new JLabel("Jumlah Bebek");
    JTextField inputNomorKandang = new JTextField();
    JTextField inputJumlahBebek = new JTextField();
    JButton tombolEdit = new JButton("Edit Kandang");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(ModelKandang kandang) {
        setTitle("Edit Kandang");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 240);

        add(header);
        add(labelNomorKandang);
        add(inputNomorKandang);
        add(labelJumlahBebek);
        add(inputJumlahBebek);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelNomorKandang.setBounds(20, 32, 440, 24);
        inputNomorKandang.setBounds(18, 56, 440, 36);
        labelJumlahBebek.setBounds(20, 96, 440, 24);
        inputJumlahBebek.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);

        inputNomorKandang.setText(kandang.getNomorKandang());
        inputJumlahBebek.setText(String.valueOf(kandang.getJumlahBebek()));

        controller = new ControllerKandang(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewKandang();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editKandang(kandang.getId());
            }
        });
    }

    public String getInputNomorKandang() {
        return inputNomorKandang.getText();
    }

    public String getInputJumlahBebek() {
        return inputJumlahBebek.getText();
    }
}