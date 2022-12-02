package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Operator extends Model {
    private int id;
    private String username;
    private String password = "";
    private String registeredDate;
    
    public static int currentOperatorId;
    
    public static int auth(String username, String password) {
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT id, password FROM operator WHERE username = ?");
            prpdStmt.setString(1, username);
            
            ResultSet rs = prpdStmt.executeQuery();
            if (rs.next()) {
                String userPassword = rs.getString("password");
                if (password.equals(userPassword)) {
                    return rs.getInt("id");
                }
            }
            
            return 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        if (!password.isEmpty()) {
            return password;
        }
        
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT password FROM operator WHERE id = ?");
            prpdStmt.setInt(1, getId());
            ResultSet rs = prpdStmt.executeQuery();
            
            if (rs.next()) {
                this.password = rs.getString("password");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
    
    public static ArrayList<Operator> get() {
        ArrayList<Operator> results = new ArrayList<Operator>();
        
        try {
            Model model = new Model();
            Connection conn = model.db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("SELECT * FROM operator WHERE id != ?");
            prpdStmt.setInt(1, Operator.currentOperatorId);
            
            ResultSet rs = prpdStmt.executeQuery();
            
            while (rs.next()) {
                Operator operator = new Operator();
                operator.setId(rs.getInt("id"));
                operator.setUsername(rs.getString("username"));
                operator.setRegisteredDate(rs.getString("tanggal"));
                
                results.add(operator);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return results;
    }
    
    public boolean create() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("INSERT INTO operator (username, password) VALUES (?, ?)");
            prpdStmt.setString(1, getUsername());
            prpdStmt.setString(2, getPassword());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean update() {
        try {
            Connection conn = db.koneksi();
            PreparedStatement prpdStmt = conn.prepareStatement("UPDATE operator SET username = ?, password = ? WHERE id = ?");
            prpdStmt.setString(1, getUsername());
            prpdStmt.setString(2, getPassword());
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
            PreparedStatement prpdStmt = conn.prepareStatement("DELETE FROM operator WHERE id = ?");
            prpdStmt.setInt(1, getId());
            return prpdStmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
