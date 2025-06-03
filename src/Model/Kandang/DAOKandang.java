/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandang;

import Model.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
    

/**
 *
 * @author HP
 */

public class DAOKandang implements InterfaceDAOKandang {
    @Override
    public void insert(ModelKandang kandang) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "INSERT INTO kandang (nomor_kandang, jumlah_bebek) VALUES (?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kandang.getNomorKandang());
            stmt.setInt(2, kandang.getJumlahBebek());
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
    public void update(ModelKandang kandang) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "UPDATE kandang SET nomor_kandang=?, jumlah_bebek=? WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kandang.getNomorKandang());
            stmt.setInt(2, kandang.getJumlahBebek());
            stmt.setInt(3, kandang.getId());
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
    public void delete(int id) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            conn.setAutoCommit(false); // Start transaction

            // Disable foreign key checks
            Statement stmt = conn.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");

            // Delete the record
            String deleteQuery = "DELETE FROM kandang WHERE id=?;";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
            deleteStmt.close();

            // Resequence kandang.id
            String selectQuery = "SELECT id FROM kandang ORDER BY id;";
            List<Integer> currentIds = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                currentIds.add(rs.getInt("id"));
            }
            rs.close();

            for (int newId = 1; newId <= currentIds.size(); newId++) {
                int oldId = currentIds.get(newId - 1);
                if (oldId != newId) {
                    // Update kandang.id
                    String updateKandang = "UPDATE kandang SET id=? WHERE id=?;";
                    PreparedStatement updateStmt = conn.prepareStatement(updateKandang);
                    updateStmt.setInt(1, newId);
                    updateStmt.setInt(2, oldId);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    // Update produksi_telur.id_kandang (corrected from id_kandang)
                    String updateProduksi = "UPDATE produksi_telur SET id_kandang=? WHERE id_kandang=?;";
                    PreparedStatement updateProdStmt = conn.prepareStatement(updateProduksi);
                    updateProdStmt.setInt(1, newId);
                    updateProdStmt.setInt(2, oldId);
                    updateProdStmt.executeUpdate();
                    updateProdStmt.close();
                }
            }

            // Reset AUTO_INCREMENT
            String resetAutoInc = "ALTER TABLE kandang AUTO_INCREMENT = ?;";
            PreparedStatement resetStmt = conn.prepareStatement(resetAutoInc);
            resetStmt.setInt(1, currentIds.size() + 1);
            resetStmt.executeUpdate();
            resetStmt.close();

            // Re-enable foreign key checks
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
            stmt.close();

            conn.commit(); // Commit transaction
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Failed: " + ex.getLocalizedMessage());
            }
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
    public List<ModelKandang> getAll() {
        List<ModelKandang> listKandang = null;
        Connection conn = null;
        try {
            listKandang = new ArrayList<>();
            conn = Connector.Connect();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM kandang;";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                ModelKandang kandang = new ModelKandang();
                kandang.setId(resultSet.getInt("id"));
                kandang.setNomorKandang(resultSet.getString("nomor_kandang"));
                kandang.setJumlahBebek(resultSet.getInt("jumlah_bebek"));
                listKandang.add(kandang);
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
        return listKandang;
    }

    @Override
    public void insertProduksi(ModelProduksi produksi) {
        Connection conn = null;
        try {
            conn = Connector.Connect();
            String query = "INSERT INTO produksi_telur (id_kandang, jumlah_telur, tanggal) VALUES (?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, produksi.getKandangId());
            stmt.setInt(2, produksi.getJumlahTelur());
            // Convert LocalDate to java.sql.Date for the DATE column
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
                // Retrieve DATE as java.sql.Date and convert to LocalDate
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
            // Convert LocalDate to java.sql.Date for the DATE column
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