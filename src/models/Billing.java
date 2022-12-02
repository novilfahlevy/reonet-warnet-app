package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Billing extends Model {
    private int id;
    private String name;
    private int operatorId;
    private int computerId;
    private int duration;
    private String date;
    
    public static boolean auth(String username, String password) {
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT password FROM operator WHERE username = ?");
            prpdStmt.setString(1, username);
            
            ResultSet rs = prpdStmt.executeQuery();
            if (rs.next()) {
                String userPassword = rs.getString("password");
                if (password.equals(userPassword)) {
                    return true;
                }
            }
            
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Operator getOperator() {
        Operator operator = new Operator();
        
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT * FROM operator WHERE id = ?");
            prpdStmt.setInt(1, getOperatorId());
            
            ResultSet rs = prpdStmt.executeQuery();
            if (rs.next()) {
                operator.setId(rs.getInt("id"));
                operator.setUsername(rs.getString("username"));
                operator.setRegisteredDate(rs.getString("tanggal"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return operator;
    }
    
    public Computer getComputer() {
        Computer computer = new Computer();
        
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT * FROM komputer WHERE id = ?");
            prpdStmt.setInt(1, getComputerId());
            
            ResultSet rs = prpdStmt.executeQuery();
            if (rs.next()) {
                computer.setId(rs.getInt("id"));
                computer.setName(rs.getString("nama"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return computer;
    }
    
    public static ArrayList<Billing> get() {
        ArrayList<Billing> results = new ArrayList<Billing>();
        
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM billing");
            
            while (rs.next()) {
                Billing billing = new Billing();
                billing.setId(rs.getInt("id"));
                billing.setName(rs.getString("nama"));
                billing.setOperatorId(rs.getInt("id_operator"));
                billing.setComputerId(rs.getInt("id_komputer"));
                billing.setDuration(rs.getInt("durasi"));
                billing.setDate(rs.getString("tanggal"));
                
                results.add(billing);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return results;
    }
    
    public boolean create() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("INSERT INTO billing (nama, id_operator, id_komputer, durasi) VALUES (?, ?, ?, ?)");
            prpdStmt.setString(1, getName());
            prpdStmt.setInt(2, getOperatorId());
            prpdStmt.setInt(3, getComputerId());
            prpdStmt.setInt(4, getDuration());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean update() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("UPDATE billing SET nama = ?, id_komputer = ?, durasi = ? WHERE id = ?");
            prpdStmt.setString(1, getName());
            prpdStmt.setInt(2, getComputerId());
            prpdStmt.setInt(3, getDuration());
            prpdStmt.setInt(4, getId());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("DELETE FROM billing WHERE id = ?");
            prpdStmt.setInt(1, getId());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
