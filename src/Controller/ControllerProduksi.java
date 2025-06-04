/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Produksi.*;
import Model.Produksi.ModelProduksi;

import java.util.List;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zatri
 */
public class ControllerProduksi {
    private View.Produksi.ViewProduksi viewProduksi;
    private View.Produksi.InputDataProduksi inputDataProduksi;
    private View.Produksi.EditDataProduksi editDataProduksi;
    private InterfaceDAOProduksi daoProduksi;
    private List<ModelProduksi> daftarProduksi;

    public ControllerProduksi(View.Produksi.ViewProduksi viewProduksi) {
        this.viewProduksi = viewProduksi;
        this.daoProduksi = new DAOProduksi();
        showAllProduksi();
    }

    public ControllerProduksi(View.Produksi.InputDataProduksi inputDataProduksi) {
        this.inputDataProduksi = inputDataProduksi;
        this.daoProduksi = new DAOProduksi();
    }

    public ControllerProduksi(View.Produksi.EditDataProduksi editDataProduksi) {
        this.editDataProduksi = editDataProduksi;
        this.daoProduksi = new DAOProduksi();
    }

    public void showAllProduksi() {
    daftarProduksi = daoProduksi.getAllProduksi();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    String[][] dataProduksi = new String[daftarProduksi.size()][4];
    int i = 0;
    for (ModelProduksi produksi : daftarProduksi) {
        dataProduksi[i][0] = String.valueOf(produksi.getId());
        dataProduksi[i][1] = String.valueOf(produksi.getKandangId());
        dataProduksi[i][2] = String.valueOf(produksi.getJumlahTelur());
        dataProduksi[i][3] = (produksi.getTanggal() != null) ? produksi.getTanggal().format(formatter) : "";
        i++;
    }
    viewProduksi.getTableProduksi().setModel(new DefaultTableModel(dataProduksi, new String[]{"ID", "ID Kandang", "Jumlah Telur", "Tanggal"}));
}

    public void insertProduksi() {
        try {
            ModelProduksi produksiBaru = new ModelProduksi();
            String kandangIdStr = inputDataProduksi.getInputKandangId();
            String jumlahTelurStr = inputDataProduksi.getInputJumlahTelur();
            String tanggalStr = inputDataProduksi.getInputTanggal();

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
                throw new Exception("Format tanggal harus dd MM yyyy (contoh: 04 06 2025)!");
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

            LocalDate today = LocalDate.now(); // 04 Juni 2025
            if (tanggal.isAfter(today)) {
                throw new Exception("Tanggal tidak boleh di masa depan!");
            }

            produksiBaru.setKandangId(kandangId);
            produksiBaru.setJumlahTelur(jumlahTelur);
            produksiBaru.setTanggal(tanggal);

            daoProduksi.insertProduksi(produksiBaru);
            JOptionPane.showMessageDialog(null, "Produksi telur berhasil ditambahkan.");
            inputDataProduksi.dispose();
            new View.Produksi.ViewProduksi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
                throw new Exception("Format tanggal harus dd MM yyyy (contoh: 04 06 2025)!");
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

            LocalDate today = LocalDate.now(); // 04 Juni 2025
            if (tanggal.isAfter(today)) {
                throw new Exception("Tanggal tidak boleh di masa depan!");
            }

            produksiEdit.setId(id);
            produksiEdit.setKandangId(kandangId);
            produksiEdit.setJumlahTelur(jumlahTelur);
            produksiEdit.setTanggal(tanggal);

            daoProduksi.editProduksi(produksiEdit);
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
                daoProduksi.hapusProduksi(id);
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