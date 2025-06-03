/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandang;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */

public class DAOKandang implements InterfaceDAOKandang {
    @Override
    public void insert(ModelKandang kandang) throws SQLException, ClassNotFoundException {
        Connection conn = Connector.Connect();
        String query = "INSERT INTO kandang (nomor_kandang, jumlah_bebek) VALUES (?, ?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, kandang.getNomorKandang());
            statement.setInt(2, kandang.getJumlahBebek());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(ModelKandang kandang) throws SQLException, ClassNotFoundException {
        Connection conn = Connector.Connect();
        String query = "UPDATE kandang SET nomor_kandang=?, jumlah_bebek=? WHERE id=?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, kandang.getNomorKandang());
            statement.setInt(2, kandang.getJumlahBebek());
            statement.setInt(3, kandang.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = Connector.Connect();
        conn.setAutoCommit(false); // Start transaction
        try {
            // Disable foreign key checks
            Statement stmt = conn.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");

            // Delete the record
            String deleteQuery = "DELETE FROM kandang WHERE id=?;";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
            }

            // Resequence kandang.id
            String selectQuery = "SELECT id FROM kandang ORDER BY id;";
            List<Integer> currentIds = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery(selectQuery)) {
                while (rs.next()) {
                    currentIds.add(rs.getInt("id"));
                }
            }

            for (int newId = 1; newId <= currentIds.size(); newId++) {
                int oldId = currentIds.get(newId - 1);
                if (oldId != newId) {
                    // Update kandang.id
                    String updateKandang = "UPDATE kandang SET id=? WHERE id=?;";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateKandang)) {
                        updateStmt.setInt(1, newId);
                        updateStmt.setInt(2, oldId);
                        updateStmt.executeUpdate();
                    }

                    // Update produksi_telur.kandang_id
                    String updateProduksi = "UPDATE produksi_telur SET kandang_id=? WHERE kandang_id=?;";
                    try (PreparedStatement updateProdStmt = conn.prepareStatement(updateProduksi)) {
                        updateProdStmt.setInt(1, newId);
                        updateProdStmt.setInt(2, oldId);
                        updateProdStmt.executeUpdate();
                    }
                }
            }

            // Reset AUTO_INCREMENT
            String resetAutoInc = "ALTER TABLE kandang AUTO_INCREMENT = ?;";
            try (PreparedStatement resetStmt = conn.prepareStatement(resetAutoInc)) {
                resetStmt.setInt(1, currentIds.size() + 1);
                resetStmt.executeUpdate();
            }

            // Re-enable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            conn.rollback(); // Rollback on error
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public List<ModelKandang> getAll() throws SQLException, ClassNotFoundException {
        List<ModelKandang> listKandang = new ArrayList<>();
        Connection conn = Connector.Connect();
        String query = "SELECT * FROM kandang;";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                ModelKandang kandang = new ModelKandang();
                kandang.setId(resultSet.getInt("id"));
                kandang.setNomorKandang(resultSet.getString("nomor_kandang"));
                kandang.setJumlahBebek(resultSet.getInt("jumlah_bebek"));
                listKandang.add(kandang);
            }
        }
        return listKandang;
    }

    @Override
    public void insertProduksi(ModelProduksi produksi) throws SQLException, ClassNotFoundException {
        Connection conn = Connector.Connect();
        String query = "INSERT INTO produksi_telur (kandang_id, jumlah_telur, tanggal) VALUES (?, ?, ?);";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, produksi.getKandangId());
            statement.setInt(2, produksi.getJumlahTelur());
            statement.setString(3, produksi.getTanggal());
            statement.executeUpdate();
        }
    }

    @Override
    public List<ModelProduksi> getAllProduksi() throws SQLException, ClassNotFoundException {
        List<ModelProduksi> listProduksi = new ArrayList<>();
        Connection conn = Connector.Connect();
        String query = "SELECT * FROM produksi_telur;";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                ModelProduksi produksi = new ModelProduksi();
                produksi.setId(resultSet.getInt("id"));
                produksi.setKandangId(resultSet.getInt("kandang_id"));
                produksi.setJumlahTelur(resultSet.getInt("jumlah_telur"));
                produksi.setTanggal(resultSet.getString("tanggal"));
                listProduksi.add(produksi);
            }
        }
        return listProduksi;
    }
}