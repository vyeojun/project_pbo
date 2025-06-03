/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Produksi;

import Controller.ControllerKandang;
import Model.Kandang.ModelProduksi;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ViewProduksi extends JFrame {
    Integer baris;
    ControllerKandang controller;

    JLabel header = new JLabel("Manajemen Produksi Telur");
    JButton tombolTambahProduksi = new JButton("Tambah Produksi");
    JButton tombolEditProduksi = new JButton("Edit Produksi");
    JButton tombolHapusProduksi = new JButton("Hapus Produksi");
    JButton tombolBack = new JButton("Kembali ke Main Menu");

    JTable tableProduksi;
    DefaultTableModel tableModelProduksi;
    JScrollPane scrollPaneProduksi;

    String namaKolom[] = {"ID", "ID Kandang", "Jumlah Telur", "Tanggal"};

    public ViewProduksi() {
        tableModelProduksi = new DefaultTableModel(namaKolom, 0);
        tableProduksi = new JTable(tableModelProduksi);
        scrollPaneProduksi = new JScrollPane(tableProduksi);

        setTitle("Manajemen Produksi Telur");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(552, 640);

        add(header);
        add(scrollPaneProduksi);
        add(tombolTambahProduksi);
        add(tombolEditProduksi);
        add(tombolHapusProduksi);
        add(tombolBack);

        header.setBounds(20, 8, 440, 24);
        scrollPaneProduksi.setBounds(20, 36, 512, 200);
        tombolTambahProduksi.setBounds(20, 250, 512, 40);
        tombolEditProduksi.setBounds(20, 300, 512, 40);
        tombolHapusProduksi.setBounds(20, 350, 512, 40);
        tombolBack.setBounds(20, 400, 512, 40);

        controller = new ControllerKandang(this);
        controller.showAllProduksi();

        tableProduksi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = tableProduksi.getSelectedRow();
            }
        });
        
        tombolTambahProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Kandang.InputData(true);
            }
        }); 
        

        tombolBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
        
        
        tombolTambahProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Kandang.InputData(true);
            }
        });

        tombolEditProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelProduksi produksiTerpilih = new ModelProduksi();
                    Integer id = (int) tableProduksi.getValueAt(baris, 0);
                    int kandangId = Integer.parseInt(tableProduksi.getValueAt(baris, 1).toString());
                    int jumlahTelur = Integer.parseInt(tableProduksi.getValueAt(baris, 2).toString());
                    String tanggalStr = tableProduksi.getValueAt(baris, 3).toString();
                    produksiTerpilih.setId(id);
                    produksiTerpilih.setKandangId(kandangId);
                    produksiTerpilih.setJumlahTelur(jumlahTelur);
                    // Parse tanggal (assuming format dd MM yyyy from table)
                    String[] tanggalParts = tanggalStr.split(" ");
                    produksiTerpilih.setTanggal(LocalDate.of(Integer.parseInt(tanggalParts[2]), 
                                                            Integer.parseInt(tanggalParts[1]), 
                                                            Integer.parseInt(tanggalParts[0])));
                    dispose();
                    new EditDataProduksi(produksiTerpilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih produksi terlebih dahulu.");
                }
            }
        });

        tombolHapusProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    Integer id = (int) tableProduksi.getValueAt(baris, 0);
                    controller.hapusProduksi(id);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih produksi terlebih dahulu.");
                }
            }
        });
    }

    public JTable getTableProduksi() {
        return tableProduksi;
    }
}