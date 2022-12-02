package models;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Computer extends Model {
    private int id;
    private int operatorId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static ArrayList<Computer> get() {
        ArrayList<Computer> results = new ArrayList<Computer>();
        
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM komputer");
            
            while (rs.next()) {
                Computer computer = new Computer();
                computer.setId(rs.getInt("id"));
                computer.setName(rs.getString("nama"));
                computer.setOperatorId(rs.getInt("id_operator"));
                
                results.add(computer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return results;
    }
    
    public static int getIdByName(String name) {
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT id FROM komputer WHERE nama = ?");
            prpdStmt.setString(1, name);
            ResultSet rs = prpdStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return 0;
    }
    
    public boolean create() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("INSERT INTO komputer (id_operator, nama) VALUES (?, ?)");
            prpdStmt.setInt(1, getOperatorId());
            prpdStmt.setString(2, getName());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean update() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("UPDATE komputer SET nama = ?, id_operator = ? WHERE id = ?");
            prpdStmt.setString(1, getName());
            prpdStmt.setInt(2, getOperatorId());
            prpdStmt.setInt(3, getId());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("DELETE FROM komputer WHERE id = ?");
            prpdStmt.setInt(1, getId());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
