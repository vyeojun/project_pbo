/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Produksi;

import java.time.LocalDate;
/**
 *
 * @author HP
 */
public class ModelProduksi {
    private int id;
    private int kandangId;
    private int jumlahTelur;
    private LocalDate tanggal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKandangId() {
        return kandangId;
    }

    public void setKandangId(int kandangId) {
        this.kandangId = kandangId;
    }

    public int getJumlahTelur() {
        return jumlahTelur;
    }

    public void setJumlahTelur(int jumlahTelur) {
        this.jumlahTelur = jumlahTelur;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }
}