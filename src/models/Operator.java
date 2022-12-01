package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Operator extends Model {
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
}
