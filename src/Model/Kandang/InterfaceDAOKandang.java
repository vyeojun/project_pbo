/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandang;
import java.util.List;
/**
 *
 * @author HP
 */
public interface InterfaceDAOKandang {
    public void insert(ModelKandang kandang);
    public void update(ModelKandang kandang);
    public void delete(int id);
    public List<ModelKandang> getAll();
    public void insertProduksi(ModelProduksi produksi);
    public List<ModelProduksi> getAllProduksi();
}