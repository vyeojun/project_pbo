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


public class ModelTableKandang extends AbstractTableModel {
    private List<ModelKandang> daftarKandang;
    private String[] kolom = {"ID", "Nomor Kandang", "Jumlah Bebek"};

    public ModelTableKandang(List<ModelKandang> daftarKandang) {
        this.daftarKandang = daftarKandang;
    }

    @Override
    public int getRowCount() {
        return daftarKandang != null ? daftarKandang.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
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

    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}