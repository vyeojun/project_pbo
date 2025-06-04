/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandang;

import Controller.ControllerKandang;
import Model.Kandang.ModelKandang;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class ViewKandang extends JFrame {
    Integer baris;
    ControllerKandang controller;

    JLabel header = new JLabel("Manajemen Kandang Bebek");
    JButton tombolTambahKandang = new JButton("Tambah Kandang");
    JButton tombolEdit = new JButton("Edit Kandang");
    JButton tombolHapus = new JButton("Hapus Kandang");
    JButton tombolBack = new JButton("Kembali ke Main Menu");

    JTable tableKandang;
    DefaultTableModel tableModelKandang;
    JScrollPane scrollPaneKandang;

    String namaKolom[] = {"ID", "Nomor Kandang", "Jumlah Bebek"};

    public ViewKandang() {
        tableModelKandang = new DefaultTableModel(namaKolom, 0);
        tableKandang = new JTable(tableModelKandang);
        scrollPaneKandang = new JScrollPane(tableKandang);

        setTitle("Manajemen Kandang Bebek");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(552, 640);

        add(header);
        add(scrollPaneKandang);
        add(tombolTambahKandang);
        add(tombolEdit);
        add(tombolHapus);
        add(tombolBack);

        header.setBounds(20, 8, 440, 24);
        scrollPaneKandang.setBounds(20, 36, 512, 200);
        tombolTambahKandang.setBounds(20, 250, 512, 40);
        tombolEdit.setBounds(20, 300, 512, 40);
        tombolHapus.setBounds(20, 350, 512, 40);
        tombolBack.setBounds(20, 400, 512, 40);

        controller = new ControllerKandang(this);
        controller.showAllKandang();

        tableKandang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = tableKandang.getSelectedRow();
            }
        });

        tombolTambahKandang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputDataKandang();
            }
        });
        
        tombolBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelKandang kandangTerpilih = new ModelKandang();
                    Integer id = (int) tableKandang.getValueAt(baris, 0);
                    String nomorKandang = tableKandang.getValueAt(baris, 1).toString();
                    int jumlahBebek = Integer.parseInt(tableKandang.getValueAt(baris, 2).toString());
                    kandangTerpilih.setId(id);
                    kandangTerpilih.setNomorKandang(nomorKandang);
                    kandangTerpilih.setJumlahBebek(jumlahBebek);
                    dispose();
                    new EditData(kandangTerpilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih kandang terlebih dahulu.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteKandang(baris);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih kandang terlebih dahulu.");
                }
            }
        });
    }

    public JTable getTableKandang() {
        return tableKandang;
    }
}