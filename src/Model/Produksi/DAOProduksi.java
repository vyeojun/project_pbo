/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Produksi;

import Model.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author zatri
 */

public class DAOProduksi implements InterfaceDAOProduksi {
    @Override
    public void insertProduksi(ModelProduksi produksi) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "INSERT INTO produksi_telur (id_kandang, jumlah_telur, tanggal) VALUES (?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, produksi.getKandangId());
            stmt.setInt(2, produksi.getJumlahTelur());
            LocalDate tanggal = produksi.getTanggal();
            if (tanggal != null) {
                stmt.setDate(3, java.sql.Date.valueOf(tanggal));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public List<ModelProduksi> getAllProduksi() {
        List<ModelProduksi> listProduksi = null;
        Connection conn = null;
        try {
            listProduksi = new ArrayList<>();
            conn = Connector.Connect();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM produksi_telur;";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                ModelProduksi produksi = new ModelProduksi();
                produksi.setId(resultSet.getInt("id"));
                produksi.setKandangId(resultSet.getInt("id_kandang"));
                produksi.setJumlahTelur(resultSet.getInt("jumlah_telur"));
                java.sql.Date sqlDate = resultSet.getDate("tanggal");
                produksi.setTanggal(sqlDate != null ? sqlDate.toLocalDate() : null);
                listProduksi.add(produksi);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getLocalizedMessage());
                }
            }
        }
        return listProduksi;
    }

    @Override
    public void editProduksi(ModelProduksi produksi) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "UPDATE produksi_telur SET id_kandang=?, jumlah_telur=?, tanggal=? WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, produksi.getKandangId());
            stmt.setInt(2, produksi.getJumlahTelur());
            LocalDate tanggal = produksi.getTanggal();
            if (tanggal != null) {
                stmt.setDate(3, java.sql.Date.valueOf(tanggal));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setInt(4, produksi.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Update Failed: " + e.getLocalizedMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public void hapusProduksi(int id) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "DELETE FROM produksi_telur WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getLocalizedMessage());
                }
            }
        }
    }
}