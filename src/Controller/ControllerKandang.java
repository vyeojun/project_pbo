/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Kandang.*;
import View.Kandang.*;
import View.Produksi.*;

import java.util.List;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author HP
 */

public class ControllerKandang {
    View.Kandang.ViewKandang viewKandang;
    View.Produksi.ViewProduksi viewProduksi;
    View.Kandang.InputData inputData;
    View.Kandang.EditData editData;
    View.Produksi.EditDataProduksi editDataProduksi;
    
    InterfaceDAOKandang daoKandang;
    List<ModelKandang> daftarKandang;
    List<ModelProduksi> daftarProduksi;

    public ControllerKandang(View.Kandang.ViewKandang viewKandang) {
        this.viewKandang = viewKandang;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(View.Produksi.ViewProduksi viewProduksi) {
        this.viewProduksi = viewProduksi;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(View.Kandang.InputData inputData) {
        this.inputData = inputData;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(View.Kandang.EditData editData) {
        this.editData = editData;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(View.Produksi.EditDataProduksi editDataProduksi) {
        this.editDataProduksi = editDataProduksi;
        this.daoKandang = new DAOKandang();
    }

    public void showAllKandang() {
        daftarKandang = daoKandang.getAll();
        String[][] dataKandang = new String[daftarKandang.size()][3];
        int i = 0;
        for (ModelKandang kandang : daftarKandang) {
            dataKandang[i][0] = String.valueOf(kandang.getId());
            dataKandang[i][1] = kandang.getNomorKandang();
            dataKandang[i][2] = String.valueOf(kandang.getJumlahBebek());
            i++;
        }
        viewKandang.getTableKandang().setModel(new DefaultTableModel(dataKandang, new String[]{"ID", "Nomor Kandang", "Jumlah Bebek"}));
    }

    public void showAllProduksi() {
        daftarProduksi = daoKandang.getAllProduksi();
        String[][] dataProduksi = new String[daftarProduksi.size()][4];
        int i = 0;
        for (ModelProduksi produksi : daftarProduksi) {
            dataProduksi[i][0] = String.valueOf(produksi.getId());
            dataProduksi[i][1] = String.valueOf(produksi.getKandangId());
            dataProduksi[i][2] = String.valueOf(produksi.getJumlahTelur());
            dataProduksi[i][3] = produksi.getTanggal().format(DateTimeFormatter.ofPattern("dd MM yyyy"));
            i++;
        }
        if (viewProduksi != null) {
            viewProduksi.getTableProduksi().setModel(new DefaultTableModel(dataProduksi, new String[]{"ID", "ID Kandang", "Jumlah Telur", "Tanggal"}));
        }
    }

    public void insertKandang() {
        try {
            ModelKandang kandangBaru = new ModelKandang();
            String nomorKandang = inputData.getInputNomorKandang();
            String jumlahBebekStr = inputData.getInputJumlahBebek();
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
            inputData.dispose();
            new View.Kandang.ViewKandang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertProduksi() {
        try {
            ModelProduksi produksiBaru = new ModelProduksi();
            String kandangIdStr = inputData.getInputKandangId();
            String jumlahTelurStr = inputData.getInputJumlahTelur();
            String tanggalStr = inputData.getInputTanggal();

            if ("".equals(kandangIdStr) || "".equals(jumlahTelurStr) || "".equals(tanggalStr)) {
                throw new Exception("Kandang ID, Jumlah Telur, atau Tanggal tidak boleh kosong!");
            }
            if (!kandangIdStr.matches("\\d+")) {
                throw new Exception("ID Kandang harus berupa angka!");
            }
            if (!jumlahTelurStr.matches("\\d+")) {
                throw new Exception("Jumlah Telur harus berupa angka!");
            }
            if (!tanggalStr.matches("\\d{2}\\s\\d{2}\\s\\d{4}")) {
                throw new Exception("Format tanggal harus dd MM yyyy (contoh: 31 12 2025)!");
            }

            int kandangId = Integer.parseInt(kandangIdStr);
            int jumlahTelur = Integer.parseInt(jumlahTelurStr);
            if (jumlahTelur < 0) {
                throw new Exception("Jumlah Telur tidak boleh negatif!");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDate tanggal;
            try {
                tanggal = LocalDate.parse(tanggalStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Exception("Tanggal tidak valid! Gunakan format dd MM yyyy.");
            }

            LocalDate today = LocalDate.now();
            if (tanggal.isAfter(today)) {
                throw new Exception("Tanggal tidak boleh di masa depan!");
            }

            produksiBaru.setKandangId(kandangId);
            produksiBaru.setJumlahTelur(jumlahTelur);
            produksiBaru.setTanggal(tanggal);

            daoKandang.insertProduksi(produksiBaru);
            JOptionPane.showMessageDialog(null, "Produksi telur berhasil ditambahkan.");
            inputData.dispose();
            new View.Produksi.ViewProduksi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editKandang(int id) {
        try {
            ModelKandang kandangEdit = new ModelKandang();
            String nomorKandang = editData.getInputNomorKandang();
            String jumlahBebekStr = editData.getInputJumlahBebek();
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
            editData.dispose();
            new View.Kandang.ViewKandang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteKandang(Integer baris) {
        try {
            Integer id = (int) viewKandang.getTableKandang().getValueAt(baris, 0);
            String nomorKandang = viewKandang.getTableKandang().getValueAt(baris, 1).toString();
            int input = JOptionPane.showConfirmDialog(null, "Hapus kandang " + nomorKandang + "?",
                    "Hapus Kandang", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                daoKandang.delete(id);
                JOptionPane.showMessageDialog(null, "Berhasil menghapus kandang. ID telah diurutkan ulang.");
                showAllKandang();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting kandang: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateProduksi(int id) {
        try {
            ModelProduksi produksiEdit = new ModelProduksi();
            String kandangIdStr = editDataProduksi.getInputKandangId();
            String jumlahTelurStr = editDataProduksi.getInputJumlahTelur();
            String tanggalStr = editDataProduksi.getInputTanggal();

            if ("".equals(kandangIdStr) || "".equals(jumlahTelurStr) || "".equals(tanggalStr)) {
                throw new Exception("Kandang ID, Jumlah Telur, atau Tanggal tidak boleh kosong!");
            }
            if (!kandangIdStr.matches("\\d+")) {
                throw new Exception("ID Kandang harus berupa angka!");
            }
            if (!jumlahTelurStr.matches("\\d+")) {
                throw new Exception("Jumlah Telur harus berupa angka!");
            }
            if (!tanggalStr.matches("\\d{2}\\s\\d{2}\\s\\d{4}")) {
                throw new Exception("Format tanggal harus dd MM yyyy (contoh: 31 12 2025)!");
            }

            int kandangId = Integer.parseInt(kandangIdStr);
            int jumlahTelur = Integer.parseInt(jumlahTelurStr);
            if (jumlahTelur < 0) {
                throw new Exception("Jumlah Telur tidak boleh negatif!");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDate tanggal;
            try {
                tanggal = LocalDate.parse(tanggalStr, formatter);
            } catch (DateTimeParseException e) {
                throw new Exception("Tanggal tidak valid! Gunakan format dd MM yyyy.");
            }

            LocalDate today = LocalDate.now();
            if (tanggal.isAfter(today)) {
                throw new Exception("Tanggal tidak boleh di masa depan!");
            }

            produksiEdit.setId(id);
            produksiEdit.setKandangId(kandangId);
            produksiEdit.setJumlahTelur(jumlahTelur);
            produksiEdit.setTanggal(tanggal);

            daoKandang.editProduksi(produksiEdit);
            JOptionPane.showMessageDialog(null, "Produksi telur berhasil diubah.");
            editDataProduksi.dispose();
            new View.Produksi.ViewProduksi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void hapusProduksi(int id) {
        try {
            String confirm = JOptionPane.showInputDialog(null, "Masukkan ID produksi untuk konfirmasi: ");
            if (confirm != null && confirm.equals(String.valueOf(id))) {
                daoKandang.hapusProduksi(id);
                JOptionPane.showMessageDialog(null, "Produksi telur berhasil dihapus.");
                showAllProduksi();
            } else {
                JOptionPane.showMessageDialog(null, "Konfirmasi ID salah atau dibatalkan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}