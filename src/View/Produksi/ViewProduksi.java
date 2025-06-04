/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Produksi;

import Controller.ControllerProduksi;
import Model.Produksi.ModelProduksi;
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
    private Integer baris;
    private ControllerProduksi controller;

    private JLabel header = new JLabel("Manajemen Produksi Telur");
    private JButton tombolTambahProduksi = new JButton("Tambah Produksi");
    private JButton tombolEditProduksi = new JButton("Edit Produksi");
    private JButton tombolHapusProduksi = new JButton("Hapus Produksi");
    private JButton tombolBack = new JButton("Kembali ke Main Menu");

    private JTable tableProduksi;
    private DefaultTableModel tableModelProduksi;
    private JScrollPane scrollPaneProduksi;

    private String[] namaKolom = {"ID", "ID Kandang", "Jumlah Telur", "Tanggal"};

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

        controller = new ControllerProduksi(this);
        controller.showAllProduksi();

        tableProduksi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                baris = tableProduksi.getSelectedRow();
                if (baris != -1) {
                    System.out.println("Baris dipilih: " + baris);
                } else {
                    System.out.println("Tidak ada baris yang dipilih.");
                }
            }
        });

        tombolTambahProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputDataProduksi();
            }
        });

        tombolEditProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null && baris >= 0 && baris < tableProduksi.getRowCount()) {
                    try {
                        ModelProduksi produksiTerpilih = new ModelProduksi();
                        Integer id = (int) tableProduksi.getValueAt(baris, 0);
                        String kandangIdStr = tableProduksi.getValueAt(baris, 1).toString();
                        String jumlahTelurStr = tableProduksi.getValueAt(baris, 2).toString();
                        String tanggalStr = tableProduksi.getValueAt(baris, 3).toString();

                        System.out.println("Tanggal dari tabel: " + tanggalStr); // Debug

                        // Validasi dan parsing
                        if (!kandangIdStr.matches("\\d+") || !jumlahTelurStr.matches("\\d+")) {
                            throw new NumberFormatException("ID Kandang atau Jumlah Telur harus berupa angka!");
                        }

                        int kandangId = Integer.parseInt(kandangIdStr);
                        int jumlahTelur = Integer.parseInt(jumlahTelurStr);

                        // Parsing tanggal dengan format dd MM yyyy
                        String[] tanggalParts = tanggalStr.split("-");
                        if (tanggalParts.length != 3) {
                            throw new IllegalArgumentException("Format tanggal tidak valid! Harus yyyy-MM-dd. Ditemukan: " + tanggalStr);
                        }
                            LocalDate tanggal = LocalDate.of(
                            Integer.parseInt(tanggalParts[0]), // Tahun
                                Integer.parseInt(tanggalParts[1]), // Bulan
                           Integer.parseInt(tanggalParts[2])  // Hari
                        );

                        produksiTerpilih.setId(id);
                        produksiTerpilih.setKandangId(kandangId);
                        produksiTerpilih.setJumlahTelur(jumlahTelur);
                        produksiTerpilih.setTanggal(tanggal);

                        dispose();
                        new EditDataProduksi(produksiTerpilih);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih produksi terlebih dahulu.");
                }
            }
        });

        tombolHapusProduksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null && baris >= 0 && baris < tableProduksi.getRowCount()) {
                    Integer id = (int) tableProduksi.getValueAt(baris, 0);
                    controller.hapusProduksi(id);
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih produksi terlebih dahulu.");
                }
            }
        });

        tombolBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }
        });
    }

    public JTable getTableProduksi() {
        return tableProduksi;
    }
}