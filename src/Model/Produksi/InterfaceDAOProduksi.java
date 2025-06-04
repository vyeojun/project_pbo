/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Produksi;

import java.util.List;

        

/**
 *
 * @author zatri
 */
public interface InterfaceDAOProduksi {
    public void insertProduksi(ModelProduksi produksi);
    public List<ModelProduksi> getAllProduksi();
    public void editProduksi(ModelProduksi produksi);
    public void hapusProduksi(int id);
}