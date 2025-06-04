/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Produksi;

import Model.Produksi.ModelProduksi;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author zatri
 */
public class ModelTableProduksi extends AbstractTableModel {
    private List<ModelProduksi> daftarProduksi;
    private String[] kolom = {"ID", "Nomor Kandang", "Jumlah Telur", "Tanggal"};

    public ModelTableProduksi(List<ModelProduksi> daftarProduksi) {
        this.daftarProduksi = daftarProduksi;
    }

    @Override
    public int getRowCount() {
        return daftarProduksi != null ? daftarProduksi.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelProduksi produksi = daftarProduksi.get(rowIndex);
        switch (columnIndex) {
            case 0: return produksi.getId();
            case 1: return produksi.getKandangId();
            case 2: return produksi.getJumlahTelur();
            case 3: return produksi.getTanggal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}