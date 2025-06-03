/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Kandang;

import Controller.ControllerKandang;
import Model.Kandang.ModelKandang;

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
    JButton tombolTambahProduksi = new JButton("Tambah Produksi Telur");
    JButton tombolEdit = new JButton("Edit Kandang");
    JButton tombolHapus = new JButton("Hapus Kandang");

    JTable tableKandang, tableProduksi;
    DefaultTableModel tableModelKandang, tableModelProduksi;
    JScrollPane scrollPaneKandang, scrollPaneProduksi;

    String namaKolom[] = {"ID", "Nomor Kandang", "Jumlah Bebek", "Jumlah Telur", "Tanggal"};

    public ViewKandang() {
        tableModelKandang = new DefaultTableModel(namaKolom, 0);
        tableKandang = new JTable(tableModelKandang);
        scrollPaneKandang = new JScrollPane(tableKandang);

        tableModelProduksi = new DefaultTableModel(namaKolom, 0);
        tableProduksi = new JTable(tableModelProduksi);
        scrollPaneProduksi = new JScrollPane(tableProduksi);

        setTitle("Manajemen Kandang Bebek");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(552, 640);

        add(header);
        add(scrollPaneKandang);
        add(scrollPaneProduksi);
        add(tombolTambahKandang);
        add(tombolTambahProduksi);
        add(tombolEdit);
        add(tombolHapus);

        header.setBounds(20, 8, 440, 24);
        scrollPaneKandang.setBounds(20, 36, 512, 200);
        scrollPaneProduksi.setBounds(20, 260, 512, 200);
        tombolTambahKandang.setBounds(20, 470, 256, 40);
        tombolTambahProduksi.setBounds(276, 470, 256, 40);
        tombolEdit.setBounds(20, 514, 512, 40);
        tombolHapus.setBounds(20, 558, 512, 40);

        controller = new ControllerKandang(this);
        controller.showAllKandang();
        controller.showAllProduksi();

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
                System.out.println("yea");
                dispose();
                new InputData(false);
            }
        });

        tombolTambahProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputData(true);
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

    public JTable getTableProduksi() {
        return tableProduksi;
    }
}