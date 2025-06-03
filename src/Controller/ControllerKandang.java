/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Kandang.*;
import View.Kandang.*;

import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */

public class ControllerKandang {
    ViewKandang halamanTable;
    InputData halamanInput;
    EditData halamanEdit;
    
    InterfaceDAOKandang daoKandang;
    List<ModelKandang> daftarKandang;
    List<ModelProduksi> daftarProduksi;

    public ControllerKandang(ViewKandang halamanTable) {
        this.halamanTable = halamanTable;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoKandang = new DAOKandang();
    }

    public void showAllKandang() {
        try {
            daftarKandang = daoKandang.getAll();
            ModelTable table = new ModelTable(daftarKandang);
            halamanTable.getTableKandang().setModel(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading kandang data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showAllProduksi() {
        try {
            daftarProduksi = daoKandang.getAllProduksi();
            ModelTable table = new ModelTable(daftarProduksi, true);
            halamanTable.getTableProduksi().setModel(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading produksi data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertKandang() {
        try {
            ModelKandang kandangBaru = new ModelKandang();
            String nomorKandang = halamanInput.getInputNomorKandang();
            String jumlahBebekStr = halamanInput.getInputJumlahBebek();
            if ("".equals(nomorKandang) || "".equals(jumlahBebekStr)) {
                throw new Exception("Nomor Kandang atau Jumlah Bebek tidak boleh kosong!");
            }
            if (!jumlahBebekStr.matches("\\d+")) {
                throw new Exception("Jumlah Bebek harus berupa angka!");
            }
            int jumlahBebek = Integer.parseInt(jumlahBebekStr);
            if (jumlahBebek <= 0) {
                throw new Exception("Jumlah Bebek harus lebih dari 0!");
            }
            kandangBaru.setNomorKandang(nomorKandang);
            kandangBaru.setJumlahBebek(jumlahBebek);
            daoKandang.insert(kandangBaru);
            JOptionPane.showMessageDialog(null, "Kandang baru berhasil ditambahkan.");
            halamanInput.dispose();
            new ViewKandang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertProduksi() {
        try {
            ModelProduksi produksiBaru = new ModelProduksi();
            String kandangIdStr = halamanInput.getInputKandangId();
            String jumlahTelurStr = halamanInput.getInputJumlahTelur();
            String tanggal = halamanInput.getInputTanggal();
            if ("".equals(kandangIdStr) || "".equals(jumlahTelurStr) || "".equals(tanggal)) {
                throw new Exception("Kandang ID, Jumlah Telur, atau Tanggal tidak boleh kosong!");
            }
            if (!kandangIdStr.matches("\\d+")) {
                throw new Exception("ID Kandang harus berupa angka!");
            }
            if (!jumlahTelurStr.matches("\\d+")) {
                throw new Exception("Jumlah Telur harus berupa angka!");
            }
            if (!tanggal.matches("\\d{2}\\s\\d{2}\\s\\d{4}")) {
                throw new Exception("Format tanggal harus dd MM yyyy (contoh: 31 12 2025)!");
            }
            int kandangId = Integer.parseInt(kandangIdStr);
            int jumlahTelur = Integer.parseInt(jumlahTelurStr);
            if (jumlahTelur < 0) {
                throw new Exception("Jumlah Telur tidak boleh negatif!");
            }
            produksiBaru.setKandangId(kandangId);
            produksiBaru.setJumlahTelur(jumlahTelur);
            produksiBaru.setTanggal(tanggal);
            daoKandang.insertProduksi(produksiBaru);
            JOptionPane.showMessageDialog(null, "Produksi telur berhasil ditambahkan.");
            halamanInput.dispose();
            new ViewKandang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editKandang(int id) {
        try {
            ModelKandang kandangEdit = new ModelKandang();
            String nomorKandang = halamanEdit.getInputNomorKandang();
            String jumlahBebekStr = halamanEdit.getInputJumlahBebek();
            if ("".equals(nomorKandang) || "".equals(jumlahBebekStr)) {
                throw new Exception("Nomor Kandang atau Jumlah Bebek tidak boleh kosong!");
            }
            if (!jumlahBebekStr.matches("\\d+")) {
                throw new Exception("Jumlah Bebek harus berupa angka!");
            }
            int jumlahBebek = Integer.parseInt(jumlahBebekStr);
            if (jumlahBebek <= 0) {
                throw new Exception("Jumlah Bebek harus lebih dari 0!");
            }
            kandangEdit.setId(id);
            kandangEdit.setNomorKandang(nomorKandang);
            kandangEdit.setJumlahBebek(jumlahBebek);
            daoKandang.update(kandangEdit);
            JOptionPane.showMessageDialog(null, "Data kandang berhasil diubah.");
            halamanEdit.dispose();
            new ViewKandang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteKandang(Integer baris) {
        try {
            Integer id = (int) halamanTable.getTableKandang().getValueAt(baris, 0);
            String nomorKandang = halamanTable.getTableKandang().getValueAt(baris, 1).toString();
            int input = JOptionPane.showConfirmDialog(
                    null,
                    "Hapus kandang " + nomorKandang + "?",
                    "Hapus Kandang",
                    JOptionPane.YES_NO_OPTION
            );
            if (input == 0) {
                daoKandang.delete(id);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus kandang. ID telah diurutkan ulang.");
                showAllKandang();
                showAllProduksi();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting kandang: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
