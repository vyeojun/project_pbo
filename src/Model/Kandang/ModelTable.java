 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandang;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author HP
 */
public class ModelTable extends AbstractTableModel {
    List<ModelKandang> daftarKandang;
    List<ModelProduksi> daftarProduksi;
    private String kolom[] = {"ID", "Nomor Kandang", "Jumlah Bebek", "Jumlah Telur", "Tanggal"};
    private boolean isProduksiTable;

    public ModelTable(List<ModelKandang> daftarKandang) {
        this.daftarKandang = daftarKandang;
        this.isProduksiTable = false;
    }

    public ModelTable(List<ModelProduksi> daftarProduksi, boolean isProduksi) {
        this.daftarProduksi = daftarProduksi;
        this.isProduksiTable = true;
    }

    @Override
    public int getRowCount() {
        return isProduksiTable ? daftarProduksi.size() : daftarKandang.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (isProduksiTable) {
            ModelProduksi produksi = daftarProduksi.get(rowIndex);
            switch (columnIndex) {
                case 0: return produksi.getId();
                case 1: return produksi.getKandangId();
                case 2: return "-";
                case 3: return produksi.getJumlahTelur();
                case 4: return produksi.getTanggal();
                default: return null;
            }
        } else {
            ModelKandang kandang = daftarKandang.get(rowIndex);
            switch (columnIndex) {
                case 0: return kandang.getId();
                case 1: return kandang.getNomorKandang();
                case 2: return kandang.getJumlahBebek();
                case 3: return "-";
                case 4: return "-";
                default: return null;
            }
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}