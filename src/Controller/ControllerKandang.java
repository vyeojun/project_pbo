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
    private View.Kandang.ViewKandang viewKandang;
    private View.Kandang.InputDataKandang inputDataKandang;
    private View.Kandang.EditData editData;
    private InterfaceDAOKandang daoKandang;
    private List<ModelKandang> daftarKandang;

    public ControllerKandang(View.Kandang.ViewKandang viewKandang) {
        this.viewKandang = viewKandang;
        this.daoKandang = new DAOKandang();
        showAllKandang();
    }

    public ControllerKandang(View.Kandang.InputDataKandang inputDataKandang) {
        this.inputDataKandang = inputDataKandang;
        this.daoKandang = new DAOKandang();
    }

    public ControllerKandang(View.Kandang.EditData editData) {
        this.editData = editData;
        this.daoKandang = new DAOKandang();
    }

    public void showAllKandang() {
        daftarKandang = daoKandang.getAll();
        viewKandang.getTableKandang().setModel(new ModelTableKandang(daftarKandang));
    }

    public void insertKandang() {
        try {
            ModelKandang kandangBaru = new ModelKandang();
            String nomorKandang = inputDataKandang.getInputNomorKandang();
            String jumlahBebekStr = inputDataKandang.getInputJumlahBebek();
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
            inputDataKandang.dispose();
            new View.Kandang.ViewKandang();
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

    public void deleteKandang(int baris) {
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
}