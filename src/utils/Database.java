package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Database {
    public Connection conn;
    public Statement stmt;
    
    private String host = "";
    private String database = "";
    private String username = "";
    private String password = "";
    
    public Database(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public Connection koneksi() {
        try {
            String url = "jdbc:mysql://" + host + "/" + database;
            Class.forName("com.mysql.jdbc.Driver");
            
            this.conn = DriverManager.getConnection(url, username, password);
            
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return conn;
    }
}
